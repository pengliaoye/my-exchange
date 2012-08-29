package com.exchange.test;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.util.Assert;

public class IbatisTest {
	@Test
	public void testConf() throws IOException{	
		String resource="Configuration.xml";
		Reader reader=Resources.getResourceAsReader(resource);
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
		Assert.notNull(sessionFactory);
	}
}
