<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Naver Static Map</title>
</head>
<body style="margin: 40px;">
    <h3>Naver Static Map</h3>
    <hr>
    <form action="/map/staticMap" method="post">
        <table>
            <tr><td>Width</td><td><input type="text" name="width" value="600"></td></tr>
            <tr><td>Height</td><td><input type="text" name="height" value="400"></td></tr>
            <tr><td>중심좌표(위도)</td><td><input type="text" name="lat" value="37.538313"></td></tr>
            <tr><td>중심좌표(경도)</td><td><input type="text" name="lng" value="127.0824"></td></tr>
            <tr><td>줌 레벨</td><td><input type="text" name="level" value="13"></td></tr>
            <tr><td>지도 유형</td>
                <td><select name="maptype">
                    <option value="basic">일반 지도 유형</option>
                    <option value="traffic">교통 정보 지도 유형</option>
                    <option value="satellite">위성 지도 유형</option>
                    <option value="satellite_base">위성 배경 지도 유형</option>
                    <option value="terrain">지형도 유형</option>
                </select></td>
            </tr>
            <tr><td>이미지 형식</td>
                <td><input type="radio" name="format" value="jpg" checked> jpg
                    <input type="radio" name="format" value="png"> png
                    <input type="radio" name="format" value="png8"> png8
                </td>
            </tr>
            <tr><td>해상도</td>
                <td><input type="radio" name="scale" value="1"> 저해상도
                    <input type="radio" name="scale" value="2" checked> 고해상도
                </td>
            </tr>
            <tr><td>사용 언어</td>
                <td><input type="radio" name="lang" value="ko" checked> 한글
                    <input type="radio" name="lang" value="en"> 영어
                    <input type="radio" name="lang" value="ja"> 일본어
                    <input type="radio" name="lang" value="zh"> 중국어
                </td>
            </tr>
            <tr><td colspan="2"><input type="submit" value="제출"></td></tr>
        </table>
    </form>
</body>
</html>