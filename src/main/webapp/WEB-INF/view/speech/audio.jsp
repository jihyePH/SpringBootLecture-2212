<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/77ad8525ff.js" crossorigin="anonymous"></script>
    <title>음성 인식</title>
    <style>
        td  { text-align: center; }
    </style>
</head>
<body style="margin: 40px;">
    <h3>음성 인식(녹음)</h3>
    <hr>
    <form action="/speech/etri" method="post">
        <table class="table table-borderless">
            <tr>
                <td>언어 선택</td>
                <td>
                    <input type="radio" name="language" value="korean" checked> 한국어
                    <input type="radio" name="language" value="english"> 영어
                    <input type="radio" name="language" value="japanese"> 일본어
                    <input type="radio" name="language" value="chinese"> 중국어
                    <input type="radio" name="language" value="french"> 프랑스어
                    <input type="radio" name="language" value="spanish"> 스페인어
                </td>
            </tr>
            <tr><td> </td><td> </td></tr>
            <tr>
                <td colspan="2">
                    <button id="record" class="btn btn-danger me-2">녹음</button>
                    <span id="mic" class=""></span>
                    <button id="stop" class="btn btn-dark disabled">정지</button>
                </td>
            </tr>
            <tr>
                <td colspan="2" id="td1"></td>      <!-- 오디오 컨트롤 -->
            </tr>
            <tr>
                <td colspan="2" id="td2"></td>      <!-- 제출 버튼 -->
            </tr>
        </table>
    </form>
    <script src="/js/audio.js?q=1"></script>		<!-- 캐쉬에 저장된 JS 코드 대신에 최신 코드를 반영 -->
</body>
</html>