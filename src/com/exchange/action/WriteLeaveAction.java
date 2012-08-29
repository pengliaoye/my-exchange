package com.exchange.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class WriteLeaveAction {

	private String  resourceName;
	public String execute(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		Map<String,Object> variables=new HashMap<String,Object>();
		variables.put("owner",username);
		return null;
	}
}
