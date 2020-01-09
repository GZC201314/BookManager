package org.bsm.service;

import java.util.List;

import org.bsm.pageModel.Menu;

public interface MenuServiceI {
	public List<Menu> getTreeNote( String id);
	public List<Menu> getAllTreeNote();
}
