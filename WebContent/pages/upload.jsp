<%@ include file="../inc/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="uploaderContainer">
		<div id="uploaderOverlay" style="position: absolute; z-index: 2"></div>
		<div id="selectFilesLink" style="z-index: 1">
			<a id="selectLink" href="#">Select Files</a>
		</div>
	</div>
	<div id="uploadFilesLink">
		<a id="uploadLink" href="#">Upload Files</a>
	</div>

	<div id="files">
		<table id="filenames"
			style="border-width: 1px; border-style: solid; padding: 5px;">
			<thead>
				<tr>
					<th>Filename</th>
					<th>File size</th>
					<th>Percent uploaded</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</body>
<c:import url="../inc/js.jsp"></c:import>
<script type="text/javascript" src="<c:url value="/js/pages/upload.js"/>"></script>
</html>