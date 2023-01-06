<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Genie Chart</title>
	<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
    <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
    <style>
        th, td {
            text-align: center;
        } 
    </style>
    <script>
        $(document).ready(function() {
            $('#example').DataTable({
                info: false,
                searching: true,
                paging: true,
                ordering: false,
                //order: [[ 0, "asc" ]],
                columnDefs: [{
                    "targets": [ 0 ],
                    "visible": false,
                    "searchable": false
                }],
                language: {
                    thousands: ',',
                    search: '',
                    searchPlaceholder: "검색어",
                    paginate: {
                        first: '처음',
                        previous: '이전',
                        next: '다음',
                        last: '마지막',
                    }
                },
                oLanguage: {
                    sLengthMenu: "_MENU_",
                },
                "lengthMenu": [[10, -1], ["10개씩 보기", "모두 보기"]]
            });
        } );
    </script>
</head>
<body>
	<h3>Genie Chart</h3>
	<hr>
	<table id="example" class="display compact" style="width:100%;">
		<thead>
			<tr>
				<th>index</th><th>순위</th><th>이미지</th><th>제목</th><th>아티스트</th><th>앨범</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="song" items="${songList}" varStatus="loop">
			<tr>
				<td>${loop.index}</td>
				<td>${song.rank}</td>
				<td><img src="${song.imgSrc}" height="36"></td>
				<td>${song.title}</td>
				<td>${song.artist}</td>
				<td>${song.album}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
    
</body>
</html>