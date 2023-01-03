<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>File Upload</title>
</head>
<body>
	<h3>File Upload</h3>
	<hr>
	<form action="/file/upload2" method="post" enctype="multipart/form-data">
		<h4>파일 선택(다중 선택 가능)</h4>
		메세지: <input type="text" name="msg"><br>
		<input type="file" name="files" multiple><br>
		<input type="submit" value="업로드">
	</form>
</body>
</html>