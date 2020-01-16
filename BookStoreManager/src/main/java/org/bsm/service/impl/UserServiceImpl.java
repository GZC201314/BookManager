package org.bsm.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bsm.dao.BaseDaoI;
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

	@Autowired
	private BaseDaoI<Tuser> userDao;

	@Override
	public Serializable save(PageUser t) {
		Tuser tuser = new Tuser();
		BeanUtils.copyProperties(t, tuser, "pwd");
		tuser.setPwd(Encrypt.e(t.getPwd()));
		tuser.setId(UUID.randomUUID().toString());
		return userDao.save(tuser);
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
		//添加过滤条件
		String condition = " where 1=1";
		Map<String, Object> params = new HashMap<>();

		if(pageUser.getStartcreatetime()!=null && pageUser.getEndcreatetime() !=null) {
			condition +=" and createdatetime between :startcreatetime and :endcreatetime";
			params.put("startcreatetime", pageUser.getStartcreatetime());
			params.put("endcreatetime", pageUser.getEndcreatetime());
		}
		if(pageUser.getStartmodifytime()!=null && pageUser.getEndmodifytime() !=null) {
			condition +=" and createdatetime between :startmodifytime and :endmodifytime";
			params.put("startmodifytime", pageUser.getStartmodifytime());
			params.put("endmodifytime", pageUser.getEndmodifytime());
		}
		if(!StringUtils.isEmpty(pageUser.getUsername())) {
			condition += " and name like :name";
			params.put("name", "%%"+pageUser.getUsername().trim()+"%%");
		}
	
		//添加排序方式
		String order = "";
		if(pageUser.getSort()!=null && pageUser.getOrder()!=null) {
			order = " order by "+pageUser.getSort()+" "+pageUser.getOrder();
		}
		
		String hql ="from Tuser"+condition+order;


		
		
		Long count = userDao.count("select count(*) "+hql,params);
		 List<Tuser> lt = userDao.find(hql,params, pageUser.getPage(), pageUser.getRows());
		 List<PageUser> pageUserList = new ArrayList<PageUser>();
		if(!CollectionUtils.isEmpty(lt)) {
			for (Tuser tuser : lt) {
				PageUser user = new PageUser();
				BeanUtils.copyProperties(tuser, user);
				pageUserList.add(user);
			}
		}
		PageDataGrid pageDataGrid = new PageDataGrid();
		pageDataGrid.setRows(pageUserList);
		pageDataGrid.setTotal(count);
		return pageDataGrid;
	}


}
