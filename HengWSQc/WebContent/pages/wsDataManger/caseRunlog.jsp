<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>测试执行日志</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />

<script src="static/jqueryUI/boot.js" type="text/javascript"></script>


<style type="text/css">
html, body {
	font-size: 12px;
	padding: 0;
	margin: 0;
	border: 0;
	height: 100%;
	overflow: hidden;
}
</style>
</head>
<body>

	<form id="form1" method="post">
		<input name="id" class="mini-hidden" />
		<div style="padding-left: 11px; padding-bottom: 5px;">
			<table style="table-layout: fixed;">
				<tr>
					<td style="width: 70px;">接口名称：</td>
					<td style="width: 150px;"><div name="apiName"
							class="mini-textbox" required="true" allowInput="false" /></td>
				</tr>
				<tr>
					<td>执行结果：</td>
					<td><div name="authStatus" class="mini-textbox"
							required="true" allowInput="false" /></td>
				</tr>
			</table>
		</div>
		<fieldset style="border: solid 1px #aaa; padding: 3px;">
			<legend>Request 信息</legend>
			<div style="padding: 5px;">
				<table>

					<tr>
						<td><input name="runtimeRequest" class="mini-textarea"
							required="true" allowInput="false"
							style="width: 500px; height: 100px;" /></td>
					</tr>

				</table>
			</div>
		</fieldset>

		<fieldset style="border: solid 1px #aaa; padding: 3px;">
			<legend>Response 信息</legend>
			<div style="padding: 5px;">
				<table>

					<tr>

						<td><input name="runtimeResponse" class="mini-textarea"
							required="true" allowInput="false"
							style="width: 500px; height: 120px;" /></td>
					</tr>

				</table>
			</div>
		</fieldset>

		<fieldset style="border: solid 1px #aaa; padding: 3px;">
			<legend>MongoDb查询结果</legend>
			<div style="padding: 5px;">
				<table>

					<tr>

						<td><input name="mongoDbQueryResult" class="mini-textarea"
							required="true" allowInput="false"
							style="width: 500px; height: 120px;" /></td>
					</tr>

				</table>
			</div>
		</fieldset>
	</form>
	<script type="text/javascript">
		mini.parse();

		var form = new mini.Form("form1");

		function SaveData() {
			var o = form.getData();

			form.validate();
			if (form.isValid() == false)
				return;
		}

		////////////////////
		//标准方法接口定义
		function SetData(data) {
			if (data.action == "edit") {
				//跨页面传递的数据对象，克隆后才可以安全使用
				data = mini.clone(data);

				$.ajax({
					url : "getRunCaseLog/" + data.id,
					cache : false,
					success : function(text) {
						var o = mini.decode(text);
						form.setData(o);
						form.setChanged(false);
						//mini.getbyName("position").setValue(o.position);
					}
				});
			}
		}

		function GetData() {
			var o = form.getData();
			return o;
		}
		function CloseWindow(action) {
			if (action == "close" && form.isChanged()) {
				if (confirm("数据被修改了，是否先保存？")) {
					return false;
				}
			}
			if (window.CloseOwnerWindow)
				return window.CloseOwnerWindow(action);
			else
				window.close();
		}
		function onOk(e) {
			SaveData();
		}
		function onCancel(e) {
			CloseWindow("cancel");
		}
	</script>
</body>
</html>