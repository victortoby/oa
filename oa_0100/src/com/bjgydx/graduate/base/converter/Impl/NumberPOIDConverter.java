package com.bjgydx.graduate.base.converter.Impl;

import java.io.Serializable;


abstract class NumberPOIDConverter extends POIDConverter<Number> {

	protected abstract Number[] getNumberImpl(Serializable[] poid);
	
	@Override
	public Number[] toPOIDImpl(Serializable[] poid) {
		return getNumberImpl(poid);
	}

}
