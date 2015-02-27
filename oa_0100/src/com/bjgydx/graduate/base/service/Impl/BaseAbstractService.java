package com.bjgydx.graduate.base.service.Impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bjgydx.graduate.base.dao.BaseDao;
import com.bjgydx.graduate.base.queryform.BasePageQueryForm;
import com.bjgydx.graduate.base.service.IBaseService;
import com.bjgydx.graduate.base.utils.Pagination;
import com.bjgydx.graduate.base.vo.ViewObject;

/**
 * Service基类。封装ExecuteApi接口的相关操作，处理数据等。
 * @author KAI
 *
 * @param <E>
 * @param <K>
 */
public abstract class BaseAbstractService<E extends ViewObject<K>, K extends Serializable> implements IBaseService<E, K> {
	
	Logger log = Logger.getLogger(BaseAbstractService.class);
	
	/**
	 * 公共Dao层接口
	 */
	private BaseDao<E, K> baseDao;

	protected BaseDao<E, K> getBaseDao() {
		return baseDao;
	}

	protected void setBaseDao(BaseDao<E, K> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public Boolean save(E viewObject) throws Exception {
		try {
			baseDao.save(viewObject);
		} catch(Exception error) {
			log.error(error.getMessage(), error);
			throw error;
		}
		return true;
	}

	@Override
	public Boolean update(E viewObject) throws Exception {
		try {
			baseDao.update(viewObject);
		} catch(Exception error) {
			log.error(error.getMessage(), error);
			throw error;
		}
		return true;
	}
	
	@Override
	public Boolean saveOrUpdate(E viewObject) throws Exception {
		try {
			baseDao.update(viewObject);
		} catch(Exception error) {
			log.error(error.getMessage(), error);
			throw error;
		}
		return true;
	} 

	@Override
	public E saveOrUpdateReturnViewObject(E viewObject) throws Exception {
		try {
			baseDao.saveOrUpdate(viewObject);
		} catch(Exception error) {
			log.error(error.getMessage(), error);
			throw error;
		}
		return viewObject;
	}

	@Override
	public E findById(K id) throws Exception {
		return baseDao.getById(id);
	}

	@Override
	public List<E> findByObject(E viewObject) throws Exception {
		return baseDao.findByObject(viewObject);
	}

	@Override
	public List<E> findByProperty(Map<String, Object> property)
			throws Exception {
		return baseDao.findByProperty(property);
	}

	@Override
	public Pagination<E, K> queryPageData(BasePageQueryForm<E, K> pageQueryForm)
			throws Exception {
		return baseDao.queryPageData(pageQueryForm);
	}

	@Override
	public Boolean deleteByIds(K[] ids) throws Exception {
		try {
			baseDao.deleteByIds(ids);
		} catch(Exception error) {
			log.error(error.getMessage(), error);
			throw error;
		}
		return true;
	}

	@Override
	public Boolean deleteById(K id) throws Exception {
		try {
			baseDao.delete(id);
		} catch(Exception error) {
			log.error(error.getMessage(), error);
			throw error;
		}
		return true;
	}

}
