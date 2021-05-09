package org.bsm.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.bsm.model.Tauthorize;
import org.bsm.pageModel.Json;
import org.bsm.pageModel.Menu;
import org.bsm.pageModel.PageDataGrid;
import org.bsm.pageModel.PageUser;
import org.bsm.pageModel.Role;
import org.bsm.service.MenuServiceI;
import org.bsm.service.RoleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ModelDriven;

@Action(value = "roleAction")
public class RoleAction extends BaseAction implements ModelDriven<Role> {

    private static final Logger logger = LogManager.getLogger(RoleAction.class.getName());

    Role role = new Role();


    private RoleServiceI roleService;


    public RoleServiceI getRoleService() {
        return roleService;
    }

    @Autowired
    public void setRoleService(RoleServiceI roleService) {
        this.roleService = roleService;
    }

    @Override
    public Role getModel() {
        return role;
    }

    public void getComboboxItem() {
        super.writeJson(roleService.getRoleItem());
    }

    /**
     * 新增角色
     */
    public void add() {
        logger.info("into the add Role function");
        Json j = new Json();
        try {
            Role r = roleService.save(role);
            if (StringUtils.isEmpty(r)) {
                j.setSuccess(false);
                j.setMsg("当前角色已经存在.");
                j.setObj(r);
            } else {
                j.setSuccess(true);
                j.setMsg("新增角色成功.");
                j.setObj(r);
            }
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        super.writeJson(j);
        logger.info("out into the Add Role  function....");
    }

    /**
     * 修改角色
     */
    public void edit() {
        logger.info("into the edit Role function");
        Json j = new Json();
        Integer resultCode = 1;
        try {
            resultCode = roleService.update(role);
            j.setSuccess(true);
            j.setObj(resultCode);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
            j.setObj(resultCode);
            j.setSuccess(false);
        }
        super.writeJson(j);
        logger.info("out into the edit Role  function....");
    }

    /**
     * 获取授权角色的页面
     */
    public void getGrandMenus() {
        logger.info("into the getGrandMenus function");
        List<Menu> menus = null;
        try {
            menus = roleService.getGrandMenus(role);
        } catch (Exception e) {

        }
        super.writeJson(menus);
        logger.info("out the getGrandMenus  function....");
    }

    /**
     * 删除角色
     */
    public void removeRole() {
        logger.info("into the remove Role function");
        Json json = new Json();
        try {
            roleService.removeRole(role);
            json.setMsg("删除角色成功.");
            json.setSuccess(true);
        } catch (Exception e) {
        }
        super.writeJson(json);
        logger.info("out into the remove Role function");
    }

    /**
     * 获取角色列表
     */
    public void datagrid() {
        logger.info("into the role datagrid function");
        PageDataGrid pageDataGrid = new PageDataGrid();
        try {
            pageDataGrid = roleService.datagrid(role);
        } catch (Exception e) {
        }
        super.writeJson(pageDataGrid);
        logger.info("out into the role datagrid function");
    }
}
