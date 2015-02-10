package com.bjgydx.graduate.base.dao.Impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.bjgydx.graduate.base.dao.BaseDao;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	
	@Autowired
	protected HibernateTemplate hibernateTemplate;
	
	protected Class<T> clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	@Override
	public void saveOrUpdate(T entity) {
		hibernateTemplate.saveOrUpdate(entity);
	}

	@Override
	public void delete(String id) {
		Object entity = this.getById(id);
		if(entity != null) {
			hibernateTemplate.delete(entity);
		}
	}

	@Override
	public T getById(String id) {
        return (T)hibernateTemplate.get(clazz , id);
	}

	@Override
	public List<T> findAll() {
		return (List<T>) hibernateTemplate.find("FROM " + clazz.getSimpleName());
	}
	
	@Override
	public List<T> getByIds(String[] ids) {
		return  (List<T>) hibernateTemplate.find("FROM " + clazz.getSimpleName() + "WHERE ID IN (?)", new Object[] {ids});
	}


}
