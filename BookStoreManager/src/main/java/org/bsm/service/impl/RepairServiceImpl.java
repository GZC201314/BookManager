package org.bsm.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bsm.dao.BaseDaoI;
import org.bsm.model.Tmenu;
import org.bsm.model.Trole;
import org.bsm.model.Tuser;
import org.bsm.service.RepairServiceI;
import org.bsm.util.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("repairService")
public class RepairServiceImpl implements RepairServiceI {

	@Autowired
	BaseDaoI<Tuser> userDaoI;

	@Autowired
	BaseDaoI<Tmenu> menuDaoI;

	public BaseDaoI<Tuser> getUserDao() {
		return userDaoI;
	}

	public void setUserDao(BaseDaoI<Tuser> userDao) {
		this.userDaoI = userDao;
	}

	public BaseDaoI<Tmenu> getMenuDao() {
		return menuDaoI;
	}

	public void setMenuDao(BaseDaoI<Tmenu> menuDao) {
		this.menuDaoI = menuDao;
	}

	@Override
	public void repair() {
		// 修复用户数据
		repairUser();
		// 修复菜单数据
		repairMenu();
	}

	private void repairMenu() {
		Tmenu root = new Tmenu();
		root.setId("0");
		root.setText("首页");
		root.setUrl("");
		menuDaoI.saveOrUpdate(root);
		
		Tmenu yhzx = new Tmenu();
		yhzx.setId("yhzx");
		yhzx.setTmenu(root);
		yhzx.setText("用户中心");
		menuDaoI.saveOrUpdate(yhzx);

		Tmenu grzx = new Tmenu();
		grzx.setId("grzx");
		grzx.setTmenu(yhzx);
		grzx.setText("个人中心");
		grzx.setUrl("/admin/grzx.jsp");
		menuDaoI.saveOrUpdate(grzx);
		
		Tmenu xxxg = new Tmenu();
		xxxg.setId("xxxg");
		xxxg.setTmenu(yhzx);
		xxxg.setText("信息修改");
		xxxg.setUrl("/admin/xxxg.jsp");
		menuDaoI.saveOrUpdate(xxxg);
		
		
		Tmenu xtgl = new Tmenu();
		xtgl.setId("xtgl");
		xtgl.setTmenu(root);
		xtgl.setText("系统管理");
		menuDaoI.saveOrUpdate(xtgl);
		
		

		Tmenu yhgl = new Tmenu();
		yhgl.setId("yhgl");
		yhgl.setTmenu(xtgl);
		yhgl.setText("用户管理");
		yhgl.setUrl("/admin/yhgl.jsp");
		menuDaoI.saveOrUpdate(yhgl);

		Tmenu jsgl = new Tmenu();
		jsgl.setId("jsgl");
		jsgl.setTmenu(xtgl);
		jsgl.setText("角色管理");
		jsgl.setIconCls("icon-save");
		jsgl.setUrl("/admin/jsgl.jsp");
		menuDaoI.saveOrUpdate(jsgl);

		Tmenu qxgl = new Tmenu();
		qxgl.setId("qxgl");
		qxgl.setTmenu(xtgl);
		qxgl.setText("权限管理");
		qxgl.setUrl("/admin/qxgl.jsp");
		menuDaoI.saveOrUpdate(qxgl);

		Tmenu cdgl = new Tmenu();
		cdgl.setId("cdgl");
		cdgl.setTmenu(xtgl);
		cdgl.setText("菜单管理");
		cdgl.setUrl("/admin/cdgl.jsp");
		menuDaoI.saveOrUpdate(cdgl);

		Tmenu buggl = new Tmenu();
		buggl.setId("buggl");
		buggl.setTmenu(xtgl);
		buggl.setText("BUG管理");
		buggl.setUrl("/admin/buggl.jsp");
		menuDaoI.saveOrUpdate(buggl);
	}

	private void repairUser() {
		String hql = "from Tuser where id != '0' and name=:name";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "admin");
		Tuser t = userDaoI.get(hql, params);
		if (t != null) {
			t.setName(t.getName() + "_" + UUID.randomUUID().toString());
		}
		Tuser admin = new Tuser();
		admin.setId("0");
		admin.setName("admin");
		admin.setPwd(Encrypt.e("admin"));
		admin.setTrole(new Trole(0, "超级管理员", "超级管理员", "", 0, null, null));
		userDaoI.saveOrUpdate(admin);
	}

}
