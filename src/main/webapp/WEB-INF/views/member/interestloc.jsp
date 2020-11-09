<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>관심 지역</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="${root}/css/header_footer.css">
<link rel="stylesheet" href="${root}/css/modifyInform.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=50448485bd3dcce296ac863f87dc45b7"></script>
</head>

<body>
	<jsp:include page="../includes/session.jsp" />
	<header><jsp:include page="../includes/userHeader.jsp" /></header>

	<div class="row">
		<div class="col-md-4">
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col"></th>
						<th scope="col">관심 지역</th>
						<th scope="col"></th>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${interestloc}" var="i" varStatus="vs">
					<tr>
						<th scope="row">${vs.count}</th>
						<td>${i}</td>
						<td><img src="${root}/img/star.PNG" class="starimg" /></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="col-md-8">
			<div class="nearbyinfo row">
				<div class="col-md-3"></div>
				<div class="col-md-3">
					<button type="button" class="btn">환경정보</button>
				</div>
				<div class="col-md-3">
					<button type="button" class="btn">상권</button>
				</div>
				<div class="col-md-3">
					<button type="button" class="btn">코로나</button>
				</div>
			</div>
			<div class="row">
				<div id="map" style="width: 100%; height: 660px;"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			level : 3
		// 지도의 확대 레벨
		};

		// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다..
		var map = new kakao.maps.Map(mapContainer, mapOption);
	</script>
</body>

</html>