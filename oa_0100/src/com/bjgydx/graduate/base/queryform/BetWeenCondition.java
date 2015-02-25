package com.bjgydx.graduate.base.queryform;

/**
 * Between属性
 * @author KAI
 *
 */
public class BetWeenCondition {
		
		//实体属性
		private String property;
		//前置条件
		private Object lo;
		//后置条件
		private Object hi;
		
		/**
		 * 无参数构造方法
		 */
		public BetWeenCondition() {}
		
		/**
		 * 全参数构造方法
		 * @param property 属性 
		 * @param lo 前置条件
		 * @param hi 后置条件
		 */
		public BetWeenCondition(String property, Object lo, Object hi) {
			this.property = property;
			this.lo = lo;
			this.hi = hi;
		}

		public String getProperty() {
			return property;
		}

		public void setProperty(String property) {
			this.property = property;
		}

		public Object getLo() {
			return lo;
		}

		public void setLo(Object lo) {
			this.lo = lo;
		}

		public Object getHi() {
			return hi;
		}

		public void setHi(Object hi) {
			this.hi = hi;
		}
		
		
		
}
