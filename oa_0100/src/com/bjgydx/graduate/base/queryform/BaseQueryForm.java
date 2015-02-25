package com.bjgydx.graduate.base.queryform;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.bjgydx.graduate.base.vo.ViewObject;

public abstract class BaseQueryForm<T extends ViewObject<K>, K extends Serializable> {
	//查询实体
	private T queryEntity;
	//是否使用like
	private Boolean enableLike = Boolean.TRUE;
	//倒叙排序属性
	private String[] orderDescByProperty;
	//正序排序属性
	private String[] orderAscByProperty;
	//in条件
	private Map<String, String> inFieldProperty = new HashMap<String, String>();
	//notIn条件
	private Map<String, String> notInFieldProperty = new HashMap<String, String>();
	//不为空属性
	private Map<String, String> notNullFieldProperty = new HashMap<String, String>();
	//between属性
	private BetWeenCondition[] betWeenConditions;
	
	/**
	 * 通过表单内容构造查询条件
	 * 如有需要可以重写此方法构造特殊参数
	 */
	public abstract void constructForm();

	/**
	 * 创建实体
	 */
	public abstract T createEntity();
	
	public T getQueryEntity() {
		return queryEntity;
	}
	public void setQueryEntity(T queryEntity) {
		this.queryEntity = queryEntity;
	}
	public Boolean getEnableLike() {
		return enableLike;
	}
	public void setEnableLike(Boolean enableLike) {
		this.enableLike = enableLike;
	}
	public String[] getOrderDescByProperty() {
		return orderDescByProperty;
	}
	public void setOrderDescByProperty(String[] orderDescByProperty) {
		this.orderDescByProperty = orderDescByProperty;
	}
	public String[] getOrderAscByProperty() {
		return orderAscByProperty;
	}
	public void setOrderAscByProperty(String[] orderAscByProperty) {
		this.orderAscByProperty = orderAscByProperty;
	}
	public Map<String, String> getInFieldProperty() {
		return inFieldProperty;
	}
	public void setInFieldProperty(Map<String, String> inFieldProperty) {
		this.inFieldProperty = inFieldProperty;
	}
	public Map<String, String> getNotInFieldProperty() {
		return notInFieldProperty;
	}
	public void setNotInFieldProperty(Map<String, String> notInFieldProperty) {
		this.notInFieldProperty = notInFieldProperty;
	}
	public Map<String, String> getNotNullFieldProperty() {
		return notNullFieldProperty;
	}
	public void setNotNullFieldProperty(Map<String, String> notNullFieldProperty) {
		this.notNullFieldProperty = notNullFieldProperty;
	}
	public BetWeenCondition[] getBetWeenConditions() {
		return betWeenConditions;
	}
	public void setBetWeenConditions(BetWeenCondition[] betWeenConditions) {
		this.betWeenConditions = betWeenConditions;
	}
	
}
