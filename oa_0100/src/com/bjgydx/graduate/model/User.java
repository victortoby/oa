package com.bjgydx.graduate.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写   
@Table(name="sys_user")
public class User {
	private String guid;
	private Set<Role> roles = new HashSet<Role>();
	private SysOrganization sysOrganization;
	private String userName;
	private String userPassword;
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")    
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")   
	@Column(name="guid")	
	public String getGuid() {
		return guid;
	}
	@ManyToMany(cascade = {CascadeType.ALL})
	public Set<Role> getRoles() {
		return roles;
	}
	@ManyToOne(cascade = {CascadeType.ALL})
	public SysOrganization getSysOrganization() {
		return sysOrganization;
	}
	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}
	@Column(name="user_password")
	public String getUserPassword() {
		return userPassword;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public void setSysOrganization(SysOrganization sysOrganization) {
		this.sysOrganization = sysOrganization;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
}
