package com.bjgydx.graduate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bjgydx.graduate.model.SysOrganization;

@Controller
@RequestMapping("/sysOrganization")
public class SysOrganizationController {
	/**
	 * 返回列表
	 * @return
	 */
	@RequestMapping("/sysOrganizationList")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("sysOrganization/sysOrganizationList");
		SysOrganization sysOrganization  = new SysOrganization();
		mav.addObject("sysOrganization", sysOrganization);
		return mav;
	}
	
	/**
	 * 返回新增页面
	 * @return
	 */
	@RequestMapping("/sysOrganizationaddUI")
	public ModelAndView addUI() {
		ModelAndView mav = new ModelAndView("sysOrganization/sysOrganizationSaveUI");
		return mav;
	}
	
	/**
	 * 新增
	 * @return
	 */
	@RequestMapping("/sysOrganizationAdd")
	public String add() {
		return "redirect:/sysOrganization/sysOrganizationList";
	}
	
	@RequestMapping("/sysOrganizationEdit/{sysOrganizationId}")
	public ModelAndView editUI(@PathVariable("sysOrganizationId") String sysOrganizationId) {
		ModelAndView mav = new ModelAndView("sysOrganization/sysOrganizationSaveUI");
		return mav;
	}
	
	/**
	 * 修改保存
	 * @return
	 */
	@RequestMapping("/sysOrganizationEdit")
	public String edit() {
		return "redirect:/sysOrganization/sysOrganizationList";
	}
	
	@RequestMapping("/sysOrganizationDelete/{sysOrganizationId}")
	public String delete(@PathVariable("sysOrganizationId") String sysOrganizationId) {
		return "redirect:/sysOrganization/sysOrganizationList";
	}
}
