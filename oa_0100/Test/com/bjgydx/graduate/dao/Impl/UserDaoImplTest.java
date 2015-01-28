package com.bjgydx.graduate.dao.Impl;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bjgydx.graduate.dao.UserDao;

public class UserDaoImplTest {

	@Test
	public void testFindAll() {
		ApplicationContext context = 	new ClassPathXmlApplicationContext("ApplicationContext.xml");
		UserDao userDao = (UserDao)context.getBean("userDaoImpl");
		userDao.findAll();
	}

}
