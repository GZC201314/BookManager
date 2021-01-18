package org.bsm.test.user;

import java.util.Date;
import java.util.UUID;

import org.bsm.pageModel.PageUser;
import org.bsm.service.UserServiceI;
import org.bsm.util.DateUtil;
import org.bsm.util.Encrypt;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {

	@Test
	public void testUser() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "classpath:spring.xml", "classpath:spring-hibernate.xml" });
		UserServiceI userServiceI = (UserServiceI) ac.getBean("userServiceI");
		PageUser tuser = new PageUser();
		tuser.setId(UUID.randomUUID().toString());
		// 为了防止主键冲突
		tuser.setName("GZC" + UUID.randomUUID().toString().substring(0, 10));
		tuser.setPwd(Encrypt.e("abc123"));
		tuser.setCreatedatetime(DateUtil.getPastDate(7));
		tuser.setLastmodifytime(new Date());
		tuser.setRoleid(1);
		userServiceI.save(tuser);
	}
}
