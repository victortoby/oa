package com.bjgydx.graduate.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.bjgydx.graduate.model.User;
import com.bjgydx.graduate.util.UserUtil;

public abstract class BaseController {
	
	Logger log = Logger.getLogger(BaseController.class);
	
	/**
	 * 获取当前登录用户
	 * @param session
	 * @return
	 */
	public User getLoginUser(HttpSession session) {
		User user = UserUtil.getUserFromSession(session);
		return user;
	}
	
	/** 获取HttpServletRequest */  
	public static HttpServletRequest getRequest() {  
	    return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();    
	} 

	/** 获取HttpServletResponse */  
	public static HttpServletResponse getResponse() {  
	    return ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();  
	}
}
