package org.bsm.service;

import org.bsm.model.Tuser;
import org.bsm.pageModel.PageDataGrid;
import org.bsm.pageModel.PageUser;

public interface UserServiceI {

	public PageUser save(PageUser t);
	
	public PageUser userInfo(PageUser t);

	public Tuser login(PageUser t);

	public PageDataGrid datagrid(PageUser pageUser);
	
	public void removeUser(PageUser pageUser);
	
	public Tuser validateName(String name);
	
	public Integer update(PageUser pageUser);
	
	public boolean uploadHeadIcon(PageUser pageUser);
}
