package com.bjgydx.graduate.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjgydx.graduate.dao.UserDao;
import com.bjgydx.graduate.model.User;
import com.bjgydx.graduate.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
 
	@Override
	public void saveUser(User user) {
		userDao.saveOrUpdate(user);
	}

	@Override
	public User getUser(String id) {
//		return userDao.get(id);
		return null;
	}

	@Override
	public List<User> findList(String queryString,Object... values) {
//		return userDao.findList(queryString, values);
		return null;
	}
	
	public void saveTwoUser(User u1,User u2) {
		this.saveUser(u1);
//		int a = 1/0;
		this.saveUser(u2);
	}
}
