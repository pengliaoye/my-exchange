package com.exchange.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exchange.domain.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public List<User> getAllUser(int firstResult, int maxResults,
			String orderBy, boolean ascending) {
			// TODO Auto-generated method stub
		DetachedCriteria criteria=DetachedCriteria.forClass(User.class);
		if(StringUtils.isNotBlank(orderBy)){
			criteria.addOrder(ascending==true?Order.asc(orderBy):Order.desc(orderBy));
		}
		List list=this.getHibernateTemplate().findByCriteria(criteria,firstResult,maxResults);
		return list;
	}

}
