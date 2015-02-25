package com.bjgydx.graduate.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.bjgydx.graduate.base.vo.ViewObject;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写   
@Table(name="SYS_ORGANIZATION")
public class SysOrganization extends ViewObject<String>{
	private static final long serialVersionUID = 1L;
	private String orgGuid;
	private String orgName;
	private Integer orgLevel;
	private Set<SysOrganization> children = new HashSet<SysOrganization>();
	private SysOrganization parent;
	private Set<User> users = new HashSet<User>();
	
	/**页面列表字段*/
	public static final String ORG_GUID = "orgGuid";
	public static final String PARENT_ORG_GUID = "parentOrgGuid";
	public static final String ORG_NAME = "orgName";
	public static final String ORG_LEVEL= "orgLevel";
	
	/**页面列表名称*/
	public static final String ORG_GUID_VO = "组织机构GUID";
	public static final String PARENT_ORG_GUID_VO = "上级组织机构GUID";
	public static final String ORG_NAME_VO = "组织机构名称";
	public static final String ORG_LEVEL_VO = "组织机构级别";
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")    
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")   
	@Column(name="org_guid")	
	@Override
	public String getId() {
		return orgGuid;
	}
	public void setId(String orgGuid) {
		this.orgGuid = orgGuid;
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
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "parent")
	public Set<SysOrganization> getChildren() {
		return children;
	}
	public void setChildren(Set<SysOrganization> children) {
		this.children = children;
	}
	@ManyToOne
	public SysOrganization getParent() {
		return parent;
	}
	public void setParent(SysOrganization parent) {
		this.parent = parent;
	}
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "sysOrganization")
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
