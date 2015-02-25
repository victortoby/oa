package com.bjgydx.graduate.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjgydx.graduate.base.service.Impl.BaseAbstractService;
import com.bjgydx.graduate.dao.SysOrganizationDao;
import com.bjgydx.graduate.model.SysOrganization;
import com.bjgydx.graduate.service.SysOrganizationService;

@Service
public class SysOrganizationServiceImpl extends BaseAbstractService<SysOrganization, String> implements SysOrganizationService {
	
	private SysOrganizationDao sysOrganizationDao;
	
	public SysOrganizationDao getSysOrganizationDao() {
		return sysOrganizationDao;
	}
	
	@Autowired
	public void setSysOrganizationDao(SysOrganizationDao sysOrganizationDao) {
		this.sysOrganizationDao = sysOrganizationDao;
		super.setBaseDao(sysOrganizationDao);
	}
	
}
