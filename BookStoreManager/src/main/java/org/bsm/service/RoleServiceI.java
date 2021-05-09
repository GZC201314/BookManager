package org.bsm.service;

import java.util.List;

import org.bsm.pageModel.Combobox;
import org.bsm.pageModel.Menu;
import org.bsm.pageModel.PageDataGrid;
import org.bsm.pageModel.Role;

public interface RoleServiceI {
    public List<Combobox> getRoleItem();

    public Role save(Role role);

    public PageDataGrid datagrid(Role role);

    public Integer update(Role role);

    public void removeRole(Role role);

    public List<Menu> getGrandMenus(Role role);

}
