package org.bsm.dao.impl;

import java.io.Serializable;

import org.bsm.dao.UserDaoI;
import org.bsm.model.Tuser;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDaoI {
	
	@Autowired
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Serializable save(Tuser t) {
		
		return this.sessionFactory.getCurrentSession().save(t);
	}

}
