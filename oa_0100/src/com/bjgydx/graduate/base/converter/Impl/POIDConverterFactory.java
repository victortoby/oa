package com.bjgydx.graduate.base.converter.Impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bjgydx.graduate.base.converter.IPOIDConverter;

/**
 * POID类型转换工厂
 * @author KAI
 *
 */
public class POIDConverterFactory {

	protected static Logger log = Logger.getLogger(POIDConverterFactory.class);
	
	private static POIDConverterFactory singleton = new POIDConverterFactory();
	
	private static Map<Class<?>, POIDConverter<?>> pool = new HashMap<Class<?>, POIDConverter<?>>();
		
	static {
		pool.put(String.class, new StringPOIDConverter());
		pool.put(Long.class, new LongPOIDConverter());
	}
	
	/**
	 * 单例模式取得当前的引用
	 */
	public static final POIDConverterFactory getInstance() {
		if(null == singleton) {
			singleton = new POIDConverterFactory();
		}
		return singleton;
	}
	
	//转换方法
		public IPOIDConverter<?> getSimpleConverter(Class<?> poidType) {
			if (!isSupport(poidType)) {
				log.debug("该工厂不支持当前类型:" + poidType.getName() + "的转换！" );
				return null;
			}
			return pool.get(poidType);
		}
		
		//是否支持
		public boolean isSupport(Class<?> poidType) {
			return pool.containsKey(poidType);
		}
	
	
}
