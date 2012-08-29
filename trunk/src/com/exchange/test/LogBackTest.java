package com.exchange.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class LogBackTest {
	private static final Logger logger=LoggerFactory.getLogger(LogBackTest.class);
	@Before
	public void setUp(){
		LoggerContext lc=new LoggerContext();
		JoranConfigurator jc=new JoranConfigurator();		
		jc.setContext(lc);
		lc.reset();
		try {
			jc.doConfigure(System.getProperty("java.class.path").split(";")[0]+"/logback.xml");
		} catch (JoranException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testLogBack(){
		MDC.put("user", "");
		MDC.put("client", "");
		logger.debug("嘿嘿");
	}
	@After
	public void tearDown(){
		MDC.remove("user");
		MDC.remove("client");
	}
}
