package com.bjgydx.graduate.service;

import com.bjgydx.graduate.model.SysOrganization;

public interface SysOrganizationService {

	void save(SysOrganization sysOrganization);

	void update(SysOrganization sysOrganization);

	void delById(String sysOrganizationId);

	SysOrganization getEntity(String sysOrganizationId);

	

}
