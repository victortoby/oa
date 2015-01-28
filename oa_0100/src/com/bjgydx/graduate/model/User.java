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
@Table(name="sys_user")
public class User {
	private String id;
	private String userName;
	private String userPassword;
	@Id
	@GeneratedValue(generator = "paymentableGenerator")    
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")   
	@Column(name="id")	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="user_password")
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
}
