<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 결과</title>
</head>
<body>
	<h3>업로드된 파일</h3>
	<hr>
	<ul>
	<c:forEach var="file" items="${uploadFiles}">
		<li>
				${file.fileName}
				<a href="/file/download?fileName=${file.fileName}">[Download]</a>
		</li>
	</c:forEach>
	</ul>
</body>
</html>