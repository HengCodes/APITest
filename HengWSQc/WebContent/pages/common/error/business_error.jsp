<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Business ERROR</title>
</head>
<body>
<div style="margin:100px auto;width:960px;">
	<h2>业务层异常</h2>
	<div th:text="${ex.message }"></div>
</div>
</body>
</html>