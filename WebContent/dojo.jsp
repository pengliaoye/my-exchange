<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dojo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="dojo-release-1.6.1/dijit/themes/claro/claro.css">
	<script type="text/javascript" src="dojo-release-1.6.1/dojo/dojo.js"></script>
  	<script type="text/javascript">
  		dojo.require("dijit.layout.TabContainer");
  		dojo.require("dijit.layout.ContentPane");
  		dojo.addOnLoad(function(){
  			var tc = new dijit.layout.TabContainer({
  				style:"height:100%;width:100%;"
  			},"tabContainer");  		
  			for(var i=0;i<30;i++){	
	  			var cp = new dijit.layout.ContentPane({
	  				title:"tab"+i,
	  				content:"tab"+i+" content"
	  			});  			
	  			tc.addChild(cp);  		
  			}	
			tc.startup();  			  		
  		});
  	</script>
  </head>
  
  <body class="claro">
     <div id="tabContainer"></div>
  </body>
</html>
