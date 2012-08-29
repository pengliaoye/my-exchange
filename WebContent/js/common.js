// 去空格
String.prototype.trim = function() {
	return this.replace(/^\s+/g, "").replace(/\s+$/g, "");
};
// 获得字节长度
String.prototype.getBytes = function() {
	var cArr = this.match(/[^\x00-\xff]/ig);
	return this.length + (cArr == null ? 0 : cArr.length);
};
// 交替换行
function changetr() {
	$(".tabList").find("tr").each(function(i) {
		this.className = [ '', 'trAlt' ][i % 2];
	});
	$(".tabList tbody tr").hover(function() {
		$(this).addClass("trOver");
	}, function() {
		$(this).removeClass("trOver");
	});
}
// 跳转
function go(url) {
	window.location.href = url;
}
// 返回
function goback() {
	window.history.back();
}
// 显示隐藏
function displayOrNo(obj) {
	var obj = document.getElementById(obj);
	obj.style.display = (obj.style.display == '') ? 'none' : '';
}
function initOptions(eid, data) {
	var element = document.getElementById(eid);
	for ( var i = 0; i < data.length; i++) {
		element.options.add(new Option(data.name, data.value, false, false));
	}
}
function initOptions(id, data, isfire) {
	var el = document.getElementById(id);
	el.options.length = 0;
	for ( var i = 0; i < data.length; i++) {
		if (i = 0) {
			var option = new Option(data.name, data.id, true, false);
		} else {
			var option = new Option(data.name, data.id, false, false);
		}
		el.options[el.options.length] = option;
	}
}
// 删除所有的Option
function removeAll(eid) {
	var obj = document.getElementById(eid);
	obj.options.length = 0;
}
// 全选
function selectAll() {
	$("tbody[id='dataTableBody'] input[type='checkbox']").attr("checked", true);
}
// 反选
function backSelect() {
	$("tbody[id='dataTableBody'] input[type='checkbox']:checked").attr(
			"checked", false);
}