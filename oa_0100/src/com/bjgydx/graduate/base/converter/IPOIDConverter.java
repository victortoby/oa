package com.bjgydx.graduate.base.converter;

import java.io.Serializable;
/**
 * poid类型转换接口
 * @author KAI
 *
 * @param <K>
 */
public interface IPOIDConverter<K extends Serializable> {
	
	K[] toPOID(Serializable[] poid);
	
}
