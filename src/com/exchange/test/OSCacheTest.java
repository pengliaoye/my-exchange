package com.exchange.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

public class OSCacheTest {
	private GeneralCacheAdministrator ads;
	@Before
	public void setUp(){
		ads=new GeneralCacheAdministrator();
	}
	@Test
	public void testPut(){
		ads.putInCache("k", 123);
	}
	//public void removeFromCache(String key){
	//ads.flushEntry("");
	//ads.removeEntry("");
	//}
	//public abstract Object getObject();覆写该方法提供第一次获取对象的能力
	@Test
	public void testGet(){
		String key="k";
		Object obj=null;
		try {
			obj=ads.getFromCache(key,3600*1000);
			//Assert.assertNotNull(obj);
		} catch (NeedsRefreshException nre) {
			/*try{
				obj=new Object();
				ads.putInCache(key, obj);
			}catch(Exception ex){
				obj=nre.getCacheContent();//取之前的
				ads.cancelUpdate(key);
			}*/
		}
		Assert.assertNotNull(obj);
	}
}
