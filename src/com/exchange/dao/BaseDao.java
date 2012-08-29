package com.exchange.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import oracle.sql.BLOB;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.ibatis.session.RowBounds;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.mybatis.spring.SqlSessionTemplate;

import com.exchange.datasource.DataSourceContext;

public abstract class BaseDao {

	private static final Logger log = LoggerFactory.getLogger(BaseDao.class);

	private HibernateTemplate hibernateTemplate;
	private SqlSessionTemplate sqlSessionTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public void baseDaoTest() throws SQLException {
		DataSourceContext.setType("jdbc/zxdb_cc_00");
		System.out.println(this.getSqlSessionTemplate()
				.getConnection());
		Object obj = this.getSqlSessionTemplate().selectOne("getTime");
		System.out.println(obj.toString());
		log.error("sdf");
	}

	public int insert(String statement) {
		int i = this.getSqlSessionTemplate().insert(statement);
		return i;
	}

	public List selectList(String statement) {
		List list = this.getSqlSessionTemplate().selectList(statement);
		String pageIndex="1";
		String pageSize="10";
		int offset=NumberUtils.toInt(pageIndex, 1);
		int limit=NumberUtils.toInt(pageSize,10);
		RowBounds rowBound=new RowBounds(offset,limit);
		this.getSqlSessionTemplate().selectList(statement, rowBound);
		return list;
	}

	public void save(Object object) {
		this.getHibernateTemplate().save(object);
	}

	public void update(final String sql,final  Object[] parameters) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Work work = new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				Blob blob=connection.createBlob();
				OutputStream os=blob.setBinaryStream(0);
				BufferedOutputStream bos=new BufferedOutputStream(os);
				InputStream is = (InputStream) parameters[0];
				BufferedInputStream bis = new BufferedInputStream(is);
				byte[] b=new byte[4096];
				int i=-1;
				try {
					while((i=bis.read(b))!=-1){
						bos.write(b);
					}
					bos.flush();
					PreparedStatement pstmt = connection.prepareStatement(sql);
					pstmt.setBlob(0, blob);
					pstmt.executeUpdate();
					bis.close();
					bos.close();
					pstmt.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				/*String sql = "insert into testlob(b) values()";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.executeUpdate();
				String strSql = "select b from testlob for update";
				pstmt = connection.prepareStatement(strSql);
				ResultSet rs = pstmt.executeQuery();
				BLOB blob = (BLOB) rs.getBlob("b");
				OutputStream os = blob.setBinaryStream(0);
				BufferedOutputStream bos = new BufferedOutputStream(os);
				InputStream is = (InputStream) parameters[0];
				BufferedInputStream bis = new BufferedInputStream(is);
				byte[] b = new byte[2048];
				int i = -1;
				try {
					while ((i = bis.read(b)) != -1) {
						bos.write(b, 0, i);
					}
					bos.flush();
					bis.close();
					bos.close();
					pstmt.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		};
		session.doWork(work);
		transaction.commit();
	}
	public List select(String hql)
	{
		this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("").setFirstResult(1);
		this.hibernateTemplate.setMaxResults(15);
		return this.hibernateTemplate.find(hql);
	}
	public List excuteNativeSql(String sql,int firstResult,int maxResult)
	{
		StringBuilder pageSql=new StringBuilder();
		pageSql.append("select * from (select t.*,rownum rn	  from (");
		pageSql.append(sql);
		pageSql.append(") t where rownum<="+maxResult+") where rn>="+firstResult+"");
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		SQLQuery query=session.createSQLQuery(pageSql.toString());
		List list=query.list();
		return list;
	}
	public List excuteNativeSql(String sql){
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		SQLQuery query=session.createSQLQuery(sql.toString());
		List list=query.list();
		return list;
	}
}
