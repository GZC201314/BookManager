package org.bsm.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bsm.dao.BaseDaoI;
import org.bsm.model.Tuser;
import org.bsm.pageModel.PageUser;
import org.bsm.service.UserServiceI;
import org.bsm.util.Encrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service(value="userServiceI")
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private BaseDaoI<Tuser> userDao;
	
	@Override
	public Serializable save(PageUser t) {
		Tuser tuser = new Tuser();
		BeanUtils.copyProperties(t, tuser, "pwd");
		tuser.setPwd(Encrypt.e(t.getPwd()));
		tuser.setId(UUID.randomUUID().toString());
		return userDao.save(tuser);
	}

	@Override
	public Tuser login(PageUser t) {
		String hql = "from Tuser where name=:name and pwd=:pwd";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", t.getName());
		params.put("pwd", Encrypt.e(t.getPwd()));
		return userDao.get(hql, params);
	}

}
