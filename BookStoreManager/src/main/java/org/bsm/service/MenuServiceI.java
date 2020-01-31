package org.bsm.service;

import java.util.List;

import org.bsm.pageModel.Menu;

public interface MenuServiceI {
	public List<Menu> getTreeNote( Menu menu);
	public List<Menu> getAllTreeNote();
}
