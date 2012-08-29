<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="inc/include.jsp"%>
<jsp:useBean id="now" class="java.util.Date" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	org.displaytag.decorator.CheckboxTableDecorator decorator = new org.displaytag.decorator.CheckboxTableDecorator();
	decorator.setId("intid");
	pageContext.setAttribute("checkboxDecorator", decorator);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<style type="text/css" media="all">
@import url("css/screen.css");
</style>
		<link rel="stylesheet" href="css/print.css" type="text/css"
			media="print" />
		<link rel="stylesheet" href="css/common.css" type="text/css" />
		<link href="css/jquery.loadmask.css" rel="stylesheet" type="text/css" />
		<link href="css/jquery.tooltip.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.5.1.js"></script>
		<script type="text/javascript" src="js/jquery.tooltip.js"></script>
		<script type="text/javascript" src="js/jquery.bgiframe.js"></script>
		<script type="text/javascript" src="js/jquery.loadmask.js"></script>
		<script type="text/javascript" src="js/prototype.js"></script>
		<script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>
		<script type="text/javascript" src="js/overlibmws/overlibmws.js"></script>
		<script type="text/javascript" src="js/ajaxtags.js"></script>
		<!-- 
		<script type="text/javascript" src="js/jquery.form.js"></script>
		 -->
		<script type="text/javascript" src="js/ctx.js"></script>
		<script type="text/javascript">
	   jQuery(function(){
	   		// prepare Options Object 
			/*var options = { 
			    target:     '#displayTagFrame', 
			    url:        'servlet/pagination',
			    beforeSend: function(xhr){
			    	xhr.setRequestHeader("x-request-target","displayTagFrame");
			    },success:function(data){
			    	
			    }
			}; 
	  	  jQuery("#disp").ajaxForm(options);*/
	  	  jQuery("#disp").bind("submit.form-plugin",function(e){
	  	  	if (!e.isDefaultPrevented()) { // if event has been canceled, don't proceed
				e.preventDefault();
				new AjaxJspTag.OnClick({baseUrl: "servlet/pagination", eventBase: this, onComplete: postfun, onCreate: prefun, parameters: "formid={disp}", requestHeaders: ['x-request-target', 'displayTagFrame'], target: "displayTagFrame"});
			} 
	   		return false;
	  	  });
	  	  jQuery("#displayTagFrame td[title]").tooltip();
	   });
	   function query(){

	   }
	   function prefun(){
	   		jQuery("#displayTagFrame").mask("Loading...",500);
	   }
	   function postfun(){
	   		jQuery("#displayTagFrame").unmask();
	   }
</script>
	</head>

	<body>
		${now }
		<form id="disp">
			<input type="text" name="flowno" class="input" />
			<input type="text" name="abc" value="456" class="input" />
			<select name="" class="select">
				<option>
					1
				</option>
			</select>
			<input type="submit" value="提交" class="button">
		</form>
		<%
		out.print(123);
		%>
		<ajaxtags:displayTag id="displayTagFrame" parameters="formid={disp}"
			preFunction="prefun" postFunction="postfun">
			<display:table requestURI="/servlet/pagination" name="resultList"
				id="row" pagesize="10" decorator="checkboxDecorator" export="true"
				defaultsort="6" class="dataGrid multiSelect">
				<display:column title="<input type='checkbox'/>" property="checkbox"
					media="html" />
				<display:column title="序号" property="rn" />
				<display:column title="主键" property="intid" />
				<display:column title="流水号" property="strserviceflowno"
					href="www.baidu.com" paramId="intbillid" paramProperty="intid" />
				<display:column title="受理时间" property="dtoldopertime"
					format="{0,date,yyyy-MM-dd HH:mm:ss}" sortable="true"
					style="white-space: nowrap;" />
				<display:column title="用户反映内容" property="strcustomermem"
					maxLength="100" />
			</display:table>
		</ajaxtags:displayTag>
	</body>
</html>
