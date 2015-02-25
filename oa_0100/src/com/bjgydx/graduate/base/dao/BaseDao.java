package com.bjgydx.graduate.base.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.bjgydx.graduate.base.queryform.BasePageQueryForm;
import com.bjgydx.graduate.base.utils.Pagination;
import com.bjgydx.graduate.base.vo.ViewObject;
/**
 * DAO接口
 * @author KAI
 *
 * @param <E>
 * @param <K>
 */
public interface BaseDao<E extends ViewObject<K>, K extends Serializable> {
	/**
	 * 保存实体
	 * @param entity
	 * @return
	 */
	public void save(E entity);
	/**
	 * 更新实体
	 * @param entity
	 * @return
	 */
	public void update(E entity);
	/**
	 * 保存更新实体
	 * @param entity
	 */
	public void saveOrUpdate(E entity);
	/**
	 * 删除实体
	 * @param id
	 */
	public void delete(K id);
	/**
	 * 查找实体
	 * @param id
	 */
	public E getById(K id);
	/**
	 * 查找实体
	 * @param ids
	 * @return
	 */
	public List<E> getByIds(K[] ids);
	/**
	 * 查找实体集合
	 * @return
	 */
	public List<E> findAll();
	/**
	 * 批量删除实体
	 * @param ids
	 */
	public void deleteByIds(K[] ids);
	/**
	 * 根据viewObject查询结果集
	 * @param viewObject
	 * @return
	 */
	public List<E> findByObject(E viewObject);
	/**
	 * 根据属性值(Key/Value)查询结果集
	 * @param property
	 * @return
	 */
	public List<E> findByProperty(Map<String, Object> property);
	/**
	 * 根据viewObject查询分页结果集。
	 * @param pageQueryForm
	 * @return
	 * @throws Exception;
	 */
	Pagination<E, K> queryPageData(BasePageQueryForm<E, K> pageQueryForm);
}
