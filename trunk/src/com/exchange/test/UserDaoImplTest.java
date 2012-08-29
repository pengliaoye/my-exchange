package com.exchange.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exchange.dao.UserDao;
import com.exchange.domain.User;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml","classpath*:applicationContext-beans.xml"})
public class UserDaoImplTest {
	@Autowired
	private UserDao userDao;
	@Test
	public void testGetAllUser(){
		List<User> list=userDao.getAllUser(0, -1, null, false);
		Assert.assertNotNull(list);
	}
}
