package com.bjgydx.graduate.service;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bjgydx.graduate.model.User;


public class UserServiceTest {
	@Test
	public void testSaveOrUpdate() throws Exception {
		ApplicationContext context = 	new ClassPathXmlApplicationContext("ApplicationContext.xml");
		UserService userService = (UserService)context.getBean("userServiceImpl");
		User user = new User();
//		user.setId("1212313");
		user.setUserName("zhangkai");
		user.setUserPassword("123456");
//		userService.saveUser(user);
		User user2 = new User();
		user2.setUserName("lisi");
		user2.setUserPassword("123456");
		userService.saveTwoUser(user, user2);
//		userService.saveUser(user);
//		System.out.println("第一次查询");
//		User u = userService.getUser("1212313");
//		System.out.println("第二次查询");
//		User u1 = userService.getUser("1212313");
	}
	
	@Test
	public void testFindList() {
		ApplicationContext context = 	new ClassPathXmlApplicationContext("ApplicationContext.xml");
		UserService userService = (UserService)context.getBean("userServiceImpl");
		System.out.println("第一次查询");
		List<User> userList = userService.findList("from User", null);
		/*for(User user : userList) {
			System.out.println(user.getId());
			System.out.println(user.getUserName());
			System.out.println(user.getUserPassword());
			System.out.println("**********************************************************");
		}*/
		System.out.println("第二次查询");
		List<User> userList2 = userService.findList("from User", null);
		
	}
}
