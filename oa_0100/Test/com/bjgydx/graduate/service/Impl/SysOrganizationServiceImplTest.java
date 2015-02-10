package com.bjgydx.graduate.service.Impl;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bjgydx.graduate.service.SysOrganizationService;

public class SysOrganizationServiceImplTest {
	
	ApplicationContext context = 	new ClassPathXmlApplicationContext("ApplicationContext.xml");
	SysOrganizationService sysOrganizationService = (SysOrganizationService)context.getBean("sysOrganizationServiceImpl");

	@Test
	public void testDelById() {
		sysOrganizationService.delById("1");
		
	}

}
