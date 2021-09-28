package org.bsm.listener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.bsm.service.RepairServiceI;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

/** Application Lifecycle Listener implementation class RepairListener */
@WebListener
public class RepairListener
    implements ServletContextListener,
        ServletContextAttributeListener,
        HttpSessionListener,
        HttpSessionAttributeListener,
        HttpSessionActivationListener,
        HttpSessionBindingListener,
        ServletRequestListener,
        ServletRequestAttributeListener {
  /** Logger for this class */
  private static final Logger logger = LogManager.getLogger(RepairListener.class.getName());

  /** Default constructor. */
  public RepairListener() {}

  /** @see HttpSessionListener#sessionCreated(HttpSessionEvent) */
  public void sessionCreated(HttpSessionEvent arg0) {}

  /** @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent) */
  public void attributeRemoved(ServletContextAttributeEvent arg0) {}

  /** @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent) */
  public void attributeAdded(ServletRequestAttributeEvent arg0) {}

  /** @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent) */
  public void attributeReplaced(HttpSessionBindingEvent arg0) {}

  /** @see HttpSessionActivationListener#sessionWillPassivate(HttpSessionEvent) */
  public void sessionWillPassivate(HttpSessionEvent arg0) {}

  /** @see ServletContextListener#contextInitialized(ServletContextEvent) */
  public void contextInitialized(ServletContextEvent arg0) {
    logger.info("修复数据库");
    ApplicationContext ctx =
        WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
    RepairServiceI repairService = (RepairServiceI) ctx.getBean("repairService");
    repairService.repair();
  }

  /** @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent) */
  public void attributeAdded(ServletContextAttributeEvent arg0) {}

  /** @see ServletRequestListener#requestDestroyed(ServletRequestEvent) */
  public void requestDestroyed(ServletRequestEvent arg0) {}

  /** @see ServletRequestAttributeListener#attributeRemoved(ServletRequestAttributeEvent) */
  public void attributeRemoved(ServletRequestAttributeEvent arg0) {}

  /** @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent) */
  public void valueBound(HttpSessionBindingEvent arg0) {}

  /** @see ServletRequestListener#requestInitialized(ServletRequestEvent) */
  // 在这边你可以添加请求参数
  public void requestInitialized(ServletRequestEvent arg0) {
    if (logger.isDebugEnabled()) {
      logger.info("请求初始化 - start"); // $NON-NLS-1$
    }
    arg0.getServletContext().setAttribute("gzc", "郭志超");

    if (logger.isDebugEnabled()) {
      logger.info("请求初始化 - end"); // $NON-NLS-1$
    }
  }

  /** @see HttpSessionListener#sessionDestroyed(HttpSessionEvent) */
  public void sessionDestroyed(HttpSessionEvent arg0) {}

  /** @see HttpSessionActivationListener#sessionDidActivate(HttpSessionEvent) */
  public void sessionDidActivate(HttpSessionEvent arg0) {}

  /** @see ServletContextListener#contextDestroyed(ServletContextEvent) */
  public void contextDestroyed(ServletContextEvent arg0) {}

  /** @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent) */
  public void attributeReplaced(ServletRequestAttributeEvent arg0) {}

  /** @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent) */
  public void attributeAdded(HttpSessionBindingEvent arg0) {}

  /** @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent) */
  public void attributeRemoved(HttpSessionBindingEvent arg0) {}

  /** @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent) */
  public void attributeReplaced(ServletContextAttributeEvent arg0) {}

  /** @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent) */
  public void valueUnbound(HttpSessionBindingEvent arg0) {}
}
