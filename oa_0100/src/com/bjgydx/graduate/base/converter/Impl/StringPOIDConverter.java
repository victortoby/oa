package com.bjgydx.graduate.base.converter.Impl;

import java.io.Serializable;

/**
 * 字符串类型转换
 * @author KAI
 *
 */
public class StringPOIDConverter extends POIDConverter<String> {

	protected String[] getStringImpl(Serializable[] poid) {
		String id[] = new String[poid.length];
		for(int i=0;poid!=null&&i<poid.length;i++){
			poid[i] = poid[i].toString().replaceAll(",", "");
			id[i] = poid[i].toString();
		}
		return id;
	}
	
	@Override
	protected String[] toPOIDImpl(Serializable[] poid) {
		return  getStringImpl(poid);
	}

}
