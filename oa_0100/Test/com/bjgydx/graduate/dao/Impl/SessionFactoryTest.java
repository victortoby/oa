package com.bjgydx.graduate.dao.Impl;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SessionFactoryTest {
	ApplicationContext context = 	new ClassPathXmlApplicationContext("ApplicationContext.xml");
	
	@Test
	public void testSessionFactory() {
		SessionFactory s = (SessionFactory)context.getBean("sessionFactory");
		System.out.println(s);
	}
}
