<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Qc Automation tool</title>
<script src="static/jqueryUI/boot.js" type="text/javascript"></script>
</head>
<body>
<style type="text/css">
html, body {
margin: 0;
padding: 0;
border: 0;
width: 100%;
height: 100%;
overflow: hidden;
}
</style>
<h1>测试结果</h1>
<div>

<div id="edListTb" style="padding: 5px;">
<label>接口名称：</label><input id="key" class="mini-textbox"
emptyText="请输入API名称" style="width: 200px;" onenter="onKeyEnter" />
<a class="mini-button" onclick="search()">查询</a>
</td> <br /> <span type="comboboxcolumn" autoShowPopup="true"
name="reusltStatus" field="reusltStatus" width="100"
allowSort="true" align="center" headerAlign="center"> <label>测试结果：</label><input
id="key2" property="editor" class="mini-combobox"
style="width: 200px;" data="reusltStatus" /> <a class="mini-button"
onclick="search2()">查询</a>
</span>
</div>

</div>
<div style="width: 100%;">
<div class="mini-toolbar" style="border-bottom: 0; padding: 0px;">
<table style="width: 100%;">
<tr>
<td style="width: 100%;"><a class="mini-button"
iconCls="icon-search" onclick="searchLog()" plain="true">查看测试执行日志</a>

<a class="mini-button" iconCls="icon-remove"
onclick="deleteCaseListResult()" plain="true">删除历史执行结果</a></td>
</tr>
</table>
</div>
</div>
<!--撑满页面-->
<div class="mini-fit">

<div id="datagrid1" class="mini-datagrid"
style="width: 100%; height: 100%;" url="getRunCaseResult"
idField="caseId" sizeList="[1000,2000,3000,5000]" allowResize="true"
pageSize="5000" allowCellEdit="true" allowCellSelect="true"
multiSelect="false" editNextOnEnterKey="true" editNextRowCell="true">
<div property="columns">
<div type="indexcolumn"></div>
<div type="checkcolumn"></div>
<div field="apiName" headerAlign="center" allowSort="true"
width="100">
接口名称 <input property="editor" class="mini-textbox"
style="width: 100%;" minWidth="30" allowInput="false" />
</div>
<div field="apiHost" headerAlign="center" allowSort="true"
width="100">
接口地址<input property="editor" class="mini-textbox"
style="width: 100%;" minWidth="30" allowInput="false" />
</div>
<!-- 
<div field="apiParameter" headerAlign="center" allowSort="true"
width="100">
请求参数 <input property="editor" class="mini-textbox"
style="width: 100%;" minWidth="30" allowInput="false" />
</div>

-->
<div field="authStatus" headerAlign="center" allowSort="true"
width="100">
执行结果<input property="editor" class="mini-textbox"
style="width: 100%;" minWidth="30" allowInput="false" />
</div>

<div field="reason" headerAlign="center" allowSort="true"
width="100">
原因 <input property="editor" class="mini-textbox"
style="width: 100%;" minWidth="30" allowInput="false" />
</div>
<div field="runTime" headerAlign="center" allowSort="true"
width="100">
执行时间<input property="editor" class="mini-textbox"
style="width: 100%;" minWidth="30" allowInput="false" />
</div>
</div>
</div>
</div>
<script type="text/javascript">
var reusltStatus = [ {
id : 'FAIL',
text : 'FAIL'
}, {
id : 'PASS',
text : 'PASS'
}];

mini.parse();

var grid = mini.get("datagrid1");
grid.load();

//////////////////////////////////////////////////////

function search() {
var key = mini.get("key").getValue();

grid.load({
key : key
});
}

function search2() {
var key = mini.get("key2").getValue();

grid.load({
key : key
});
}

function onKeyEnter(e) {
search();
}

function addRow() {
var newRow = {
name : "New Row"
};
grid.addRow(newRow, 0);

grid.beginEditCell(newRow, "text");
}
function removeRow() {
var rows = grid.getSelecteds();
if (rows.length > 0) {
grid.removeRows(rows, true);
}
}
function saveData() {

var data = grid.getChanges();
var json = mini.encode(data);

grid.loading("保存中，请稍后......");
$.ajax({
url : "saveWsDataCaseList",
data : {
data : json
},
type : "post",
success : function(text) {
grid.reload();
},
error : function(jqXHR, textStatus, errorThrown) {
alert(jqXHR.responseText);
}
});
}

grid.on("celleditenter", function(e) {
var index = grid.indexOf(e.record);
if (index == grid.getData().length - 1) {
var row = {};
grid.addRow(row);
}
});

grid.on("beforeload", function(e) {
if (grid.getChanges().length > 0) {
if (confirm("有增删改的数据未保存，是否取消本次操作？")) {
e.cancel = true;
}
}
});
//删除执行的结果
function deleteCaseListResult() {

$.get("deleteCaseRunResult", function(result) { //获取返回值
var status = result.status;
if (status == "ok") {
mini.alert("删除成功！");
grid.load();
}

}, "json"

);
}

function searchLog() {
var row = grid.getSelected();
if (row) {
mini.open({
url: "getRunCaseLog",
title: "查看执行详情", width: 570, height: 600,
onload: function () {
var iframe = this.getIFrameEl();
var data = { action: "edit", id: row.caseId };
iframe.contentWindow.SetData(data);
},
ondestroy: function (action) {
grid.reload();

}
});

} else {
alert("请选中一条记录");
}
}
</script>


</body>
</html>