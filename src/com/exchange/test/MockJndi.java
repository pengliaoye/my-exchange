package com.exchange.test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.mybatis.spring.SqlSessionTemplate;


public class MockJndi {
	@Test
	public void testJndi() {
		Driver driver=null;
		try {
			driver=(Driver)Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url="jdbc:oracle:thin:@134.201.138.248:1521:rcat";
		String userName="zxdb_cc_03";
		String passWord="zxdb_cc_03";
		DataSource obj = new SimpleDriverDataSource(driver,url,userName,passWord);
		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = "select 1 from dual";
		try {
			SimpleNamingContextBuilder builder = SimpleNamingContextBuilder
					.emptyActivatedContextBuilder();
			builder.bind("jdbc/zxdb_cc_03", obj);
			userName="zxdb_cc_04";
			passWord="zxdb_cc_04";
			obj = new SimpleDriverDataSource(driver,url,userName,passWord);
			builder.bind("jdbc/zxdb_cc_04", obj);
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("jdbc/zxdb_cc_03");
			conn = ds.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.executeQuery();

			DataSource dsr = (DataSource) context.lookup("jdbc/zxdb_cc_04");
			conn = dsr.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.executeQuery();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		@Test
	public void test() {
		SimpleNamingContextBuilder builder =new SimpleNamingContextBuilder();
		DataSource ds=new DriverManagerDataSource();
		builder.bind("zxdb_cc_00", ds);
		try {
			builder.activate();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		SqlSessionTemplate sqlSessionTemplate=context.getBean("sqlSessionTemplate", SqlSessionTemplate.class);
		Assert.assertNotNull(sqlSessionTemplate);
	}
}
