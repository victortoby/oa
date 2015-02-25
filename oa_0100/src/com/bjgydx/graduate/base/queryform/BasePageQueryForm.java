package com.bjgydx.graduate.base.queryform;

import java.io.Serializable;

import com.bjgydx.graduate.base.vo.ViewObject;

public abstract class BasePageQueryForm<T extends ViewObject<K>, K extends Serializable> extends BaseQueryForm<T, K> {
		
		//页码
		private Integer page = 0;
		
		// 每页行数
		private Integer rows = 999999;

		//全文检索值
		private String queryTextSearch;
		
		public Integer getPage() {
			return page;
		}

		public void setPage(Integer page) {
			this.page = page;
		}

		public Integer getRows() {
			return rows;
		}

		public void setRows(Integer rows) {
			this.rows = rows;
		}

		public String getQueryTextSearch() {
			return queryTextSearch;
		}

		public void setQueryTextSearch(String queryTextSearch) {
			this.queryTextSearch = queryTextSearch;
		}
}
