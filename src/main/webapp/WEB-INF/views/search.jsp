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
<link rel="stylesheet" href="./css/header_footer.css">
<link rel="stylesheet" href="./css/search.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
</head>
<body>
	<jsp:include page="session.jsp" />
	<header><jsp:include page="searchHeader.jsp" /></header>
	<div class="search" style="padding-top: 20px;">
		<div class="row">
			<div class="col-lg-12">
				<div class="row">
					<div class="col-md-5">
						<div class="row">
							<div class="search-container">
								<form class="form-inline" id="searchForm" method="post" action="${root}/main">
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
										type="search" placeholder="Search" aria-label="Search">
									<button type="button" id="searchbtn" onclick="search()">
										<i class="fa fa-search"></i>
									</button>
								</form>
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
						<div class="row">
							<div id="map" style="width: 100%; height: 500px; margin: auto;"></div>
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
		
	/*	$(document).ready(function() {
			$.ajax({
				url:'${root}/house/search',  
				type:'GET',
				contentType:'application/json;charset=utf-8',
				dataType:'json',
				success:function(list) {
					makeList(list);
				},
				error:function(xhr,status,error){
					console.log("상태값 : " + xhr.status + "\nHttp에러메시지 : " + xhr.responseText + "\nerror : " + error);
				}
			});
			
			$("#searchbtn").click(function() {
				$.ajax({
					url:'${root}/house/searchdata',  
					type:'GET',
					contentType:'application/json;charset=utf-8',
					dataType:'json',
					success:function(list) {
						makeList(list);
					},
					error:function(xhr,status,msg){
						console.log("상태값 : " + status + " Http에러메시지 : "+msg);
					}
				});
			});
		}
		
	
	function makeList(list) {
			$("#searchlist").empty();
			if(list.length == 0) {
				$("#noresultp").remove();
				$("#rowbuildinglist").append("<p id='noresultp'>검색결과가 없습니다.</p>");
			}
			else{
				$(list).each(function(index, user) {
					let str = "<tr id=\"view_" + user.no + "\" class=\"view\" data-id=\"" + user.no + "\">"
					+ "	<td>" + index + "</td>"
					+ "	<td>" + user.dong + "</td>" // pw
					+ "	<td>" + user.aptName + "</td>" // name
					+ "	<td><button type=\"button\" class=\"modiBtn btn btn-outline-primary btn-sm\">상세정보</button> "
					+ "		<button type=\"button\" class=\"delBtn btn btn-outline-danger btn-sm\">삭제</button></td>"
					+ "</tr>"
					+ "<tr id=\"mview_" + user.userid + "\" data-id=\"" + user.userid + "\" style=\"display: none;\">"
					+ "	<td>" + user.userid + "</td>"
					+ "	<td><input type=\"text\" name=\"userpwd\" id=\"userpwd" + user.userid + "\" value=\"" + user.userpwd + "\"></td>"
					+ "	<td>" + user.username + "</td>"
					+ "	<td><input type=\"text\" name=\"email\" id=\"email" + user.userid + "\" value=\"" + user.email + "\"></td>"
					+ "	<td><input type=\"text\" name=\"address\" id=\"address" + user.userid + "\" value=\"" + user.address + "\"></td>" 
					+ "	<td>" + user.joindate + "</td>"
					+ "	<td><button type=\"button\" class=\"modifyBtn btn btn-primary btn-sm\">수정</button> "
					+ "		<button type=\"button\" class=\"cancelBtn btn btn-danger btn-sm\">취소</button></td>"
					+ "</tr>";
					$("#searchlist").append(str);
				});//each
			}
		}*/
		
		
		$("#deal1btn").click(function() {
			$("#dealType").val("1");
		});

		$("#deal2btn").click(function() {
			$("#dealType").val("2");
		});

		$("#deal3btn").click(function() {
			$("#dealType").val("3");
		});

		$("#deal4btn").click(function() {
			$("#dealType").val("4");
		});

		$("#detailbtn").click(function() {
			$(".detaildiv").append("<div>안녕</div>");
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
				center: multi, zoom: 12
			});
			multimarker = new google.maps.Marker({position: multi, map: map});
			dealicon = new google.maps.MarkerImage("./img/deal.png", null, null, null, new google.maps.Size(40,40)); 
			hos0icon = new google.maps.MarkerImage("./img/hos0.png", null, null, null, new google.maps.Size(40,40)); 
			hos1icon = new google.maps.MarkerImage("./img/hos1.png", null, null, null, new google.maps.Size(40,40)); 
		}
		
		function initSearch() { // 처음
			//alert("search하니?!");
			$.ajax({
				url:'${root}/house/searchdata',  
				type:'GET',
				
				dealType: "${searchbean.getDealType()}", 
				searchType:"${searchbean.getSearchType()}", 
				keyword:"${searchbean.getKeyword()}",
				
				contentType:'application/json;charset=utf-8',
				dataType:'json',
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
						});//each
						addMarkers(data);
					}
				},//function
				error:function(xhr,status,error){
					console.log("상태값 : " + xhr.status + "\nHttp에러메시지 : " + xhr.responseText + "\nerror : " + error);
				}
				}
			);//get
		}
		
		
		function search() { //searchdata
			//alert("search하니?!");
			$.ajax({
				url:'${root}/house/searchdata',  
				type:'GET',
				
				dealType: "${searchbean.getDealType()}", 
				searchType:"${searchbean.getSearchType()}", 
				keyword:"${searchbean.getKeyword()}",
				
				contentType:'application/json;charset=utf-8',
				dataType:'json',
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
							});//each
							addMarkers(data);
						}
					},//function
					error:function(xhr,status,error){
						console.log("상태값 : " + xhr.status + "\nHttp에러메시지 : " + xhr.responseText + "\nerror : " + error);
					}
			}
			);//get
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
				//console.log(vo.lat + " " + vo.lng);
				//console.log(avglat + " " + avglng);
				
				curGungu = vo.code;
				
				tmp.setMap(map);
				markers.push(tmp);
			});
			avglat /= markers.length;
			avglng /= markers.length;
			var center = {	lat : avglat, lng : avglng };
			//console.log(center);
			
			if($("#searchType").val() == 1) {
				map.setCenter(multi);
				map.setZoom(12);
			} else {
				map.setCenter(center);
				map.setZoom(16);
			}
		}

		function getDetail(n){
			//alert(n);
			$.get("${root}/main"
					,{act:"detail", no: n}
					,function(data, status){
						$("#detailbody").empty();
						let str = "<tr><td style='width:20%;'>이름</td><td>"+data.aptName+"</td></tr>"
								+ "<tr><td>주소</td><td>"+data.dong+" "+data.jibun+"번지 "+data.floor+"층</td></tr>"
								+ "<tr><td>거래금액</td><td>"+data.dealAmount+"</td></tr>"
								+ "<tr><td>건축연도</td><td>"+data.buildYear+"</td></tr>"
								+ "<tr><td>전용면적</td><td>"+data.area+"</td></tr>";
						$("#detailbody").append(str);
						zoomin(data);
						curDong = data.dong;
					}//function
					, "json"
			);//get
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
			
			curGungu = data.code;
			
			marker.setMap(map);
			markers.push(marker);
			
			map.setCenter(marker.getPosition());
			map.setZoom(17);
		}
		
		function getHospital(t){
			if(t == 0){
				if(hos0flag) {
					while (h0markers.length > 0) {
						h0markers.pop().setMap(null);
					}
					hos0flag= false;
				} else {
					$.get("${root}/main"
							,{act:"hospital", type: t, code: curGungu}
							,function(data, status){
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
							}//function
							, "json"
					);//get
				}
			} else {
				if (hos1flag) {
					while (h1markers.length > 0) {
						h1markers.pop().setMap(null);
					}
					hos1flag= false;
				} else {
					$.get("${root}/main"
							,{act:"hospital", type: t, code: curGungu}
							,function(data, status){
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
							}//function
							, "json"
					);//get
				}
			}
		}
	</script>
</body>

</html>