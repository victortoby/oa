package com.bjgydx.graduate.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class WebApplicationContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 注册项目名称，项目名称对应web.xml
	 * <display-name>oa</display-name>
	 * 方便页面获取
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("------------------------------------------------" + arg0.getServletContext().getContextPath());
		arg0.getServletContext().setAttribute("ctx", arg0.getServletContext().getContextPath());
	}

}
