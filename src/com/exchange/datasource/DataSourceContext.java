package com.exchange.datasource;

public class DataSourceContext {
	private static final ThreadLocal<String> local=new ThreadLocal<String>();
	public static void setType(String type){
		local.set(type);
	}
	public static String getType(){
		return local.get();
	}
	public static void clearType(){
		local.remove();
	}
}
