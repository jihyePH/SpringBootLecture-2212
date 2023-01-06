<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>객체 탐지</title>
</head>
<body style="margin: 40px;">
	<h3>네이버 인공지능 API 객체 탐지</h3>
    <hr>
    <form action="/detect/naver" method="post" enctype="multipart/form-data">
    	탐지할 파일: <input type="file" name="upload"> 			<!--name="upload"  -->
    	<input type="submit" value="업로드">
	</form>
</body>
</html>