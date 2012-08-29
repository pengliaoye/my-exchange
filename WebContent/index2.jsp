<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://ajaxtags.sourceforge.net/tags/ajaxtags"
	prefix="ajaxtags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.5.js"></script>W
<script type="text/javascript">
	var xmlhttp;
	function loadDoc(url) {
		xmlhttp = null;
		if (window.XMLHttpRequest) {
			// code for all new browsers
			xmlhttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			// code for IE5 and IE6
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		if (xmlhttp != null) {
			xmlhttp.onreadystatechange = onResponse;
			xmlhttp.open("POST", url);
			xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			xmlhttp.send("user=username&pwd=password");
			//xmlhttp.send(null);
		} else {
			alert("Your browser does not support XMLHTTP.");
		}
	}
	function onResponse() {
		if (xmlhttp.readyState == 4) {// 4 = "loaded"
			if (xmlhttp.status == 200) {// 200 = OK
				alert(xmlhttp.responseText);
				var data=eval("("+xmlhttp.responseText+")");
				alert(data.name);
			} else {
				alert("Problem retrieving data:" + xmlhttp.statusText);
			}
		}
	}
	$(document).ready(function(){
		var obj={abc:123,def:456};
		//alert($.param(obj));
	});
</script>
</head>
<body>
<ajaxtags:displayTag id="displayTagFrame">
	<display:table>
		<display:column>
		</display:column>
	</display:table>
</ajaxtags:displayTag>
</body>
</html>