<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>사용자 수정</title>
</head>
<body>
 	<h3>사용자 수정</h3>
 	<hr>
 	<form action="/user/update" method="post">
 		<input type="hidden" name="uid" value="${user.uid}">
 		<table>
 		<tr>
 			<td>UID</td>
 			<td><input type="text" name="uid" value="${user.uid}" disabled></td>
 		</tr>
 		<tr>
 			<td>이름</td>
 			<td><input type="text" name="uname" value="${user.uname}"></td>
 		</tr>
 		<tr>
 			<td>이메일</td>
 			<td><input type="email" name="email" value="${user.email}"></td>
 		</tr>
 		<tr>
 			<td colspan="2"><input type="submit" name="제출"></td>
 		</tr>
 		</table>
 	</form>
</body>
</html>