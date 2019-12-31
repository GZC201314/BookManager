package org.bsm.test.book;

import org.bsm.interfa.BookI;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class firstTest {
	@Test
	public void test() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] {"classpath:spring.xml"});
		BookI bookI = (BookI) ac.getBean("bookI");
		bookI.test();
	}
}
