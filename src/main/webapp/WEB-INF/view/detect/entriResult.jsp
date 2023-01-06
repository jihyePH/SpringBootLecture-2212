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
    <h3>ETRI 공공 인공지능 API 객체 탐지 결과</h3>
    <hr>
    <canvas id="tcanvas" width="100" height="100"></canvas>
    <script>
        let jsonStr = '${jsonResult}';
        let obj = JSON.parse(jsonStr);
        let returnData = obj.return_object.data;
        
        const canvas = document.getElementById('tcanvas');
        let ctx = canvas.getContext("2d");
        let img = new Image();
        img.src = '/upload/${fileName}';
        img.onload = function() {
            canvas.width = img.width;
            canvas.height = img.height;
            ctx.drawImage(img, 0, 0, img.width, img.height);

            ctx.strokeStyle = 'red';
            ctx.linewidth = 2;
            
            for (let data of returnData) {
            	ctx.strokeRect(data.x, data.y, data.width, data.height);
            	let label = data['class'] + ' (' + parseInt(data.confidence * 100) + '%)';
                ctx.strokeText(label, data.x + 5, data.y - 5);
            }
        }
    </script>
</body>
</html>