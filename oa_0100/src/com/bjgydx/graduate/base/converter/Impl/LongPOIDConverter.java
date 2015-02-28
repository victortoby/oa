package com.bjgydx.graduate.base.converter.Impl;

import java.io.Serializable;

public class LongPOIDConverter extends NumberPOIDConverter {
		
	@Override
	protected Number[] getNumberImpl(Serializable[] poid) {
		Long id[] = new Long[poid.length];
		for(int i=0;poid!=null&&i<poid.length;i++){
			poid[i] = poid[i].toString().replaceAll(",", "");
			id[i] = new Long(poid[i].toString());
		}
		return id;
	}

}
