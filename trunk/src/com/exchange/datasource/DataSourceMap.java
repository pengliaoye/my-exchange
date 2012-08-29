package com.exchange.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jndi.JndiTemplate;

public class DataSourceMap implements FactoryBean<Map<Object,Object>>,InitializingBean{
	private static final Logger log=LoggerFactory.getLogger(DataSourceMap.class);
	
	private String keys;
	private Map<Object,Object> map=new HashMap<Object,Object>();
	private JndiTemplate jndiTemplate=null;
	
	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}


	public JndiTemplate getJndiTemplate() {
		return jndiTemplate;
	}

	public void setJndiTemplate(JndiTemplate jndiTemplate) {
		this.jndiTemplate = jndiTemplate;
	}

	@Override
	public Map<Object,Object> getObject() throws Exception {
		// TODO Auto-generated method stub
		return this.map;
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return this.map.getClass();
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		String[] names=this.keys.split(",");
		for(int i=0;i<names.length;i++){
			Object obj=getDataSource(names[i]);;
			if(obj==null){
				log.error("Name {} is not bound in this Context",names[i]);
				continue;
			}
			map.put(names[i],obj);
		}
	}
	private Object getDataSource(String jndiName){
		Object object=null;
		object=lookUpDataSource(jndiName);
		if(object==null){
			if (jndiName.startsWith("java:comp/env/")) {
				String jndiNameAlias = jndiName.substring("java:comp/env/"
						.length());
				object = this.lookUpDataSource(jndiNameAlias);
				if (object != null) {
					return object;
				}
			} else {
				String jndiNameAlias = "java:comp/env/" + jndiName;
				object = this.lookUpDataSource(jndiNameAlias);
				if (object != null) {
					return object;
				}
			}
		}
		return object;
	}
	private Object lookUpDataSource(String jndiName){
		Object obj=null;
		try {
			obj = jndiTemplate.lookup(jndiName);
		} catch (NamingException e) {
			// unhandled exception
		}
		return obj;
	}
}
