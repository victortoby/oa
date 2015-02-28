package com.bjgydx.graduate.base.converter.Impl;

import java.io.Serializable;

import com.bjgydx.graduate.base.converter.IPOIDConverter;

/**
 * poid类型转换抽象类
 * @author KAI
 *
 * @param <K>
 */
public abstract class POIDConverter<K extends Serializable> implements IPOIDConverter<K> {

	protected abstract K[] toPOIDImpl(Serializable[] poid);

	public final K[] toPOID(Serializable[] poid) {
		if (poid != null) {
			return toPOIDImpl(poid);
		}

		return null;
	}
}
