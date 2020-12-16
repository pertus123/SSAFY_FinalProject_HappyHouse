<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:requestEncoding value="utf-8" />
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HappyHouse</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="${root}/css/header_footer.css">
<link rel="stylesheet" href="${root}/css/search.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
</head>
<body>
	<jsp:include page="includes/session.jsp" />
	<header><jsp:include page="includes/searchHeader.jsp" /></header>
	<div class="search" style="padding-top: 20px;">
		<div class="row">
			<div class="col-lg-12">
				<div class="row">
					<div class="col-md-5">
						<div class="row">
							<div class="search-container form-inline">
							
									<input type="hidden" name="act" id="act" value="search">
									<select id="dealType" name="dealType" style="display: none;">
										<option value="1" selected></option>
										<option value="2"></option>
										<option value="3"></option>
										<option value="4"></option>
									</select>
									<select class="custom-select" id="searchType" name="searchType">
										<option value="0" selected>동으로 검색</option>
										<option value="1">건물명으로 검색</option>
										<option value="2">역이름으로 검색</option>
									</select> 
									<input class="form-control mr-sm-3" id="keyword" name="keyword"
										type="search" placeholder="Search" aria-label="Search" onkeyup="enterkey();">
									<button type="button" id="searchbtn" onclick="search()">
										<i class="fa fa-search"></i>
									</button>
								
							</div>
						</div>
						<div class="row">
							<div id="rowbuildinglist" style="height: 300px; overflow:scroll;">
								<table class="table table-hover table-sm">
									<tbody id="searchlist">

									</tbody>
								</table>
							</div>
						</div>
						<div class="row">
							<div id="detaildiv">
								<table class="table table-sm">
									<tbody id="detailbody">

									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="col-md-7">
						<div class="nearbyinfo row">
							<div class="col-md-6"></div>

							<div class="col-md-6" id='detail'>
								<button type="button" class="btn subfuncbtn">환경정보</button>
								<button type="button" class="btn subfuncbtn" onclick='getHospital(0)'>선별 진료소</button>
								<button type="button" class="btn subfuncbtn" onclick='getHospital(1)'>안심 병원</button>
							</div>
						</div>
						<div class = "row">
						<div id="map" style=" height: 500px; margin: auto;"></div>
						</div>
					
						
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>
	<script defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCoeYF0y0MXUVo3DBdHsFn4Pescd9ZSvE4&callback=initMap"></script>

	<script>
		$(document).ready(function() {
			initMap();
			$("#dealType").val("${searchbean.getDealType()}");
			$("#searchType").val("${searchbean.getSearchType()}");
			$("#keyword").val("${searchbean.getKeyword()}");
			initSearch();
		});
		
		var multi = {
			lat : 37.527541,
			lng : 126.999239
		};
		var multimarker;
		var markers = new Array();
		var h0markers = new Array();
		var h1markers = new Array(); 
		var dealicon;
		var hos0icon;
		var hos1icon;
		var map;
		var curGungu;
		var hos0flag = false;
		var hos1flag = false;

		function initMap() {
			map = new google.maps.Map(document.getElementById('map'), {
				center: multi, zoom: 12,
				mapTypeId:google.maps.MapTypeId.ROADMAP,
				mapTypeControl:false
			});
			multimarker = new google.maps.Marker({position: multi, map: map});//////////////
			dealicon = new google.maps.MarkerImage("./img/deal.png", null, null, null, new google.maps.Size(40,40)); 
			hos0icon = new google.maps.MarkerImage("./img/hos0.png", null, null, null, new google.maps.Size(40,40)); 
			hos1icon = new google.maps.MarkerImage("./img/hos1.png", null, null, null, new google.maps.Size(40,40)); 
		}
		
		function initSearch() { // 처음
			$.ajax({
				url:'${root}/searchdata',  
				type:'GET',
				contentType:'application/json;charset=utf-8',
				dataType:'json',
				data :{
					dealType: "${searchbean.getDealType()}", 
					searchType:"${searchbean.getSearchType()}", 
					keyword:"${searchbean.getKeyword()}"
				   },
				success:function(data, status){
					$("#searchlist").empty();
					multimarker.setMap(null);
					if(data.length == 0) {
						$("#noresultp").remove();
						$("#rowbuildinglist").append("<p id='noresultp'>검색결과가 없습니다.</p>");
					} else {
						$.each(data, function(index, vo) {
							let str = "<tr>"
							+ "<td style='width:10%;'>" + (index +1) + "</td>"
							+ "<td style='width:15%;'>" + vo.dong + "</td>"
							+ "<td style='width:50%;'>" + vo.aptName + "</td>"
							+ "<td style='width:20%;'><button calss='btn' onclick=getDetail("+vo.no+")>상세정보</button> </td>" 
							+ "<td id='lat"+vo.no+"' style='display:none;'>"+vo.lat+"</td>"
							+ "<td id='lng"+vo.no+"' style='display:none;'>"+vo.lng+"</td></tr>";
							$("#searchlist").append(str);
						});
						addMarkers(data);
					}
				},
				error:function(xhr,status,error){
					console.log("상태값 : " + xhr.status + "\nHttp에러메시지 : " + xhr.responseText + "\nerror : " + error);
				}
			});
		}
		
		
		function search() { //searchdata
			$.ajax({
				url:'${root}/searchdata',  
				type:'GET',
				contentType:'application/json;charset=utf-8',
				dataType:'json',
				data :{
					dealType: $("#dealType").val(), 
					searchType:$("#searchType").val(), 
					keyword:$("#keyword").val()
				   },
				success:function(data, status){
						$("#searchlist").empty();
						if(data.length == 0) {
							$("#noresultp").remove();
							$("#rowbuildinglist").append("<p id='noresultp'>검색결과가 없습니다.</p>");
						} else {
							$.each(data, function(index, vo) {
								let str = "<tr>"
								+ "<td>" + (index + 1) + "</td>"
								+ "<td>" + vo.dong + "</td>"
								+ "<td>" + vo.aptName + "</td>"
								+ "<td><button calss='btn' onclick=getDetail("+vo.no+")>상세정보</button></td>" 
								+ "<td id='lat"+vo.no+"' style='display:none;'>"+vo.lat+"</td>"
								+ "<td id='lng"+vo.no+"' style='display:none;'>"+vo.lng+"</td></tr>";
								$("#searchlist").append(str);
							});
							addMarkers(data);
						}
					},
					error:function(xhr,status,error){
						console.log("상태값 : " + xhr.status + "\nHttp에러메시지 : " + xhr.responseText + "\nerror : " + error);
					}
			}
			);
		}
		
		function addMarkers(data){
			while (markers.length > 0) {
				markers.pop().setMap(null);
			}
			while (h0markers.length > 0) {
				h0markers.pop().setMap(null);
				hos0flag= false;
			}
			while (h1markers.length > 0) {
				h1markers.pop().setMap(null);
				hos1flag= false;
			}
			
			var tmp;
			var avglat = 0.0;
			var avglng = 0.0;
			$.each(data, function(index, vo) {
				tmp = new google.maps.Marker({
					position: new google.maps.LatLng(parseFloat(vo.lat), parseFloat(vo.lng)),
					icon: dealicon
				});
				avglat += parseFloat(vo.lat);
				avglng += parseFloat(vo.lng);
				console.log(vo);
				curGungu = vo.gunguCode;
				
				tmp.setMap(map);
				markers.push(tmp);
			});
			avglat /= markers.length;
			avglng /= markers.length;
			var center = {	lat : avglat, lng : avglng };
			
			if($("#searchType").val() == 1) {
				map.setCenter(multi);
				map.setZoom(12);
			} else {
				map.setCenter(center);
				map.setZoom(16);
			}
		}

		function getDetail(n){
			$.ajax({
				url:'${root}/detail',  
				type:'GET',
				contentType:'application/json;charset=utf-8',
				dataType:'json',
				data :{
					no:n
				   },
				success:function(data, status){	
					while (h0markers.length > 0) {
						h0markers.pop().setMap(null);
					}
					hos0flag= false;
				//	hos1flag= false;
				//	hos0flag= false;
					while (h1markers.length > 0) {
						h1markers.pop().setMap(null);
					}
					hos1flag= false;

					$("#detailbody").empty();
					let str = "<tr><td style='width:20%;'>이름</td><td>"+data.aptName+"</td></tr>"
							+ "<tr><td>주소</td><td>"+data.dong+" "+data.jibun+"번지 "+data.floor+"층</td></tr>"
							+ "<tr><td>거래금액</td><td>"+data.dealAmount+"</td></tr>"
							+ "<tr><td>건축연도</td><td>"+data.buildYear+"</td></tr>"
							+ "<tr><td>전용면적</td><td>"+data.area+"</td></tr>";
					$("#detailbody").append(str);
					zoomin(data);
					curDong = data.dong;
				},
				error:function(xhr,status,error){
					console.log("상태값 : " + xhr.status + "\nHttp에러메시지 : " + xhr.responseText + "\nerror : " + error);
				}
				}
			);
		}
		
		function zoomin(data) {
			while (markers.length > 0) {
				markers.pop().setMap(null);
			}
			
			var marker = new google.maps.Marker({
				position: new google.maps.LatLng(parseFloat($("#lat"+data.no).text()), parseFloat($("#lng"+data.no).text())),
				icon: dealicon,
				label: data.aptName,
				title: data.aptName
			});
			
			curGungu = data.gunguCode;
			
			marker.setMap(map);
			markers.push(marker);
			
			map.setCenter(marker.getPosition());
			map.setZoom(17);
		}
		/*
		function getHospital(t){
			if(t == 0){
				if(hos0flag) {
					while (h0markers.length > 0) {
						h0markers.pop().setMap(null);
					}
					hos0flag= false;
				} else {
					$.ajax({
						url:'${root}/hospital',  
						type:'GET',
						contentType:'application/json;charset=utf-8',
						dataType:'json',
						data :{
							 type: t, code: curGungu
						   },
						success:function(data, status){
							var tmp;
							$.each(data, function(index, vo) {
								tmp = new google.maps.Marker({
									position: new google.maps.LatLng(parseFloat(vo.lat), parseFloat(vo.lng)),
		
									icon: hos0icon,
									label: vo.name, 
									title: vo.name 
								});
								tmp.setMap(map);
								h0markers.push(tmp);
								map.setZoom(15);
							});
							hos0flag = true;
						},
							error:function(xhr,status,error){
								console.log("상태값 : " + xhr.status + "\nHttp에러메시지 : " + xhr.responseText + "\nerror : " + error);
							}
					}
					);
				}
			} 
			
			else {
				if (hos1flag) {
					while (h1markers.length > 0) {
						h1markers.pop().setMap(null);
					}
					hos1flag= false;
				} else {
					
					$.ajax({
						url:'${root}/hospital',  
						type:'GET',
						contentType:'application/json;charset=utf-8',
						dataType:'json',
						data :{
							 type: t, code: curGungu
						   },
						success:function(data, status){
							var tmp;
							$.each(data, function(index, vo) {
								tmp = new google.maps.Marker({
									position: new google.maps.LatLng(parseFloat(vo.lat), parseFloat(vo.lng)),
			
									icon: hos1icon,
									label: vo.name, 
									title: vo.name 
								});
								tmp.setMap(map);
								h1markers.push(tmp);
								map.setZoom(15);
							});
							hos1flag = true;
						},
							error:function(xhr,status,error){
								console.log("상태값 : " + xhr.status + "\nHttp에러메시지 : " + xhr.responseText + "\nerror : " + error);
							}
					}
					);
				}
			}
		}
		function enterkey() {
	        if (window.event.keyCode == 13) {
	             search();
	        }
	}*/
	</script>
</body>

</html>