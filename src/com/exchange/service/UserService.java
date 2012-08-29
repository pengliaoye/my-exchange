package com.exchange.service;

import java.util.List;

import com.exchange.domain.User;

public interface UserService {
	public List<User> getAllUser(int firstResult, int maxResults, String orderBy, boolean ascending);
}
