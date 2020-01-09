package org.bsm.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bsm.dao.BaseDaoI;
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

	public BaseDaoI<Tmenu> getMenuDao() {
		return menuDao;
	}

	@Autowired
	public void setMenuDao(BaseDaoI<Tmenu> menuDao) {
		this.menuDao = menuDao;
	}
	@Override
	public List<Menu> getTreeNote(String id) {
		String hql = "";
		Map<String, Object> params = new HashMap<String, Object>();
		List<Menu> menuList = new ArrayList<>();
		//如果是根节点发送的请求
		if(StringUtils.isEmpty(id)) {
			hql="from Tmenu t where t.tmenu.id is null";
		}else {
			params.put("id", id);
			hql="from Tmenu t where t.tmenu.id=:id";
		}
		List<Tmenu> tmenuList = menuDao.find(hql, params);
		if(!StringUtils.isEmpty(tmenuList)) {
			for (Tmenu tmenu : tmenuList) {
				Menu menu = new Menu();
				BeanUtils.copyProperties(tmenu, menu);
				Tmenu t = tmenu.getTmenu();
				if(!StringUtils.isEmpty(t)) {
					menu.setPid(t.getId());
				}
				Set<Tmenu> childrens = tmenu.getTmenus();
				//初始化attributes 属性
				Map<String, Object> attributes = new HashMap<>();
				attributes.put("url", tmenu.getUrl());
				menu.setAttributes(attributes);
				
				//如果子菜单不为空
				if(CollectionUtils.isEmpty(childrens)) {
					menu.setState("open");
				}else {
					menu.setState("closed");
				}
				menuList.add(menu);
			}
		}
		return menuList;
	}

	@Override
	public List<Menu> getAllTreeNote() {
		// 
		List<Menu> listMenu = new ArrayList<>();
		List<Tmenu> tmenuList = menuDao.find("from Tmenu");
		if(!CollectionUtils.isEmpty(tmenuList)) {
			for (Tmenu tmenu : tmenuList) {
				Menu menu = new  Menu();
				BeanUtils.copyProperties(tmenu, menu);
				Tmenu parent = tmenu.getTmenu();
				if(parent != null) {
					menu.setPid(tmenu.getTmenu().getId());
				}
				Set<Tmenu> childrens = tmenu.getTmenus();
				
				//初始化attributes 属性
				Map<String, Object> attributes = new HashMap<>();
				attributes.put("url", tmenu.getUrl());
				menu.setAttributes(attributes);
				
				//如果子菜单不为空
				if(CollectionUtils.isEmpty(childrens)) {
					menu.setState("open");
				}else {
					menu.setState("closed");
				}
				listMenu.add(menu);
			}
		}
		return listMenu;
	}

}
