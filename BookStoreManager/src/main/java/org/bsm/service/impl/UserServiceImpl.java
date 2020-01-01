package org.bsm.service.impl;

import java.io.Serializable;

import org.bsm.dao.UserDaoI;
import org.bsm.model.Tuser;
import org.bsm.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service(value="userServiceI")
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private UserDaoI userDaoI;
	
	public UserDaoI getUserDaoI() {
		return userDaoI;
	}
	public void setUserDaoI(UserDaoI userDaoI) {
		this.userDaoI = userDaoI;
	}
	@Override
	public void test() {
		System.out.println("GZC........");
	}
	@Override
	public Serializable save(Tuser t) {
		return userDaoI.save(t);
	}

}
