<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Genie Chart</title>
</head>
<body>
	<h3>Genie Chart</h3>
	<hr>
	<table border="1">
		<tr>
			<th>순위</th><th>이미지</th><th>제목</th><th>아티스트</th><th>앨범</th>
		</tr>
	<c:forEach var="song" items="${songList}">
		<tr>
			<td>${song.rank}</td>
			<td><img src="${song.imgSrc}" height="36"></td>
			<td>${song.title}</td>
			<td>${song.artist}</td>
			<td>${song.album}</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>