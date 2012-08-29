package com.exchange.service;

import java.util.List;

import com.exchange.cache.CacheKey;
import com.exchange.cache.CacheModel;
import com.exchange.dao.UserDao;
import com.exchange.domain.User;
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	private CacheModel cacheModel;
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public CacheModel getCacheModel() {
		return cacheModel;
	}
	public void setCacheModel(CacheModel cacheModel) {
		this.cacheModel = cacheModel;
	}
	@Override
	public List<User> getAllUser(int firstResult, int maxResults,
			String orderBy, boolean ascending) {
			CacheKey cacheKey=new CacheKey();
			cacheKey.update("getAllUser");
		    Object object = cacheModel.getObject(cacheKey);
		    if (object == CacheModel.NULL_OBJECT){
		    	//	This was cached, but null
		    	object = null;
		    }else if (object == null) {
		       object =userDao.getAllUser(firstResult, maxResults, orderBy, ascending);
		       cacheModel.putObject(cacheKey, object);
		    }
		    	return (List<User>)object; 
	}
}
