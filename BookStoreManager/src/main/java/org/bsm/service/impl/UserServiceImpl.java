package org.bsm.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.bsm.dao.BaseDaoI;
import org.bsm.model.Trole;
import org.bsm.model.Tuser;
import org.bsm.pageModel.PageDataGrid;
import org.bsm.pageModel.PageUser;
import org.bsm.service.UserServiceI;
import org.bsm.util.Encrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service(value = "userServiceI")
public class UserServiceImpl implements UserServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private BaseDaoI<Tuser> userDao;

	@Override
	public PageUser save(PageUser t) {
		Tuser tuser = new Tuser();
		PageUser pageUser = new PageUser();
		BeanUtils.copyProperties(t, tuser, "pwd");
		tuser.setPwd(Encrypt.e(t.getPwd()));
		tuser.setId(UUID.randomUUID().toString());
		tuser.setCreatedatetime(new Date());
		tuser.setLastmodifytime(new Date());
		if (!StringUtils.isEmpty(t.getRoleid())) {
			tuser.setTrole(new Trole(Integer.parseInt(t.getRoleid()), 0));
		} else {// 注册默认是买家角色
			tuser.setTrole(new Trole(3, 0));
		}
		userDao.save(tuser);
		BeanUtils.copyProperties(tuser, pageUser);
		pageUser.setRoleid(tuser.getTrole().getRoleid() + "");
		return pageUser;
	}

	@Override
	public Tuser login(PageUser t) {
		String hql = "from Tuser where name=:name and pwd=:pwd";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", t.getName());
		params.put("pwd", Encrypt.e(t.getPwd()));
		return userDao.get(hql, params);
	}

	@Override
	public PageDataGrid datagrid(PageUser pageUser) {
		// 添加过滤条件
		String condition = " where 1=1";
		Map<String, Object> params = new HashMap<>();

		if (pageUser.getStartcreatetime() != null && pageUser.getEndcreatetime() != null) {
			condition += " and createdatetime between :startcreatetime and :endcreatetime";
			params.put("startcreatetime", pageUser.getStartcreatetime());
			params.put("endcreatetime", pageUser.getEndcreatetime());
		}
		if (pageUser.getStartmodifytime() != null && pageUser.getEndmodifytime() != null) {
			condition += " and createdatetime between :startmodifytime and :endmodifytime";
			params.put("startmodifytime", pageUser.getStartmodifytime());
			params.put("endmodifytime", pageUser.getEndmodifytime());
		}
		if (!StringUtils.isEmpty(pageUser.getUserName())) {
			condition += " and name like :name";
			params.put("name", "%%" + pageUser.getUserName().trim() + "%%");
		}

		// 添加排序方式
		String order = "";
		if (pageUser.getSort() != null && pageUser.getOrder() != null) {
			order = " order by " + pageUser.getSort() + " " + pageUser.getOrder();
		}

		String hql = "from Tuser" + condition + order;

		Long count = userDao.count("select count(*) " + hql, params);
		List<Tuser> lt = userDao.find(hql, params, pageUser.getPage(), pageUser.getRows());
		List<PageUser> pageUserList = new ArrayList<PageUser>();
		if (!CollectionUtils.isEmpty(lt)) {
			for (Tuser tuser : lt) {
				PageUser user = new PageUser();
				BeanUtils.copyProperties(tuser, user);
				if (!StringUtils.isEmpty(tuser.getTrole())) {
					user.setRoleid(tuser.getTrole().getRoleid() + "");
				}
				pageUserList.add(user);
			}
		}
		PageDataGrid pageDataGrid = new PageDataGrid();
		pageDataGrid.setRows(pageUserList);
		pageDataGrid.setTotal(count);
		return pageDataGrid;
	}

	@Override
	public void removeUser(PageUser pageUser) {
		// 如果删除的用户不为空
		if (!StringUtils.isEmpty(pageUser.getIds())) {
			String ids = pageUser.getIds();
			String hql = "delete from Tuser t where t.id in (" + ids + ")";
			int count = userDao.executeHql(hql);
			logger.error("删除的返回值是:   " + count);
		}
	}

	@Override
	public Tuser validateName(String name) {
		if (!StringUtils.isEmpty(name)) {
			String hql = "from Tuser where name=:name";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", name);
			return userDao.get(hql, params);
		} else {
			return null;
		}
	}

	@Override
	public Integer update(PageUser pageUser) {
		Integer resultCode = 1;
		if (!StringUtils.isEmpty(pageUser)) {
			String name = pageUser.getName();
			String oldname = pageUser.getOldname();
			// 如果没有修改登录名
			if (oldname.equals(name)) {
				String hql = "from Tuser where name=:name";
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("name", name);
				Tuser tuser = (Tuser) userDao.get(hql, params);
				userDao.delete(tuser);
				if (!StringUtils.isEmpty(tuser)) {
					if (!StringUtils.isEmpty(pageUser.getPwd())) {
						tuser.setPwd(Encrypt.e(pageUser.getPwd()));
					}
					if (!StringUtils.isEmpty(pageUser.getRoleid())) {
						tuser.setTrole(new Trole(Integer.parseInt(pageUser.getRoleid()), 0));
					}
					if (!StringUtils.isEmpty(pageUser.getLastmodifytime())) {
						tuser.setLastmodifytime(pageUser.getLastmodifytime());
					}
					userDao.saveOrUpdate(tuser);
				}
			} else {// 如果修改了用户的名称
				String hql = "from Tuser where name=:name";
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("name", name);
				// 判断新的用户名是否重复
				Tuser tuser = (Tuser) userDao.get(hql, params);
				// 如果不重复,找到要修改的记录修改
				if (StringUtils.isEmpty(tuser)) {
					params.put("name", oldname);
					Tuser oldUser = (Tuser) userDao.get(hql, params);
					userDao.delete(oldUser);
					if (!StringUtils.isEmpty(oldUser)) {
						oldUser.setName(name);
						if (!StringUtils.isEmpty(pageUser.getPwd())) {
							oldUser.setPwd(Encrypt.e(pageUser.getPwd()));
						}
						if (!StringUtils.isEmpty(pageUser.getRoleid())) {
							oldUser.setTrole(new Trole(Integer.parseInt(pageUser.getRoleid()), 0));
						}
						userDao.saveOrUpdate(oldUser);
						resultCode = 0;
					}
				} else {
					resultCode = 2;
				}
			}
		}
		return resultCode;
	}

	@Override
	public PageUser userInfo(PageUser t) {
		String userName = t.getUsername();
		try {
			userName = URLDecoder.decode(userName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (!StringUtils.isEmpty(userName)) {
			String hql = "from Tuser where name=:name";
			Map<String, Object> params = new HashMap<>();
			params.put("name", userName);
			Tuser tuser = userDao.get(hql, params);
			PageUser pageUser = new PageUser();
			BeanUtils.copyProperties(tuser, pageUser);
			pageUser.setRoleid(tuser.getTrole().getRolename());
			return pageUser;
		}
		return null;
	}

	@Override
	public boolean uploadHeadIcon(PageUser pageUser) {
		HttpServletRequest request = ServletActionContext.getRequest(); 
		String url = request.getRequestURL().toString();
		String servletpath = request.getServletPath();
		url = url.replace(servletpath, "");
		String path = ServletActionContext.getServletContext().getRealPath("/upload");
		if (!StringUtils.isEmpty(pageUser.getUploadImg())) {
			String filename = UUID.randomUUID().toString();
			String suffix = pageUser.getUploadFileName();
			suffix = suffix.substring(suffix.lastIndexOf(".") + 1);
			filename = filename+"."+suffix;
			File targetFile = new File(path);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 保存
			pageUser.getUploadImg().renameTo(new File(targetFile,filename));
			try {
				String hql = "from Tuser where name =:name";
				Map<String, Object> params = new HashMap<>();
				params.put("name", pageUser.getUsername());
				Tuser tuser = userDao.get(hql, params);
				if(!StringUtils.isEmpty(tuser)) {
					tuser.setUserlog(url+"/upload/"+filename);
					userDao.saveOrUpdate(tuser);
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;
	}

}
