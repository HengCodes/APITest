<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Query Info</title>
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
<h1>测试数据</h1>
<div>

<div id="edListTb" style="padding: 5px;">
<label>接口名称：</label><input id="key" class="mini-textbox"
emptyText="请输入接口名称" style="width: 200px;" onenter="onKeyEnter" /> <a
class="mini-button" onclick="search()" >查询</a>
</td> <br /> <label>执行环境：</label><input id="combo1" class="mini-combobox"
style="width: 200px;" textField="envName" valueField="address"
emptyText="请选择..." url="getEnvInfoJson" required="true"
allowInput="true" showNullItem="true" nullItemText="请选择..." /> <a
class="mini-button" onclick="cutmuchCaseList()" >执行</a>
</div>

</div>
<div style="width: 100%;">
<div class="mini-toolbar" style="border-bottom: 0; padding: 0px;">
<table style="width: 100%;">
<tr>
<td style="width: 100%;"><a class="mini-button"
iconCls="icon-add" onclick="addRow()" plain="true" tooltip="Add...">增加用例</a>
<a class="mini-button" iconCls="icon-remove" onclick="removeRow()"
plain="true">删除用例</a> <span class="separator"></span> <a
class="mini-button" iconCls="icon-save" onclick="saveData()"
plain="true">保存</a></td>
</tr>
</table>
</div>
</div>
<!--撑满页面-->
<div class="mini-fit">

<div id="datagrid1" class="mini-datagrid"
style="width: 100%; height: 100%;" url="getWsDataCaseGirdJson"
idField="id" sizeList="[1000,2000,3000,5000]" allowResize="true"
pageSize="5000" allowCellEdit="true" allowCellSelect="true"
multiSelect="true" editNextOnEnterKey="true" editNextRowCell="true">
<div property="columns">
<div type="indexcolumn"></div>
<div type="checkcolumn"></div>
<div field="apiName" headerAlign="center" allowSort="true"
width="300">
接口名称 <input property="editor" class="mini-textbox"
style="width: 100%;" minWidth="30" />
</div>
<div field="apiHost" headerAlign="center" allowSort="true"
width="200">
接口地址 <input property="editor" class="mini-textbox"
style="width: 100%;" minWidth="30" />
</div>
<!--ComboBox：本地数据-->
<div type="comboboxcolumn" autoShowPopup="true" name="apiType"
field="apiType" width="100" allowSort="true" align="center"
headerAlign="center">
请求方式 <input property="editor" class="mini-combobox"
style="width: 100%;" data="apiType" />
</div>

<div field="apiParameter" headerAlign="center" allowSort="true"
width="600">
请求参数 <input property="editor" class="mini-textarea"
style="width: 100%;" minWidth="30" />
</div>

<!--ComboBox：本地数据-->
<div type="comboboxcolumn" autoShowPopup="true" name="parameterType"
field="parameterType" width="100" allowSort="true" align="center"
headerAlign="center">
参数格式 <input property="editor" class="mini-combobox"
style="width: 100%;" data="parameterType" />
</div>
<div field="expectRCode" headerAlign="center" allowSort="true"
width="100">
返回状态码 <input property="editor" class="mini-spinner"
style="width: 100%;" minWidth="30" minValue="100" maxValue="550"/>
</div>
<div field="expectResult" headerAlign="center" allowSort="true"
width="100">
预期结果 <input property="editor" class="mini-textarea"
style="width: 100%;" minWidth="30" />
</div>
<div field="mongoDbQuery" headerAlign="center" allowSort="true"
width="600">
mongoDBQuery查询 <input property="editor" class="mini-textarea"
style="width: 100%;" minWidth="30" />
</div>
<div field="apiDese" headerAlign="center" allowSort="true"
width="100">
备注<input property="editor" class="mini-textbox"
style="width: 100%;" minWidth="30" />
</div>

<!--ComboBox：本地数据-->
<div type="checkboxcolumn" field="dependStatus" trueValue="1"
falseValue="0" width="60" headerAlign="center">是否有依赖接口</div>
<div field="depend" headerAlign="center" allowSort="true"
width="100">
依赖关系 <input property="editor" class="mini-spinner"
style="width: 100%;" minWidth="30" />
</div>

</div>
</div>
</div>
<script type="text/javascript">
var apiType = [ {
id : 'GET',
text : 'GET'
}, {
id : 'POST',
text : 'POST'
}, {
id : 'DELETE',
text : 'DELETE'
}, {
id : 'UPDATE',
text : 'UPDATE'
} ];
var parameterType = [ {
id : 'JSON',
text : 'JSON'
}, {
id :'数组',
text : '数组'
}, {
id : '键值对',
text : '键值对'
} ];

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

function cutmuchCaseList() {
var testEnv = mini.get("combo1").getValue();
var testText = mini.get("combo1").getText();
var fronId = grid.getSelecteds();//获取被选中的测试用例

if (testEnv == "") {
mini.alert("请选择测试地址！");
} else if (fronId == "") {
mini.alert("请选择测试数据！");
}else {
	var caseId = new Array();
	for (var i = 0; i < fronId.length; i++) {
	caseId.push(fronId[i].caseId);
	}
$.ajax({
type : "POST",
url : "runMuchCase",
data : {
"caseId" : caseId,
"testEnv" : testEnv,
"testText":testText
},
dataType : 'json',
traditional : true,
//async: false, //同步请求，默认情况下是异步（true）
beforeSend : function() {
mini.alert("测试执行中......");
},
success : function(msg) {
if (msg.status == "ok") {
//alert("测试用例执行完成：通过"+msg.pass+"个,失败"+msg.fail+"个");
mini.alert("测试执行完成......");
}
}
});
}
}
</script>


</body>
</html>