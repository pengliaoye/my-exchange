package com.exchange.dao;

import java.util.List;

import com.exchange.domain.User;

public interface UserDao{
	public List<User> getAllUser(int firstResult, int maxResults, String orderBy, boolean ascending);
}
