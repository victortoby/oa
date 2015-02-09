package com.bjgydx.graduate.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjgydx.graduate.dao.SysOrganizationDao;
import com.bjgydx.graduate.model.SysOrganization;
import com.bjgydx.graduate.service.SysOrganizationService;

@Service
public class SysOrganizationServiceImpl implements SysOrganizationService {
	
	@Autowired
	private SysOrganizationDao sysOrganizationDao;

	@Override
	public void save(SysOrganization sysOrganization) {
		sysOrganizationDao.saveOrUpdate(sysOrganization);
	}

	@Override
	public void update(SysOrganization sysOrganization) {
		sysOrganizationDao.saveOrUpdate(sysOrganization);
	}

	@Override
	public void delById(String sysOrganizationId) {
		sysOrganizationDao.delete(sysOrganizationId);
	}

	@Override
	public SysOrganization getEntity(String sysOrganizationId) {
		return sysOrganizationDao.getById(sysOrganizationId);
	}

}
