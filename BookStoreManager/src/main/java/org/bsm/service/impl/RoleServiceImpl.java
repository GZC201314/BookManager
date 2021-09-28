package org.bsm.service.impl;

import org.bsm.dao.BaseDaoI;
import org.bsm.model.Tauthorize;
import org.bsm.model.Tmenu;
import org.bsm.model.Trole;
import org.bsm.pageModel.Combobox;
import org.bsm.pageModel.Menu;
import org.bsm.pageModel.PageDataGrid;
import org.bsm.pageModel.Role;
import org.bsm.service.RoleServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Service("roleService")
public class RoleServiceImpl implements RoleServiceI {

  @Autowired private BaseDaoI<Trole> roleDao;

  @Autowired private BaseDaoI<Tauthorize> authorizeDao;

  @Autowired private BaseDaoI<Tmenu> menuDao;

  @Override
  public List<Combobox> getRoleItem() {
    String hql = "from Trole where disabled = :disabled";
    Map<String, Object> params = new HashMap<>();
    params.put("disabled", 0);
    List<Combobox> comboboxs = new ArrayList<Combobox>();
    List<Trole> lists = roleDao.find(hql, params);
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
      String hql = "from Trole where rolename=:rolename and disabled=:disable";
      Map<String, Object> params = new HashMap<>();
      params.put("rolename", role.getRolename());
      params.put("disable", 0);
      Trole rTrole = roleDao.get(hql, params);
      if (!StringUtils.isEmpty(rTrole)) {
        return null;
      }
      Trole trole = new Trole();
      Role rolereturn = new Role();
      BeanUtils.copyProperties(role, trole, "roleid");
      Long roleid = roleDao.count("select max(roleid) from Trole");
      trole.setRoleid(Integer.parseInt(++roleid + ""));
      // 新增角色的授权页面的添加
      Set<Tauthorize> tauthorizes = trole.getTauthorizes();
      roleDao.save(trole);
      String[] idarr = role.getMenusId().split(",");
      for (String id : idarr) {
        id = id.replace("'", "");
        Tauthorize tauthorize = new Tauthorize();
        tauthorize.setId(UUID.randomUUID().toString());
        Tmenu tmenu = new Tmenu();
        tmenu.setId(id);
        tauthorize.setTrole(trole);
        tauthorize.setTmenu(tmenu);
        authorizeDao.save(tauthorize);
        tauthorizes.add(tauthorize);
      }
      String[] pidarr = role.getMenusPid().split(",");
      for (String id : pidarr) {
        id = id.replace("'", "");
        Tauthorize tauthorize = new Tauthorize();
        tauthorize.setId(UUID.randomUUID().toString());
        Tmenu tmenu = new Tmenu();
        tmenu.setId(id);
        tauthorize.setTrole(trole);
        tauthorize.setTmenu(tmenu);
        authorizeDao.save(tauthorize);
        tauthorizes.add(tauthorize);
      }
      BeanUtils.copyProperties(trole, rolereturn);
      return rolereturn;
    }
    return null;
  }

  @Override
  public PageDataGrid datagrid(Role role) {
    // 添加过滤条件
    String condition = " where disabled = 0 ";
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
      } else { // 如果修改了角色名
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
      if (resultCode != 2) {
        BeanUtils.copyProperties(role, trole);
        // 删除原来授权的页面
        String hql1 = "delete from  tauthorize t where t.roleid=:roleid";
        Map<String, Object> autParams = new HashMap<>();
        autParams.put("roleid", trole.getRoleid());
        authorizeDao.executeHql(hql1, autParams);

        // 添加新的授权页面
        Set<Tauthorize> tauthorizes = trole.getTauthorizes();
        tauthorizes.clear();
        String[] idarr = role.getMenusId().split(",");
        for (String id : idarr) {
          id = id.replace("'", "");
          Tauthorize tauthorize = new Tauthorize();
          tauthorize.setId(UUID.randomUUID().toString());
          Tmenu tmenu = new Tmenu();
          tmenu.setId(id);
          tauthorize.setTrole(trole);
          tauthorize.setTmenu(tmenu);
          authorizeDao.save(tauthorize);
          tauthorizes.add(tauthorize);
        }
        String[] pidarr = role.getMenusPid().split(",");
        Set<String> set = new HashSet<String>(Arrays.asList(pidarr));
        pidarr = set.toArray(new String[set.size()]);
        for (String id : pidarr) {
          id = id.replace("'", "");
          if ("undefined".equals(id) || "0".equals(id)) {
            continue;
          }
          Tauthorize tauthorize = new Tauthorize();
          tauthorize.setId(UUID.randomUUID().toString());
          Tmenu tmenu = new Tmenu();
          tmenu.setId(id);
          tauthorize.setTrole(trole);
          tauthorize.setTmenu(tmenu);
          authorizeDao.save(tauthorize);
          tauthorizes.add(tauthorize);
        }
        roleDao.update(trole);
      }
    }
    return resultCode;
  }

  @Override
  public void removeRole(Role role) {
    //
    // 如果删除的角色不为空
    if (!StringUtils.isEmpty(role.getIds())) {
      String ids = role.getIds();
      // 删除原来授权的页面
      String hql1 = "delete from Tauthorize t where t.roleid in(" + ids + ")";
      authorizeDao.executeHql(hql1);
      String hql = "from Trole t where t.roleid in (" + ids + ")";
      List<Trole> troles = roleDao.find(hql);
      for (Trole trole : troles) {
        trole.setDisabled(1);
        roleDao.update(trole);
      }
    }
  }

  @Override
  public List<Menu> getGrandMenus(Role role) {
    if (!StringUtils.isEmpty(role.getRoleid())) {
      // 1.获取全部的菜单信息
      List<Menu> listMenu = new ArrayList<>();
      List<Tmenu> tmenuList = menuDao.find("from Tmenu");

      // 2.获取所有的授权的页面
      String hql = "from Tauthorize where roleid=:roleid";
      Map<String, Object> params = new HashMap<>();
      params.put("roleid", role.getRoleid());
      List<Tauthorize> tauthorizes = authorizeDao.find(hql, params);

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

          // 3.设置是否选中该页面
          for (Tauthorize tauthorize : tauthorizes) {
            if (tmenu.getId().equals(tauthorize.getTmenu().getId())
                && tauthorize.getTmenu().getTmenus().isEmpty()) {
              menu.setChecked(true);
            }
          }

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
    return null;
  }
}
