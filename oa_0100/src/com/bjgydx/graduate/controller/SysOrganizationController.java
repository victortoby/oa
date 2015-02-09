package com.bjgydx.graduate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bjgydx.graduate.model.SysOrganization;
import com.bjgydx.graduate.service.SysOrganizationService;

@Controller
@RequestMapping("/sysOrganization")
public class SysOrganizationController {
	@Autowired
	private SysOrganizationService sysOrganizationService;
	/**
	 * 返回列表
	 * @return
	 */
	@RequestMapping("/sysOrganizationList")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("sysOrganization/sysOrganizationList");
		return mav;
	}
	
	/**
	 * 返回新增页面
	 * @return
	 */
	@RequestMapping("/sysOrganizationaddUI")
	public ModelAndView addUI() {
		ModelAndView mav = new ModelAndView("sysOrganization/sysOrganizationSaveUI");
		SysOrganization sysOrganization  = new SysOrganization();
		mav.addObject("sysOrganization", sysOrganization);
		return mav;
	}
	
	/**
	 * 新增
	 * @return
	 */
	@RequestMapping("/sysOrganizationAdd")
	public String add(SysOrganization sysOrganization) {
		sysOrganizationService.save(sysOrganization);
		return "redirect:/sysOrganization/sysOrganizationList";
	}
	
	/**
	 * 返回修改页面
	 * @param sysOrganizationId
	 * @return
	 */
	@RequestMapping("/sysOrganizationEdit/{sysOrganizationId}")
	public ModelAndView editUI(@PathVariable("sysOrganizationId") String sysOrganizationId) {
		SysOrganization sysOrganization = sysOrganizationService.getEntity(sysOrganizationId);
		ModelAndView mav = new ModelAndView("sysOrganization/sysOrganizationSaveUI");
		mav.addObject("sysOrganization", sysOrganization);
		return mav;
	}
	
	/**
	 * 修改保存
	 * @return
	 */
	@RequestMapping("/sysOrganizationEdit")
	public String edit(SysOrganization sysOrganization) {
		sysOrganizationService.update(sysOrganization);
		return "redirect:/sysOrganization/sysOrganizationList";
	}
	
	/**
	 * 删除
	 * @param sysOrganizationId
	 * @return
	 */
	@RequestMapping("/sysOrganizationDelete/{sysOrganizationId}")
	public String delete(@PathVariable("sysOrganizationId") String sysOrganizationId) {
		sysOrganizationService.delById(sysOrganizationId);
		return "redirect:/sysOrganization/sysOrganizationList";
	}
}
