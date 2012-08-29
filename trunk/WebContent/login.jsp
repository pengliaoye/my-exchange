<%@ include file="inc/include.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
<link rel="stylesheet" type="text/css"
	href="yui/build/cssreset/reset-min.css.css" />
<link rel="stylesheet" type="text/css"
	href="yui/build/cssfonts/fonts-min.css" />
<link rel="stylesheet" type="text/css"
	href="yui/build/cssgrids/grids-min.css" />
<link rel="stylesheet" type="text/css"
	href="yui/build/cssbase/base-min.css" />
</head>
<body onload='document.getElementById("f").j_username.focus();'>
	<%-- this form-login-page form is also used as the
         form-error-page to ask for a login again.
         --%>
	<c:if test="${not empty param.login_error}">
		<span>Your login attempt was not successful, try again.<br />
			<br /> Reason: <c:out
				value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
		</span>
	</c:if>

	<form id="f" action="<c:url value='j_spring_security_check'/>"
		method="post">
		<table>
			<tr>
				<td><s:text name="login.username" />:</td>
				<td><input type='text' id="j_username" name='j_username'
					value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>' /></td>
			</tr>
			<tr>
				<td><s:text name="login.password" />:</td>
				<td><input type='password' id="j_password" name='j_password' /></td>
			</tr>
			<tr>
				<td><input type="checkbox" name="_spring_security_remember_me" /></td>
				<td>Don't ask for my password for two weeks</td>
			</tr>

			<tr>
				<td colspan='2'><input name="submit" type="submit"
					class="button" /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="reset" type="reset" class="button" /></td>
			</tr>
		</table>
	</form>
	<c:import url="inc/js.jsp"></c:import>
	<script type="text/javascript" src="<c:url value='/js/login.js'/>"></script>
</body>
</html>