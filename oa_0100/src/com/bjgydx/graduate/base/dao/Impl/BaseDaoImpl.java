package com.bjgydx.graduate.base.dao.Impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Column;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.bjgydx.graduate.base.dao.BaseDao;
import com.bjgydx.graduate.base.queryform.BasePageQueryForm;
import com.bjgydx.graduate.base.queryform.BetWeenCondition;
import com.bjgydx.graduate.base.utils.Pagination;
import com.bjgydx.graduate.base.vo.ViewObject;
/**
 * DAO基类
 * @author KAI
 *
 * @param <E>
 * @param <K>
 */
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<E extends ViewObject<K>, K extends Serializable> implements BaseDao<E,K> {
	
	Logger log = Logger.getLogger(BaseDaoImpl.class);
	
	@Autowired
	protected HibernateTemplate hibernateTemplate;
	
	protected Class<E> clazz = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	
	protected Method[] methods = clazz.getDeclaredMethods();

	@Override
	public void save(E entity) {
		hibernateTemplate.save(entity);
	}

	@Override
	public void update(E entity) {
		hibernateTemplate.update(entity);
		log.info(entity);
	}

	@Override
	public void saveOrUpdate(E entity) {
		hibernateTemplate.saveOrUpdate(entity);
		log.info(entity);
	}

	@Override
	public void delete(K id) {
		E entity = this.getById(id);
		hibernateTemplate.delete(entity);
		log.info(entity);
	}

	@Override
	public E getById(K id) {
		E entity = (E)hibernateTemplate.get(clazz , id);
		log.info(entity);
		return entity;
	}

	@Override
	public List<E> getByIds(K[] ids) {
		List<E> entityList = (List<E>) hibernateTemplate.find("FROM " + clazz.getSimpleName() + "WHERE ID IN (?)", new Object[] {ids});
		log.info(entityList);
		return  entityList;
	}

	@Override
	public List<E> findAll() {
		List<E> entityList = (List<E>) hibernateTemplate.find("FROM " + clazz.getSimpleName()); 
		log.info(entityList);
		return  entityList;
	}

	@Override
	public void deleteByIds(K[] ids) {
		hibernateTemplate.deleteAll(this.getByIds(ids));
		log.info(ids);
	}

	@Override
	public List<E> findByObject(E viewObject) {
		return hibernateTemplate.findByExample(viewObject);
	}

	@Override
	public List<E> findByProperty(Map<String, Object> property) {
		String[] paramNames = new String[property.size()];
		Object[] values = new Object[property.size()];
		int index = 0;
		for(Entry<String, Object> entry : property.entrySet()) {
			paramNames[index] = entry.getKey();
			values[index] = entry.getValue();
			index ++;
		}
		StringBuffer hql = new StringBuffer();
		hql.append(" FROM ");
		hql.append(clazz.getSimpleName());
		hql.append(" WHERE 1=1");
		for(String param : paramNames) {
			hql.append(" AND ");
			hql.append(param);
			hql.append(" = :");
			hql.append(param);
		}
		log.info(hql.toString());
		return (List<E>) hibernateTemplate.findByNamedParam(hql.toString(), paramNames, values);
	}

	@Override
	public Pagination<E, K> queryPageData(BasePageQueryForm<E, K> pageQueryForm) {
		StringBuffer hql = new StringBuffer();
		hql.append("FROM " + clazz.getSimpleName() + " WHERE 1=1");
		Pagination<E, K> pagination = new Pagination<E,K>();
		int total = 0;
		hql.append(buildQuerySqlByForm(pageQueryForm));
		log.info(hql.toString());
		List<E> rows = (List<E>) hibernateTemplate.find(hql.toString());
		pagination.setRows(rows);
		if(null != rows && rows.size() > 0){
			total = rows.size();
		}
		pagination.setTotal(total);
		return pagination;
	}
	/**
	 * 根据查询bean进行查询条件拼装
	 * @param pageQueryForm
	 * @return
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	protected StringBuffer buildQuerySqlByForm(BasePageQueryForm<E, K> pageQueryForm) {
		StringBuffer conditionSql = new StringBuffer();
		for(Method method : methods) {
			Column column = method.getAnnotation(Column.class);
			try {
				if(null != column && null != method.invoke(pageQueryForm.getQueryEntity())){
					String methodName = method.getName().substring(3);//截取get之后字符串
					String beginWord = methodName.substring(0, 1);//获取首字母
					beginWord = beginWord.toLowerCase();
					String otherWords = methodName.substring(1);//其余其母
					if(method.getReturnType().equals("java.lang.String")) {
						if(pageQueryForm.getEnableLike() == Boolean.TRUE) {
							conditionSql.append(" AND ");
							conditionSql.append(beginWord + otherWords);
							conditionSql.append(" LIKE '%");
							conditionSql.append(method.invoke(pageQueryForm.getQueryEntity()));
							conditionSql.append(" %'");
						} else {
							conditionSql.append(" AND ");
							conditionSql.append(beginWord + otherWords);
							conditionSql.append(" ='");
							conditionSql.append(method.invoke(pageQueryForm.getQueryEntity()));
							conditionSql.append("'");
						}
					} else {
						conditionSql.append(" AND ");
						conditionSql.append(beginWord + otherWords);
						conditionSql.append(" ='");
						conditionSql.append(method.invoke(pageQueryForm.getQueryEntity()));
						conditionSql.append("'");
					}
				}
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		//between参数
		if(null != pageQueryForm.getBetWeenConditions() && pageQueryForm.getBetWeenConditions().length > 0) {
			for(BetWeenCondition betWeenCondition : pageQueryForm.getBetWeenConditions()) {
				conditionSql.append(" AND " + betWeenCondition.getProperty() + " BETWEEN " + betWeenCondition.getLo() + " AND " + betWeenCondition.getHi());
			}
		}
		//in参数
		if(null != pageQueryForm.getInFieldProperty() && pageQueryForm.getInFieldProperty().size() > 0) {
			for(Entry<String, String> entry : pageQueryForm.getInFieldProperty().entrySet()) {
				conditionSql.append(" AND " + entry.getKey().toUpperCase() + " IN " + "(" + entry.getValue() +")");
			}
		}
		//notIn参数
		if(null != pageQueryForm.getNotInFieldProperty() && pageQueryForm.getNotInFieldProperty().size() > 0) {
			for(Entry<String, String> entry : pageQueryForm.getNotInFieldProperty().entrySet()) {
				conditionSql.append(" AND " + entry.getKey().toUpperCase() + " NOT IN " + "(" + entry.getValue() +")");
			}
		}
		//不为空参数
		if(null != pageQueryForm.getNotNullFieldProperty() && pageQueryForm.getNotNullFieldProperty().size() > 0) {
			for(Entry<String, String> entry : pageQueryForm.getNotNullFieldProperty().entrySet()) {
				conditionSql.append(" AND " + entry.getKey().toUpperCase() + " IS NOT NULL ");
			}
		}
		//顺序排序
		if(null != pageQueryForm.getOrderAscByProperty() && pageQueryForm.getOrderAscByProperty().length > 0) {
			conditionSql.append(" ORDER BY ");
			for(int i = 0; i <= pageQueryForm.getOrderAscByProperty().length; i++) {
				conditionSql.append(pageQueryForm.getOrderAscByProperty()[i]);
				if(i != pageQueryForm.getOrderAscByProperty().length) {
					conditionSql.append(",");
				}
			}
			conditionSql.append(" ASC ");
		}
		//倒叙排序
		if(null != pageQueryForm.getOrderDescByProperty() && pageQueryForm.getOrderDescByProperty().length > 0) {
			conditionSql.append(" ORDER BY ");
			for(int i = 0; i <= pageQueryForm.getOrderDescByProperty().length; i++) {
				conditionSql.append(pageQueryForm.getOrderDescByProperty()[i]);
				if(i != pageQueryForm.getOrderDescByProperty().length) {
					conditionSql.append(",");
				}
			}
			conditionSql.append(" DESC ");
		}
		return conditionSql;
	}
	
}
