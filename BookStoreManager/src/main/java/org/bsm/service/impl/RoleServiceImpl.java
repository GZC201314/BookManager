package org.bsm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bsm.dao.BaseDaoI;
import org.bsm.model.Trole;
import org.bsm.model.Tuser;
import org.bsm.pageModel.Combobox;
import org.bsm.pageModel.PageDataGrid;
import org.bsm.pageModel.Role;
import org.bsm.service.RoleServiceI;
import org.bsm.util.Encrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service("roleService")
public class RoleServiceImpl implements RoleServiceI {

	@Autowired
	private BaseDaoI<Trole> roleDao;

	@Override
	public List<Combobox> getRoleItem() {
		String hql = "from Trole";
		List<Combobox> comboboxs = new ArrayList<Combobox>();
		List<Trole> lists = roleDao.find(hql);
		for (Trole trole : lists) {
			Combobox combobox = new Combobox();
			combobox.setId(trole.getRoleid() + "");
			combobox.setText(trole.getRolename());
			comboboxs.add(combobox);
		}
		return comboboxs;
	}

	@Override
	public Role save(Role role) {
		// 判断新增的角色是否重复
		if (!StringUtils.isEmpty(role)) {
			String hql = "from Trole where rolename=:rolename";
			Map<String, Object> params = new HashMap<>();
			params.put("rolename", role.getRolename());
			Trole rTrole = roleDao.get(hql, params);
			if (!StringUtils.isEmpty(rTrole)) {
				return null;
			}
			Trole trole = new Trole();
			Role rolereturn = new Role();
			BeanUtils.copyProperties(role, trole);
			Long roleid = roleDao.count("select max(roleid) from Trole");
			trole.setRoleid(Integer.parseInt(++roleid + ""));
			roleDao.save(trole);
			BeanUtils.copyProperties(trole, rolereturn);
			return rolereturn;
		}
		return null;
	}

	@Override
	public PageDataGrid datagrid(Role role) {
		// 添加过滤条件
		String condition = " where 1=1";
		Map<String, Object> params = new HashMap<>();

		if (!StringUtils.isEmpty(role.getRolename())) {
			condition += " and rolename like :name";
			params.put("name", "%%" + role.getRolename().trim() + "%%");
		}

		// 添加排序方式
		String order = "";
		if (role.getSort() != null && role.getOrder() != null) {
			order = " order by " + role.getSort() + " " + role.getOrder();
		}

		String hql = "from Trole" + condition + order;

		Long count = roleDao.count("select count(*) " + hql, params);
		List<Trole> lt = roleDao.find(hql, params, role.getPage(), role.getRows());
		List<Role> roleList = new ArrayList<Role>();
		if (!CollectionUtils.isEmpty(lt)) {
			for (Trole trole : lt) {
				Role r = new Role();
				BeanUtils.copyProperties(trole, r);
				roleList.add(r);
			}
		}
		PageDataGrid pageDataGrid = new PageDataGrid();
		pageDataGrid.setRows(roleList);
		pageDataGrid.setTotal(count);
		return pageDataGrid;
	}

	@Override
	public Integer update(Role role) {
		Integer resultCode = 1;
		if (!StringUtils.isEmpty(role)) {
			int roleid = role.getRoleid();
			String rolename = role.getRolename();
			String oldrolename = role.getOldrolename();

			String hql = "from Trole where roleid=:roleid";
			Map<String, Object> params = new HashMap<>();
			params.put("roleid", roleid);
			Trole trole = roleDao.get(hql, params);
			// 如果没有查询到角色
			if (StringUtils.isEmpty(trole)) {
				return resultCode;
			}
			// 如果查询到角色信息

			// 如果没有修改用户名
			if (oldrolename.equals(rolename)) {
				resultCode = 0;
			} else {// 如果修改了角色名
					// 1 先查询新的角色名是否有存在的角色
				String hqlString = "from Trole where rolename=:rolename";
				Map<String, Object> params1 = new HashMap<>();
				params1.put("rolename", rolename);
				Trole trole1 = roleDao.get(hqlString, params1);
				// 如果不存在同名的记录
				if (StringUtils.isEmpty(trole1)) {
					resultCode = 0;
				} else {
					resultCode = 2;
				}
			}
			if(resultCode != 2) {
				BeanUtils.copyProperties(role, trole);
				roleDao.update(trole);
			}
			
			
		}
		return resultCode;
	}

	@Override
	public void removeRole(Role role) {
		// 
		//如果删除的用户不为空
		if(!StringUtils.isEmpty(role.getIds())) {
			String ids = role.getIds();
			String hql = "delete from Trole t where t.roleid in ("+ids+")";
			  roleDao.executeHql(hql);
		}
	}

}
