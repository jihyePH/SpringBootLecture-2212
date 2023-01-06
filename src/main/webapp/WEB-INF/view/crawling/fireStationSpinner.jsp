<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>FireStation</title>
	<script>
		window.onload = funtion(){
			const form = document.getElementById('form');
			form.submit();
		}
	</script>
</head>
<body>
	<h3>서울 소재 소방서 현황</h3>
	<hr>
	<img src="/img/wait.jpg">
	<form style="display: none" action="/crawling/fireStation" method="post" id="form">
		<input type="hidden" name="dummy" value="0">
	</form>
</body>
</html>