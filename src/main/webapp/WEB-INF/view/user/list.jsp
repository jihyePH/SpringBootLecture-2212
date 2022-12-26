<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>User List</title>
</head>
<body >
	<h3>사용자 리스트</h3>
	<a href="/user/register">사용자 등록</a>
	<hr>
	<table boarder="1">
	<tr>
		<th>UID</th><th>패스워드</th><th>이름</th>
		<th>email</th><th>등록일</th><th>탈퇴여부</th>
	</tr>
	<c:forEach var="user" items="${userList}">
	<tr>
		<td>${user.uid}</td>
		<td>${user.pwd}</td>
		<td>${user.uname}</td>
		<td>${user.email}</td>
		<td>${user.regDate}</td>
		<td>${user.isDeleted}</td>
	</tr>
	</c:forEach>
	</table>
</body>
</html>