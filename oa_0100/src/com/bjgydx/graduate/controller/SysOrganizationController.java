package com.bjgydx.graduate.controller;

import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bjgydx.graduate.base.controller.BaseCRUDController;
import com.bjgydx.graduate.base.utils.Pagination;
import com.bjgydx.graduate.model.SysOrganization;
import com.bjgydx.graduate.pagequeryform.SysOrganizationpageQueryForm;
import com.bjgydx.graduate.service.SysOrganizationService;

@Controller
@RequestMapping("/sysOrganization")
public class SysOrganizationController extends  BaseCRUDController<SysOrganizationpageQueryForm, SysOrganization, String>{
	@Autowired
	private SysOrganizationService sysOrganizationService;
	
	final String LIST_PATH = "sysOrganization/sysOrganizationList";
	
	final String EDIT_PATH = "sysOrganization/sysOrganizationEdit";
	
//	@RequestMapping("/sysOrganizationList")
//	public String getList() {
//		return LIST_PATH;
//	}
	
//	/**
//	 * 返回列表数据
//	 * @return
//	 */
//	@RequestMapping("/query")
//	@ResponseBody
//	public Map<String, Object> query() {
////		List<SysOrganization> sysOrganizations = sysOrganizationService.query();
////		if(null != sysOrganizations) {
////			Map<String, Object> result = new HashMap<String, Object>();
////			result.put("total", sysOrganizations.size());
////			result.put("rows", sysOrganizations);
////			return result;
////		}
//		return null;
//	}
//	
//	/**
//	 * 返回新增页面
//	 * @return
//	 */
//	@RequestMapping("/sysOrganizationaddUI")
//	public ModelAndView addUI() {
//		ModelAndView mav = new ModelAndView("sysOrganization/sysOrganizationSaveUI");
//		SysOrganization sysOrganization  = new SysOrganization();
//		mav.addObject("sysOrganization", sysOrganization);
//		return mav;
//	}
//	
//	/**
//	 * 新增
//	 * @return
//	 */
//	@RequestMapping("/sysOrganizationAdd")
//	public String add(SysOrganization sysOrganization) {
//		sysOrganizationService.save(sysOrganization);
//		return "redirect:/sysOrganization/sysOrganizationList";
//	}
//	
//	/**
//	 * 返回修改页面
//	 * @param sysOrganizationId
//	 * @return
//	 */
//	@RequestMapping("/sysOrganizationEditUI/{sysOrganizationId}")
//	public ModelAndView editUI(@PathVariable("sysOrganizationId") String sysOrganizationId) {
////		SysOrganization sysOrganization = sysOrganizationService.getEntity(sysOrganizationId);
//		ModelAndView mav = new ModelAndView("sysOrganization/sysOrganizationSaveUI");
////		mav.addObject("sysOrganization", sysOrganization);
//		return mav;
//	}
//	
//	/**
//	 * 修改保存
//	 * @return
//	 */
//	@RequestMapping("/sysOrganizationEdit")
//	public String edit(SysOrganization sysOrganization) {
////		sysOrganizationService.update(sysOrganization);
//		return "redirect:/sysOrganization/sysOrganizationList";
//	}
//	
//	/**
//	 * 删除
//	 * @param sysOrganizationId
//	 * @return
//	 */
//	@RequestMapping("/sysOrganizationDelete/{sysOrganizationId}")
//	public String delete(@PathVariable("sysOrganizationId") String sysOrganizationId) {
////		sysOrganizationService.delById(sysOrganizationId);
//		return "redirect:/sysOrganization/sysOrganizationList";
//	}

	@Override
	protected String getListPath() {
		return LIST_PATH;
	}

	@Override
	protected String getEditPath() {
		return EDIT_PATH;
	}

	@Override
	public void beforeInit() throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeSave(SysOrganization savingEntity)
			throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeRemove(String[] poids) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected SysOrganization createEntity() {
		return null;
	}

	@Override
	protected Pagination<SysOrganization, String> refreshData(
			SysOrganizationpageQueryForm queryForm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void beforeView(SysOrganization savingEntity, Model model)
			throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Class<String> getPOIDType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void beforeEdit(SysOrganization savingEntity, Model model)
			throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getAttachTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void afterRemove(String[] poids) {
		// TODO Auto-generated method stub
		
	}
}
