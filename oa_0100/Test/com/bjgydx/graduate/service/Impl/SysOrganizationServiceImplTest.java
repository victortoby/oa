package com.bjgydx.graduate.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bjgydx.graduate.base.utils.Pagination;
import com.bjgydx.graduate.dao.SysOrganizationDao;
import com.bjgydx.graduate.model.SysOrganization;
import com.bjgydx.graduate.pagequeryform.SysOrganizationpageQueryForm;
import com.bjgydx.graduate.service.SysOrganizationService;

public class SysOrganizationServiceImplTest {
	
	ApplicationContext context = 	new ClassPathXmlApplicationContext("ApplicationContext.xml");
	SysOrganizationService sysOrganizationService = (SysOrganizationService)context.getBean("sysOrganizationServiceImpl");
	
	@Autowired
	private SysOrganizationDao sysOrganizationDao;

	@Test
	public void testDelById() {
		try {
			for(int i = 1; i<100; i++) {
				sysOrganizationService.deleteById(String.valueOf(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSaveSysOrg() throws Exception {
		for(int i = 0; i<100; i++) {
			SysOrganization sysOrganization = new SysOrganization();
			sysOrganization.setOrgName(i+"");
			sysOrganization.setOrgLevel(1);
			sysOrganizationService.save(sysOrganization);
		}
	}
	
	@Test
	public void testFindBy() throws Exception {
		SysOrganization sysOrganization = new SysOrganization();
		sysOrganization.setOrgLevel(1);
		List<SysOrganization> sysOrgList = sysOrganizationService.findByObject(sysOrganization);
		for(SysOrganization s : sysOrgList) {
			sysOrganizationService.deleteById(s.getId());
		}
	}
	
	@Test
	public void testFindByParam() throws Exception {
		Map<String, Object> findParam = new HashMap<String, Object>();
		findParam.put("orgName", "0");
		findParam.put("orgLevel", 1);
		List<SysOrganization> sysOrgList = sysOrganizationService.findByProperty(findParam);
		for(SysOrganization s : sysOrgList) {
			System.out.println("------------------------------------------"+s.getId());
		}
	}
	
	@Test
	public void testqueryPageData() throws Exception{
		SysOrganizationpageQueryForm queryForm = new SysOrganizationpageQueryForm();
		SysOrganization sysOrganization = new SysOrganization();
		sysOrganization.setOrgLevel(1);
		queryForm.setQueryEntity(sysOrganization);
		Pagination<SysOrganization, String> page = sysOrganizationService.queryPageData(queryForm);
		System.out.println(page.getTotal());
	}

}
