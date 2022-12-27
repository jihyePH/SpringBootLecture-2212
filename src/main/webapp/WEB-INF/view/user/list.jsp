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
	<a href="/user/logout">로그아웃</a>
	${sessionUname}님 환영합니다.  ${uname }
	<hr>
	<table boarder="1">
	<tr>
		<th>UID</th><th>패스워드</th><th>이름</th>
		<th>email</th><th>등록일</th><th>탈퇴여부</th><th>액션</th>
	</tr>
	<c:forEach var="user" items="${userList}">
	<tr>
		<td>${user.uid}</td>
		<td>${user.pwd}</td>
		<td>${user.uname}</td>
		<td>${user.email}</td>
		<td>${user.regDate}</td>
		<td>${user.isDeleted}</td>
		<td>
			<button onclick="location.href='/user/update/${user.uid}'">수정</button>
			<button onclick="location.href='/user/delete/${user.uid}'">삭제</button>
		</td>
	</tr>
	</c:forEach>
	</table>
</body>
</html>