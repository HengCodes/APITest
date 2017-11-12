<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接口测试管理平台</title>
<!--jQuery js-->
<script src="static/jqueryUI/boot.js" type="text/javascript"></script>
<link href="static/nh/css/main.css" rel="stylesheet" type="text/css" />
<link href="static/nh/css/home.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<!--Layout-->
	<div id="layout1" class="mini-layout"
		style="width: 100%; height: 100%;">
		<div class="header" region="north" height="70" showSplit="false"
			showHeader="false">
			<h1
				style="margin: 0; padding: 15px; cursor: default; font-family: 'Trebuchet MS', Arial, sans-serif;text-align:center;">接口测试自动化管理平台 </h1>
			<div style="position: absolute; bottom: 0; right: 0">
				<a class="sizeti">用户名:${user}</a>
				<c:if test="${user!=null}">
					<a href="loginOut" style="padding: 5px;">注销</a>
				</c:if>
			</div>

		</div>
		<div title="south" region="south" showSplit="false" showHeader="false"
			height="30">
			<div style="line-height: 28px; text-align: center; cursor: default">Copyright
				@Eric版权所有</div>

		</div>
		<div showHeader="false" region="west" width="180" maxWidth="250"
			minWidth="100">
			<!--OutlookMenu-->
			<div id="leftTree" class="mini-outlookmenu" url="getMenuJson"
				onitemselect="onItemSelect" idField="id" parentField="pid"
				textField="text" borderStyle="border:0"></div>

		</div>
		<div title="center" region="center" bodyStyle="overflow:hidden;">
			<iframe id="mainframe" frameborder="0" name="main"
				style="width: 100%; height: 100%;" border="0"></iframe>
		</div>

	</div>
	<script type="text/javascript">
        mini.parse();
        //init iframe src
        var iframe = document.getElementById("mainframe");
        iframe.src = ""
        function onItemSelect(e) {
            var item = e.item;
            iframe.src = item.url;
        }
    </script>

</body>
</html>