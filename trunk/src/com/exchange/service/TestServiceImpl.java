package com.exchange.service;

import java.util.List;

import com.exchange.dao.BaseDao;
import com.exchange.dao.TestDaoImpl;

public class TestServiceImpl implements TestService{
	private TestDaoImpl testDao;

	public TestDaoImpl getTestDao() {
		return testDao;
	}
	public void setTestDao(TestDaoImpl testDao) {
		this.testDao = testDao;
	}
	@Override
	public void testService() throws Exception {
		testDao.baseDaoTest();
	}

	@Override
	public List selectList(Object object) throws Exception {
		String statement="";
		List list=testDao.selectList(statement);
		return list;
	}	
	
}
