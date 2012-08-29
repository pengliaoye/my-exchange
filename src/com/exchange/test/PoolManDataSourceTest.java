package com.exchange.test;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.codestudio.sql.PoolManDataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public class PoolManDataSourceTest {
	@Autowired
	private PoolManDataSource poolManDataSource;
	@Test
	public void testPoolMan()throws Exception
	{
		Connection conn=this.poolManDataSource.getConnection();
		System.out.println(conn);
		Assert.assertNotNull(conn);
		System.out.println(conn.getMetaData().getUserName());
	}
}
