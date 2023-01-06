<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Interpark Best Seller</title>
</head>
<body>
	<h3>Interpark Best Seller</h3>
	<hr>
	<table border="1">
		<tr>
			<th>순위</th><th>이미지</th><th>제목</th><th>저자</th><th>출판사</th><th>가격</th>
		</tr>
	<c:forEach var="book" items="${bookList}">
		<tr>
			<td>${book.rank}</td>
			<td><img src="${book.imgSrc}" height="36"></td>
			<td>${book.title}</td>
			<td>${book.author}</td>
			<td>${book.company}</td>
			<td><fmt:formatNumber type="number" value="${book.price}" /></td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>