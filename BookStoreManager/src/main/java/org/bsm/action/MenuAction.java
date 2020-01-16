package org.bsm.action;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.bsm.pageModel.Menu;
import org.bsm.service.MenuServiceI;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Action(value = "menuAction")
public class MenuAction extends BaseAction implements ModelDriven<Menu> {

	private static final Logger logger = LogManager.getLogger(MenuAction.class.getName());

	Menu menu = new Menu();


	private MenuServiceI menuService;
	
	public MenuServiceI getMenuService() {
		return menuService;
	}
	@Autowired
	public void setMenuService(MenuServiceI menuService) {
		this.menuService = menuService;
	}
	@Override
	public Menu getModel() {
		return menu;
	}

	// 异步获取菜单树的方法
	public void getTreeNote() {
		super.writeJson(menuService.getTreeNote(menu.getId()));
	}
	
	// 同步获取菜单树的方法
	public void getAllTreeNote() {
		super.writeJson(menuService.getAllTreeNote());
	}

}
