package com.bjgydx.graduate.base.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bjgydx.graduate.base.vo.ViewObject;
/**
 * 分页对象
 * @author KAI
 *
 * @param <E>
 * @param <K>
 */

public class Pagination<E extends ViewObject<K>,K extends Serializable> {
	private int total;
	private List<E> rows = new ArrayList<E>();
	@SuppressWarnings("rawtypes")
	private List footer = new ArrayList();
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<E> getRows() {
		return rows;
	}
	public void setRows(List<E> rows) {
		this.rows = rows;
	}
	@SuppressWarnings("rawtypes")
	public List getFooter() {
		return footer;
	}
	@SuppressWarnings("rawtypes")
	public void setFooter(List footer) {
		this.footer = footer;
	}
}
