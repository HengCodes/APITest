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
	<h1>菜单路由配置管理</h1>


	<div style="width: 100%;">
		<div class="mini-toolbar" style="border-bottom: 0; padding: 0px;">
			<table style="width: 100%;">
				<tr>
					<td style="width: 100%;"><a class="mini-button"
						iconCls="icon-add" onclick="addRow()" plain="true" tooltip="增加...">增加</a>
						<a class="mini-button" iconCls="icon-remove" onclick="removeRow()"
						plain="true">删除</a> <span class="separator"></span> <a
						class="mini-button" iconCls="icon-save" onclick="saveData()"
						plain="true">保存</a></td>
					<td style="white-space: nowrap;"><input id="key"
						class="mini-textbox" emptyText="请输入菜单名称" style="width: 150px;"
						onenter="onKeyEnter" /> <a class="mini-button" onclick="search()">查询</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<!--撑满页面-->
	<div class="mini-fit">
		<div id="datagrid1" class="mini-datagrid"
			style="width: 100%; height: 100%;" url="getMenuGirdJson" idField="id"
			sizeList="[5,10,20,50]" allowResize="true" pageSize="20"
			allowCellEdit="true" allowCellSelect="true" multiSelect="true"
			editNextOnEnterKey="true" editNextRowCell="true">
			<div property="columns">
				<div type="indexcolumn"></div>
				<div type="checkcolumn"></div>
				<div field="text" headerAlign="center" allowSort="true" width="150">
					菜单名称 <input property="editor" class="mini-textbox"
						style="width: 100%;" minWidth="200" />
				</div>
				<div field="iconCls" headerAlign="center" allowSort="true"
					width="150">
					iconCls <input property="editor" class="mini-textbox"
						style="width: 100%;" minWidth="200" />
				</div>
				<div field="pid" headerAlign="center" allowSort="true" width="150">
					pid <input property="editor" class="mini-spinner"
						style="width: 100%;" minWidth="200" />
				</div>
				<div field="url" headerAlign="center" allowSort="true" width="150">
					url <input property="editor" class="mini-textbox"
						style="width: 100%;" minWidth="200" />
				</div>
				<div field="iconPosition" headerAlign="center" allowSort="true"
					width="150">
					iconPosition <input property="editor" class="mini-textbox"
						style="width: 100%;" minWidth="200" />
				</div>
				<div field="remarks" width="120" headerAlign="center"
					allowSort="true">
					备注 <input property="editor" class="mini-textarea"
						style="width: 200px;" minWidth="200" minHeight="50" />
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
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
				url : "saveMenuList",
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

	</script>


</body>
</html>