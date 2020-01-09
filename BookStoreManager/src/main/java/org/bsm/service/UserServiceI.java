package org.bsm.service;

import java.io.Serializable;

import org.bsm.model.Tuser;
import org.bsm.pageModel.PageUser;

public interface UserServiceI {
	
	public Serializable save(PageUser t);
	
	public Tuser login(PageUser t);
}
