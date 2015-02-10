package com.bjgydx.graduate.base.dao;

import java.util.List;

public interface BaseDao<T> {
	/**
	 * 保存更新实体
	 * @param entity
	 */
	public void saveOrUpdate(T entity);
	/**
	 * 删除实体
	 * @param id
	 */
	public void delete(String id);
	/**
	 * 查找实体
	 * @param id
	 */
	public T getById(String id);
	/**
	 * 查找实体
	 * @param ids
	 * @return
	 */
	public List<T> getByIds(String[] ids);
	/**
	 * 查找实体集合
	 * @return
	 */
	public List<T> findAll();
}
