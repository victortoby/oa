package com.bjgydx.graduate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bjgydx.graduate.model.SysOrganization;

@Controller
@RequestMapping("/sysOrganization")
public class SysOrganizationController {
	@RequestMapping("/sysOrganizationList")
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView("sysOrganization/sysOrganizationList");
		SysOrganization sysOrganization  = new SysOrganization();
		mav.addObject("sysOrganization", sysOrganization);
		return mav;
	}
}
