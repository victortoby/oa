package com.bjgydx.graduate.common;

import org.apache.log4j.Logger;
import org.junit.Test;

public class Log4jTest {
	@Test
	public void testMethod() {
		Logger log = Logger.getLogger(Log4jTest.class); 
		// 记录debug级别的信息  
		log.debug("This is debug message.");  
        // 记录info级别的信息  
		log.info("This is info message.");  
        // 记录error级别的信息  
		log.error("This is error message.");  
	}
}
