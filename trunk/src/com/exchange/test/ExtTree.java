package com.exchange.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.exchange.dao.BaseDao;
import com.exchange.utils.ExtTreeUtil;

public class ExtTree {
	private BaseDao baseDao;
	
	
	public BaseDao getBaseDao() {
		return baseDao;
	}


	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}


	public String getTree()
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
		return jso.toString();
	}
}
