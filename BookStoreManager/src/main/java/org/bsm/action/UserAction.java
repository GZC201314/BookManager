package org.bsm.action;



import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.bsm.service.BookI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@ParentPackage("basePackage")
@Namespace("/")
@Action(value="userAction")
public class UserAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(UserAction.class.getName());

	
	private BookI bookI;
	
	public BookI getBookI() {
		return bookI;
	}
	public void setBookI(BookI bookI) {
		this.bookI = bookI;
	}

	public static Logger getLogger() {
		return logger;
	}

	public void test() {
		logger.error("UserAction class test method is running!!!!!!");
//		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
//		BookI bookI = (BookI) ac.getBean("bookI");
		bookI.test();
		
	}
}
