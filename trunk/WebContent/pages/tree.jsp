<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="../css/ext-all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/ext-base.js"></script>
<script type="text/javascript" src="../js/ext-all.js"></script>
<script type="text/javascript" src="../js/DwrTreeLoader.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/dwr/interface/treeUtil.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript">
	Ext.onReady(function() {
		var rootNode = new Ext.tree.AsyncTreeNode( {
			id : "-1",
			text : "Invisible root",
			hasChildren : true
		});
		var tree = new Ext.tree.TreePanel( {
			el : "tree-ct",
			width : 552,
			autoHeight : true,
			rootVisible : false,
			autoScroll : true,
			title : "Folders & Leaves",
			loader : new Ext.ux.DWRTreeLoader( {
				dwrCall : treeUtil.getNodes
			}),
			root : rootNode,
			listeners : {
				dblclick : getNodeDblClick
			}
		});
		tree.render();
		function getNodeDblClick() {

		}
	});
	Ext.BLANK_IMAGE_URL = '../images/default/s.gif';
</script>
</head>
<body>
<div id="loading"></div>
<div id="tree-ct" style="overflow: auto;"></div>
</body>
</html>