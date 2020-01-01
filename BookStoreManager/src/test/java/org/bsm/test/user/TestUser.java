package org.bsm.test.user;

import java.util.UUID;

import org.bsm.dao.UserDaoI;
import org.bsm.model.Tuser;
import org.bsm.service.UserServiceI;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {

	@Test
	public void testUser() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] {"classpath:spring.xml","classpath:spring-hibernate.xml"});
		UserServiceI userServiceI =(UserServiceI) ac.getBean("userServiceI");
		Tuser tuser = new Tuser();
		tuser.setId(UUID.randomUUID().toString());
		tuser.setName("GZC");
		tuser.setPwd("abc123");
		userServiceI.save(tuser);
	}
}
