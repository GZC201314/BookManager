package org.bsm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bsm.dao.BaseDaoI;
import org.bsm.model.Tauthorize;
import org.bsm.model.Tmenu;
import org.bsm.pageModel.Menu;
import org.bsm.service.MenuServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service("menuService")
public class MenuServiceImpl implements MenuServiceI {
	private BaseDaoI<Tmenu> menuDao;

	private BaseDaoI<Tauthorize> authorizeDao;

	public BaseDaoI<Tmenu> getMenuDao() {
		return menuDao;
	}

	@Autowired
	public void setMenuDao(BaseDaoI<Tmenu> menuDao) {
		this.menuDao = menuDao;
	}

	public BaseDaoI<Tauthorize> getAuthorizeDao() {
		return authorizeDao;
	}

	@Autowired
	public void setAuthorizeDao(BaseDaoI<Tauthorize> authorizeDao) {
		this.authorizeDao = authorizeDao;
	}

	@Override
	public List<Menu> getTreeNote(Menu menu) {

		String id = menu.getId();
		Integer roleid = null;
		if(!StringUtils.isEmpty(menu.getAttributes().get("role"))) {
			roleid = Integer.parseInt(menu.getAttributes().get("role").toString());
		}
		String hql = "";
		Map<String, Object> params = new HashMap<String, Object>();
		List<Menu> menuList = new ArrayList<>();
		if(StringUtils.isEmpty(roleid)) {
			// 如果是根节点发送的请求
			if (StringUtils.isEmpty(id)) {
				hql = "from Tmenu t where t.tmenu.id is null";
			} else {
				params.put("id", id);
				hql = "from Tmenu t where t.tmenu.id=:id";
				params.put("id", id);
			}
			List<Tmenu> tmenus = menuDao.find(hql,params);
			for (Tmenu tmenu : tmenus) {
				Menu menu1 = new Menu();
				BeanUtils.copyProperties(tmenu, menu1);	
				Tmenu t = tmenu.getTmenu();
				if (!StringUtils.isEmpty(t)) {
					menu1.setPid(t.getId());
				}
				Set<Tmenu> childrens = tmenu.getTmenus();
				// 初始化attributes 属性
				Map<String, Object> attributes = new HashMap<>();
				attributes.put("url", tmenu.getUrl());
				menu1.setAttributes(attributes);
				// 如果子菜单不为空
				if (CollectionUtils.isEmpty(childrens)) {
					menu1.setState("open");
				} else {
					menu1.setState("closed");
				}
				menuList.add(menu1);
			}
			return menuList;
		}
		// 获取当前角色的授权页面
		String authql = "from Tauthorize where roleid=:roleid";
		Map<String, Object> autparams = new HashMap<>();
		autparams.put("roleid", roleid);
		List<Tauthorize> aTauthorizes = authorizeDao.find(authql, autparams);
		for (Tauthorize tauthorize : aTauthorizes) {
			Menu menu1 = new Menu();
			Tmenu tmenu = tauthorize.getTmenu();
//			if("首页".equals(tmenu.getText())) {
//				continue;
//			}
			BeanUtils.copyProperties(tmenu, menu1);	
			Tmenu t = tmenu.getTmenu();
			if (!StringUtils.isEmpty(t)) {
				menu1.setPid(t.getId());
			}
			Set<Tmenu> childrens = tmenu.getTmenus();
			// 初始化attributes 属性
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("url", tmenu.getUrl());
			menu1.setAttributes(attributes);
			
			// 如果子菜单不为空
			if (CollectionUtils.isEmpty(childrens)) {
				menu1.setState("open");
			} else {
				menu1.setState("closed");
			}
			menuList.add(menu1);
		
		}

		return menuList;
	}

	@Override
	public List<Menu> getAllTreeNote() {
		// 
		List<Menu> listMenu = new ArrayList<>();
		List<Tmenu> tmenuList = menuDao.find("from Tmenu");
		if (!CollectionUtils.isEmpty(tmenuList)) {
			// 获取
			for (Tmenu tmenu : tmenuList) {
				Menu menu = new Menu();
				BeanUtils.copyProperties(tmenu, menu);
				Tmenu parent = tmenu.getTmenu();
				if (parent != null) {
					menu.setPid(tmenu.getTmenu().getId());
				}
				Set<Tmenu> childrens = tmenu.getTmenus();

				// 初始化attributes 属性
				Map<String, Object> attributes = new HashMap<>();
				attributes.put("url", tmenu.getUrl());
				menu.setAttributes(attributes);

				// 如果子菜单不为空
				if (CollectionUtils.isEmpty(childrens)) {
					menu.setState("open");
				} else {
					menu.setState("closed");
				}
				listMenu.add(menu);
			}
		}
		return listMenu;
	}

}
