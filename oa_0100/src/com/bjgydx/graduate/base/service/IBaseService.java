package com.bjgydx.graduate.base.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.bjgydx.graduate.base.queryform.BasePageQueryForm;
import com.bjgydx.graduate.base.utils.Pagination;
import com.bjgydx.graduate.base.vo.ViewObject;

/**
 * 封装了常用的CRUD数据库操作
 * @author KAI
 *
 * @param <E> 实体
 * @param <K> 主键
 */

public interface IBaseService<E extends ViewObject<K>, K extends Serializable> {
	
	/**
	 * 保存对象
	 * @param viewObject
	 * @return 如果保存成功,返回TRUE
	 * @throws Exception
	 */
	Boolean save(E viewObject) throws Exception;
	
	/**
	 * 更新对象
	 * @param viewObject
	 * @return 如果更新成功,返回TRUE
	 * @throws Exception
	 */
	Boolean update(E viewObject)throws Exception;
	
	/**
	 * 保存或更新对象
	 * @param viewObject
	 * @return 保存后的对象，如果保存成功
	 * @throws Exception
	 */
	E saveOrUpdate(E viewObject) throws Exception;
	
	/**
	 * 根据ID查找实体
	 * @param id
	 * @return 主键id为id的实体对象
	 * @throws Exception
	 */
	E findById(K id) throws Exception;
	
	/**
	 * 根据viewObject查询结果集
	 * @param viewObject
	 * @return
	 * @throws Exception
	 */
	List<E> findByObject(E viewObject) throws Exception;
	
	/**
	 * 根据属性值(Key/Value)查询结果集
	 * @param property
	 * @return
	 * @throws Exception
	 */
	List<E> findByProperty(Map<String, Object> property) throws Exception;
	
	/**
	 * 根据viewObject查询分页结果集。
	 * @param pageQueryForm
	 * @return
	 * @throws Exception
	 */
	Pagination<E, K> queryPageData(BasePageQueryForm<E, K> pageQueryForm) throws Exception;
	
	/**
	 * 根据Id数组删除数据
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	Boolean deleteByIds(K[] ids) throws Exception;
	
	/**
	 * 根据Id删除数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Boolean deleteById(K id) throws Exception;
	
}
