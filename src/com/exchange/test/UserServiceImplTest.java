package com.exchange.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

import com.exchange.domain.User;
import com.exchange.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml",
		"classpath*:applicationContext-beans.xml" })
public class UserServiceImplTest {
	@Autowired
	private UserService userService;
	
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
	public void testGetAllUser() {
		List<User> list = userService.getAllUser(0, -1, null, false);
		Assert.assertNotNull(list);
		
		List<User> list1 = userService.getAllUser(0, -1, null, false);
		Assert.assertNotNull(list1);
		Assert.assertEquals(list1, list);
	}
}
