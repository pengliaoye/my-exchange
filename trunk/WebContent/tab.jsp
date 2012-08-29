<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<base target="mainFrame">
<title>My JSP 'main.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	border: 0;
}

ul.tab li {
	display: inline; /*float: right;*/
}

ul.tab li  a {
	display: block;
	width: 92px;
	height: 21px;
	padding: 8px 0 0 0;
	background: url(images/menuBg2.gif);
	font-weight: bold;
	text-align: center;
	text-decoration: none;
	color: #FFF;
	float: left;
}

ul.tab li a:hover,ul.tab li.select a {
	background-image: url(images/menuBg1.gif);
	color: #000;
}
</style>
<script type="text/javascript">
	function initTab(t) {
		var tab = document.getElementById(t);
		var lis = tab.getElementsByTagName("li");
		for ( var i = 0; i < lis.length; i++) {
			lis[i].nu = i;
			lis[i].onclick = function() {
				for ( var j = 0; j < lis.length; j++) {
					if (j == this.nu) {
						this.className = "select";
						document.body.focus();
					} else {
						lis[j].className = "";
					}
				}
			};
		}
	}
	window.onload = function() {
		initTab("globalNav");
	};
</script>
</head>
<body>
	<ul id="globalNav" class="tab">
		<li class="select"><a href="pages/page1.jsp">选项1</a></li>
		<li><a href="pages/page2.jsp">选项2</a></li>
		<li><a href="pages/page3.jsp">选项3</a></li>
		<li><a href="pages/page4.jsp">选项4</a></li>
	</ul>
</body>
</html>