package org.bsm.test.mongodb;

import org.bsm.pageModel.Book;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;


public class MongodbTest {
	/**
	 * MongoDb 整合测试
	 */
	@Test
	public void redisTest() {
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] {"classpath:spring.xml","classpath:spring-hibernate.xml"});
		MongoTemplate mongoTemplate = (MongoTemplate) ac.getBean("MongoTemplate");
		Book book = new Book();
		book.setAuthor("gzc");
		book.setCip("12243");
		mongoTemplate.save(book, "book");

	}
}
