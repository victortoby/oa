package com.bjgydx.graduate.service;

import java.util.List;

import com.bjgydx.graduate.model.User;

public interface UserService {
	public void saveUser(User user);
	public User getUser(String id);
	public List<User> findList(String queryString,Object... values);
	public void saveTwoUser(User u1,User u2);
}
