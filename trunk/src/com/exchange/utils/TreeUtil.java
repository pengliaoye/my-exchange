package com.exchange.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exchange.dao.BaseDao;

public class TreeUtil extends BaseDao{
	private static final Logger logger=LoggerFactory.getLogger(TreeUtil.class);
	public List<?> getNodes(String id){
		List<?> treeList=null;
		try{
			treeList=this.getSqlSessionTemplate().selectList("exchange.getdutyreason",id);
		}catch(Exception e){
			logger.error("",e);
		}
		return treeList;
	}
}
