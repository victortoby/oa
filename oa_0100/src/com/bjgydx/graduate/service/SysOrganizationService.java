package com.bjgydx.graduate.service;

import org.springframework.stereotype.Service;

import com.bjgydx.graduate.model.SysOrganization;

@Service
public interface SysOrganizationService {

	void save(SysOrganization sysOrganization);

	void update(SysOrganization sysOrganization);

	void delById(String sysOrganizationId);

	SysOrganization getEntity(String sysOrganizationId);

	

}
