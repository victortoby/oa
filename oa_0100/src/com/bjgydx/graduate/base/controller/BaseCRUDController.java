package com.bjgydx.graduate.base.controller;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjgydx.graduate.base.Message;
import com.bjgydx.graduate.base.queryform.BasePageQueryForm;
import com.bjgydx.graduate.base.service.IBaseService;
import com.bjgydx.graduate.base.utils.Pagination;
import com.bjgydx.graduate.base.vo.ViewObject;


/**
 * 增删改查Controller基类
 * @author KAI
 *
 * @param <Q>
 * @param <E>
 * @param <K>
 */
public abstract class BaseCRUDController<
		Q extends BasePageQueryForm<E,K>,
		E extends ViewObject<K>,
		K extends Serializable>  extends BaseController {
	
	protected static Logger logger = Logger.getLogger(BaseCRUDController.class);

	private IBaseService<E,K> baseService;
	
	// 添加、编辑、查看的区分标志
	protected Boolean isDisabled = Boolean.FALSE;

	protected abstract String getListPath();

	protected abstract String getEditPath();

	//需要在子类中实现的方法，在保存前进行的操作，如数据类型的转换，关联实体对象的转换
	public abstract void beforeInit() throws ApplicationException;

	//需要在子类中实现的方法，在保存前进行的操作，如数据类型的转换，关联实体对象的转换
	public abstract void beforeSave(E savingEntity) throws ApplicationException;

	//需要在子类中实现的方法，删除前进行的操作
	public abstract void beforeRemove(K[] poids) throws ApplicationException;

	//需要在子类中实现的方法，创建实现类的要操作的实体
	protected abstract E createEntity();

	//需要在子类中实现的方法，查询并返回分页对象
	protected abstract Pagination<E, K> refreshData(Q queryForm);
	
	// 在子类中重写此方法，在查看时进行操作，如数据类型的转换，关联实体对象的转换
	protected abstract void beforeView(E savingEntity, Model model) throws ApplicationException;

	protected abstract Class<K> getPOIDType();
	
	// 在子类中重写此方法，在编辑时进行操作，如数据类型的转换，关联实体对象的转换
	protected abstract void beforeEdit(E savingEntity, Model model) throws ApplicationException;
	
	public BaseCRUDController() {}


//	public BaseCRUDController(SysUserService sysUserService) {
//		super.setSysUserService(sysUserService);
//	}
//
//
//	public BaseCRUDController(SysUserService sysUserService, SysOperationLogService sysOperationLogService) {
//		setSysUserService(sysUserService);
//		setSysOperationLogService(sysOperationLogService);
//	}
	
	public IBaseService<E, K> getBaseService() {
		return baseService;
	}
	
	public void setBaseService(IBaseService<E, K> baseService) {
		this.baseService = baseService;
	}
	
//	@SuppressWarnings("unchecked")
//	protected IPOIDConverter<K> getPOIDConverter() {
//		return (IPOIDConverter<K>) POIDConverterFactory.getInstance().getSimpleConverter(getPOIDType());
//	}

	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public String init() {
		try {
			beforeInit();
		} catch (ApplicationException e) {
			e.printStackTrace();
			logger.error("系统异常: " + e , e.fillInStackTrace());
		}
		return getListPath();
	}

	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<E, K> query(Q queryForm) {
		queryForm.constructForm();
		return refreshData(queryForm);
	}

	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String newEntity(Model model) {
		E saveEntity = createEntity();
		model.addAttribute("saveEntity",saveEntity);
		model.addAttribute("isDisabled",isDisabled);
		return getEditPath();
	}

	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String editEntity(@PathVariable K id, Model model) {
		try{
			if (null != id) {
				E saveEntity = baseService.findById(id);
				beforeEdit(saveEntity, model);
				model.addAttribute("saveEntity",saveEntity);
				model.addAttribute("isDisabled",isDisabled);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("系统异常: " + e , e.fillInStackTrace());
		}
		return getEditPath();
	}
	
	@RequestMapping(value="/view/{id}", method = RequestMethod.GET)
	public String viewEntity(@PathVariable K id, Model model) {
		try{
			if (null != id) {
				E saveEntity = baseService.findById(id);
				beforeView(saveEntity, model);
				model.addAttribute("saveEntity",saveEntity);
				model.addAttribute("isDisabled",!isDisabled);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("系统异常: " + e , e.fillInStackTrace());;
		}
		return getEditPath();
	}

	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Message saveEntity(@Validated E entity, BindingResult result) {
		try {
			beforeSave(entity);
			baseService.saveOrUpdate(entity);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统异常: " + e , e.fillInStackTrace());
			return createMessage(Boolean.FALSE, "保存失败！" + e.getMessage());
		}

		return  createMessage(Boolean.TRUE, "保存成功！");
	}
	
	protected Message createMessage(Boolean success, String msgStr,String...otherInfo ) {
		Message msg = new Message();
		msg.setSuccess(success);
		msg.setMessage(msgStr);
		String newOtherInfo = "";
		if(otherInfo != null && otherInfo.length > 0) {
			for(String s : otherInfo){
				newOtherInfo += s + ",";
			}
			String allOtherInfo = newOtherInfo.substring(0, newOtherInfo.length()-1);
			msg.setOtherInfo(allOtherInfo);
		}
		return msg;
	}
	
	@RequestMapping(value = "/saveOrUpdateAndView", method = RequestMethod.POST)
	@ResponseBody
	public Message saveOrUpdateAndView(@Validated E entity, BindingResult result) {
		E returnEntity = null;
		try {
			beforeSave(entity);
			returnEntity = baseService.saveOrUpdateReturnViewObject(entity);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统异常: " + e , e.fillInStackTrace());
			return createMessage(Boolean.FALSE, "保存失败！" + e.getMessage());
		}

		return  createMessage(Boolean.TRUE, "保存成功！",returnEntity != null ?returnEntity.getId().toString():"");
	}

//	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
//	@ResponseBody
//	public Message delete(@RequestParam("poids[]") K[] poids) {
//		
//		try{
//			if (null != poids) {
//				if (poids instanceof Serializable) {
//					poids = (K[]) getPOIDConverter().toPOID((Serializable[]) poids);
//				}
//				
//				beforeRemove(poids);
//				baseService.deleteByIds(poids);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			logger.error("系统异常: " + e , e.fillInStackTrace());
//			String detailMsg = "";
//			if (e.getMessage().contains("ORA-02292")) {
//				if(poids.length == 1) {
//					detailMsg = "<br>此数据已被使用，不可删除！";
//				} else {
//					detailMsg = "<br>要删除的数据中有被使用的数据，不可删除！";
//				}
//			}
//			return createMessage(Boolean.FALSE, "删除失败！" + detailMsg);
//		}
//		return createMessage(Boolean.TRUE, "删除成功！");
//	}
//	
//	@RequestMapping(value = "/deleteBatchWithAttach", method = RequestMethod.POST)
//	@ResponseBody
//	public Message deleteWithAttach(@RequestParam("poids[]") K[] poids) {
//		
//		try{
//			if (null != poids) {
//				if (poids instanceof Serializable) {
//					poids = (K[]) getPOIDConverter().toPOID((Serializable[]) poids);
//				}
//				
//				beforeRemove(poids);
//				String attachTableName = getAttachTableName();
//				Map<String,List<Map<Object, Object>>> diskFileNameListMap = baseService.queryDiskFileNameByMainGuids(poids, attachTableName);
//				Boolean isSuccess = baseService.deleteByIds(poids);
//				afterRemove(poids);
//				if (isSuccess) {
//					for (Entry<String,List<Map<Object, Object>>> entry : diskFileNameListMap.entrySet()) {
//						baseService.delDiskFiles(entry.getKey(), entry.getValue());
//					}
//				}
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			logger.error("系统异常: " + e , e.fillInStackTrace());
//			return createMessage(Boolean.FALSE, "删除失败！" + e.getMessage());
//		}
//		return createMessage(Boolean.TRUE, "删除成功！");
//	}
//
//	
//

//
	
//

//	

//
//	// 获取自定义表名，需在子类中复写此方法
//	protected String getAttachTableName() {
//		return null;
//	}
//	
//	protected void afterRemove(K[] poids) {
//		
//	}
}
