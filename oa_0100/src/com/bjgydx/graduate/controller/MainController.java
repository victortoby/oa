package com.bjgydx.graduate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	 @RequestMapping(value = "/index")
	    public String index() {
	        return "login";
	    }
	 
	 /*@RequestMapping(value = "/main/index")
	    public String index() {
	        return "/main/index";
	    }*/

	    @RequestMapping(value = "/main/login")
	    public String login() {
	        return "main";
	    }
}
