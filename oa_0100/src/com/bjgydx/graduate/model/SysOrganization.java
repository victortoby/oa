package com.bjgydx.graduate.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写   
@Table(name="SYS_ORGANIZATION")
public class SysOrganization {
	private String orgGuid;
	private String parentOrgGuid;
	private String orgName;
	private Integer orgLevel;
	
	/**页面列表字段*/
	public static final String ORG_GUID = "ORG_GUID";
	public static final String PARENT_ORG_GUID = "PARENT_ORG_GUID";
	public static final String ORG_NAME = "ORG_NAME";
	public static final String ORG_LEVEL= "ORG_LEVEL";
	
	/**页面列表名称*/
	public static final String ORG_GUID_VO = "组织机构GUID";
	public static final String PARENT_ORG_GUID_VO = "上级组织机构GUID";
	public static final String ORG_NAME_VO = "组织机构名称";
	public static final String ORG_LEVEL_VO = "组织机构级别";
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")    
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")   
	@Column(name="org_guid")	
	public String getOrgGuid() {
		return orgGuid;
	}
	public void setOrgGuid(String orgGuid) {
		this.orgGuid = orgGuid;
	}
	@Column(name="parent_org_guid")
	public String getParentOrgGuid() {
		return parentOrgGuid;
	}
	public void setParentOrgGuid(String parentOrgGuid) {
		this.parentOrgGuid = parentOrgGuid;
	}
	@Column(name="org_name")
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Column(name="org_level")
	public Integer getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}

}
