package org.bsm.test.redis;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTest {
	/**
	 * redis 整合测试
	 */
	@Test
	public void redisTest() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] {"classpath:spring.xml","classpath:spring-hibernate.xml"});
		RedisTemplate<String, String> redisTemplate = (RedisTemplate<String, String>) ac.getBean("redisTemplate");
		redisTemplate.opsForValue().set("spring", "Hello World!!!");
		redisTemplate.expire("spring", 30, TimeUnit.SECONDS);
	}
}
