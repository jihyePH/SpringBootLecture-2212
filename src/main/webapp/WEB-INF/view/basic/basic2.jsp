<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSP 파일 사용</title>
</head>
<body>
	<h1>데이터 전달</h1>
	<hr>
	<h3>파일 이름: ${fileName} </h3>
	<h3>전달 내용: ${message}</h3>
	<h3>과일 목록</h3>
	<ul>
	<c:forEach var="fruit" items="${fruits}">
		<li>${fruit}</li>
	</c:forEach>
	</ul>
	<img src="/img/github.png" width="256"><br>
	<h3>이미지와 같은 정적인 자료는 static 폴더에 반드시 넣어야 함</h3>
	
</body>
</html>