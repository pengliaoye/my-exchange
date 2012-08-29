package com.exchange.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exchange.dao.BaseDao;
import com.exchange.utils.ExtTreeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml","classpath*:applicationContext-beans.xml"})
public class BaseDaoTest {
	@Autowired
	private BaseDao baseDao;
	@Test
	public void test(){
		
	}
	@Test
	public void testSelect()
	{
		String sql="select sysdate from dual";
		List list=baseDao.excuteNativeSql(sql);
		System.out.println(list.get(0));
	}
	@Test
	public void testSelect1()
	{
		StringBuilder builder=new StringBuilder();
		builder.append("select substr(headid,1,1),substr(headid,1,3),substr(headid,1,5),t.headid,t.parentid,t.name,t.headtype\n");
		builder.append("from cc_headtype t\n");
		builder.append("order by 1,2,3");
		String sql=builder.toString();
		List<?> list=baseDao.excuteNativeSql(sql);
		Iterator<?> iterator=list.iterator();
		List<Map<String,String>> data=new ArrayList<Map<String,String>>();
		while(iterator.hasNext()){
			Map<String,String> map=new HashMap<String,String>();
			Object[] obj=(Object[])iterator.next();
			map.put("id",obj[3].toString());
			map.put("parentid", obj[4].toString());
			map.put("leaf", "0");
			data.add(map);
		}
		ExtTreeUtil extTree=new ExtTreeUtil();
		JSONObject jso=extTree.createTree(data);
		System.out.println(jso.toString());
	}
}
