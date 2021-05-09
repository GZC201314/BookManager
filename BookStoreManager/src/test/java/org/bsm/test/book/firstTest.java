package org.bsm.test.book;


import org.bsm.pageModel.BookQuery;
import org.bsm.service.BookServiceI;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class firstTest {
    @Test
    public void test() {
        ApplicationContext ac = new ClassPathXmlApplicationContext(
                new String[]{"classpath:spring.xml", "classpath:spring-hibernate.xml"});
        BookServiceI bookService = (BookServiceI) ac.getBean("bookService");
//		bookService.getBookInfobyIsbn(new BookQuery("9787312026065"));
        bookService.getBookInfobyIsbn(new BookQuery("9787312026065"));
    }
}
