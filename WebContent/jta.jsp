<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ page import="javax.transaction.UserTransaction"%>
<%@ page import="javax.sql.DataSource"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.SQLException"%>
<%
	UserTransaction trans = null;
	PreparedStatement psmt = null;
	Connection conn = null;
	String sql = "insert into cc_dictcom(intid,inttype,strname) values(8888168,9090960,'test')";
	try {
		InitialContext context = new InitialContext();
		trans = (UserTransaction) context.lookup("jta/usertransaction");
		trans.begin();

		DataSource ds = (DataSource) context.lookup("jdbc/zxdb_cc_03");
		conn = ds.getConnection();
		psmt = conn.prepareStatement(sql);
		psmt.executeUpdate();

		DataSource dsr = (DataSource) context.lookup("jdbc/zxdb_cc_04");
		conn = dsr.getConnection();
		psmt = conn.prepareStatement(sql);
		psmt.executeUpdate();

		trans.commit();
	} catch (Exception e) {
		trans.rollback();
		out.print("执行出错");
	} finally {
		try {
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jta</title>
</head>
<body>

</body>
</html>