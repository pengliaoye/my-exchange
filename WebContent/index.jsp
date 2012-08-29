<%@ include file="inc/include.jsp"%>
<%@ page session="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>Home Page</title>
<link rel="stylesheet" type="text/css"
	href="yui/build/cssreset/reset-min.css.css" />
<link rel="stylesheet" type="text/css"
	href="yui/build/cssfonts/fonts-min.css" />
<link rel="stylesheet" type="text/css"
	href="yui/build/cssgrids/grids-min.css" />
<link rel="stylesheet" type="text/css"
	href="yui/build/cssbase/base-min.css" />
</head>
<body>
	<div id="hd"></div>
	<div id="bd"></div>
	<div id="ft"></div>
	<c:import url="inc/js.jsp"></c:import>
	<script type="text/javascript" src="<c:url value="js/index.js"/>"></script>
</body>
</html>
