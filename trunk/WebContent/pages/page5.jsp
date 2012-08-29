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
<base target="page6">
<title>My JSP 'main.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	border: 0;
}

ul.menu {
	position: absolute;
	top: 40px;
	left: 0;
}

ul.menu li {
	list-style-type: none; /*去掉列项前面的装饰*/
}

ul.menu li a {
	display: block;
	width: 180px;
	padding: 10px;
	background: #666;
	border-bottom: 3px solid #525252;
	color: #FFF;
	text-decoration: none;
	font-weight: bold;
}

ul.menu  li a:hover {
	background: #FF9834;
	border-right: 3px solid #333;
}

ul.menu  li ul li a {
	background: #585858;
}

ul.menu  li ul li a:hover {
	background: #666;
}
</style>
<script type="text/javascript">
	function initMenu(t) {
		var m = document.getElementById(t);
		var lis = m.getElementsByTagName("li");
		for ( var i = 0; i < lis.length; i++) {
			var uls = lis[i].getElementsByTagName("ul");
			if (uls.length != 0) {
				uls[0].style.display = "none";
				var as = lis[i].getElementsByTagName("a");
				as[0].tget = uls[0];
				as[0].onclick = function() {
					openTag(this, this.tget);
				};
			}
		}
	}
	function openTag(a, t) {
		if (t.style.display == "none") {
			t.style.display = "block";
			a.className="folder";
		} else {
			t.style.display = "none";
			a.className
		}
	}
	window.onload = function() {
		initMenu("globalNav");
	}
</script>
</head>
<body>
	<ul id="globalNav" class="menu">
		<li><a href="">菜单1</a>
			<ul>
				<li><a href="">1-1</a>
				</li>
				<li><a href="">1-2</a>
				</li>
				<li><a href="">1-3</a>
				</li>
				<li><a href="">1-4</a>
				</li>
			</ul></li>
		<li><a href="">菜单2</a>
			<ul>
				<li><a href="">2-1</a>
				</li>
				<li><a href="">2-2</a>
				</li>
				<li><a href="">2-3</a>
				</li>
				<li><a href="">2-4</a>
				</li>
			</ul>
		</li>
		<li><a href="">菜单3</a>
			<ul>
				<li><a href="">3-1</a>
				</li>
				<li><a href="">3-2</a>
				</li>
				<li><a href="">3-3</a>
				</li>
				<li><a href="">3-4</a>
				</li>
			</ul>
		</li>
		<li><a href="">菜单4</a>
			<ul>
				<li><a href="">4-1</a>
				</li>
				<li><a href="">4-2</a>
				</li>
				<li><a href="">4-3</a>
				</li>
				<li><a href="">4-4</a>
				</li>
			</ul>
		</li>
	</ul>
</body>
</html>