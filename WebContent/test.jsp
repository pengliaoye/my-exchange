<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="javax.naming.*" %>
<%@page import="javax.sql.DataSource" %>
<%
try {
Context ctx = new InitialContext();
DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oraclejndi");
Connection conn = ds.getConnection();
Statement st = conn.createStatement();
ResultSet rs = st.executeQuery("select sysdate from dual");
while(rs.next()){
out.println(rs.getString(1) + "<br>");
}
} catch (NamingException e) {
e.printStackTrace();
} catch (SQLException e) {
e.printStackTrace();
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function test(){
		window.location.href="test.action";
	}
</script>
<title>测试页面</title>
</head>
<body>
	<input type="button" value="测试" onclick="test()">
</body>
</html>