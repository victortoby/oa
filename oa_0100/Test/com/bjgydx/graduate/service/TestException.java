package com.bjgydx.graduate.service;

import org.junit.Test;

public class TestException {
	@Test
	public void tryCatch()throws Exception {
		try {
			System.out.println("try-----------");
			throw new Exception();
		} catch(Exception e) {
			System.out.println("catch-----------");
			throw e;
		}
	}
}
