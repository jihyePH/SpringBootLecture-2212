<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
	</head>
<body>
	<h3>로그인</h3>
	<hr>
	<form action="/user/login" method="post">
		UID: <input type="text" name="uid"><br>
		PWD: <input type="password" name="pwd"><br>
		<input type="submit" value="로그인">
	</form>
</body>
</html>