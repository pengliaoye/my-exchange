package com.exchange.dp;

public class Singleton {   
	  
	  static class SingletonHolder {   
	    static Singleton instance = new Singleton();   
	  }   
	  
	  public static Singleton getInstance() {   
	    return SingletonHolder.instance;   
	  }   
	  
	}  

