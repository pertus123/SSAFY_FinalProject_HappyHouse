<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Happy House</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="${root}/css/main.css">
<link rel="stylesheet" href="${root}/css/header_footer.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
	<jsp:include page="includes/session.jsp" />
	<header><jsp:include page="includes/mainHeader.jsp" /></header>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<div class="row searchrow">
					<div class="btn-group btn-group-lg" role="group">
						<button class="btn btn-secondary" id='deal1btn' type="button">아파트
							매매</button>
						<button class="btn btn-secondary" id='deal2btn' type="button">아파트
							전월세</button>
						<button class="btn btn-secondary" id='deal3btn' type="button">주택
							매매</button>
						<button class="btn btn-secondary" id='deal4btn' type="button">주택
							전월세</button>
					</div>
					<div class="form-div">
						<form class="form-inline" id="searchForm" method="post" action="${root}/main">
							<div class="col-auto my-1">
								<input type="hidden" name="act" id="act" value="search">
								<select id="dealType" name="dealType" style="display: none;">
									<option value="1" selected></option>
									<option value="2"></option>
									<option value="3"></option>
									<option value="4"></option>
								</select> 
								<select class="custom-select mr-sm-2" id="searchType" name="searchType">
									<option value="0" selected>동으로 검색</option>
									<option value="1">건물명으로 검색</option>
									<option value="2">역이름으로 검색</option>
								</select>
							</div>
							<input class="form-control mr-sm-3" id="keyword" name="keyword"
								type="search" placeholder="Search" aria-label="Search">
							<button class="btn my-2 my-sm-0" type="submit" >검색</button>
						</form>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="carousel slide" id="carousel-166681">
							<ol class="carousel-indicators">
								<li data-slide-to="0" data-target="#carousel-166681"
									class="active"></li>
								<li data-slide-to="1" data-target="#carousel-166681"></li>
								<li data-slide-to="2" data-target="#carousel-166681"></li>
							</ol>
							<div class="carousel-inner">
								<div class="carousel-item">
									<img class="d-block w-100" alt="room" src="${root}/img/slide1.jpg" />
									<div class="carousel-caption">
										<h4>어떤 집에서 살고 싶나요?</h4>
										<p>집은 사람이나 동물이 거주하기 위해 지은 건물로, 보통 벽과 지붕이 있으며, 추위와 더위, 비바람을
											막아 줍니다. 좁은 뜻으로는 인간이 사는 집, 곧 주택만을 가리키기도 한다. 때때로는 민가라는 이칭을 가지고
											있습니다.</p>
									</div>
								</div>
								<div class="carousel-item">
									<img class="d-block w-100" alt="room" src="./img/slide2.jpg" />
									<div class="carousel-caption">
										<h4>주택임대차계약서 작성 시 알아두면 좋은 법률 상식</h4>
										<p>마음에 드는 집을 구했다면, 이제 계약서를 써야 할 때! 하지만 주택임대차계약서... 이름만 들어도
											어렵다구요? 주택임대차계약서 어려워마시고 게시물만 따라서 꼼꼼하게 확인만 하면 된답니다!</p>
									</div>
								</div>
								<div class="carousel-item active">
									<img class="d-block w-100" alt="room" src="./img/slide3.jpg" />
									<div class="carousel-caption">
										<h4>Ssafy Happy House TV 광고 이벤트!</h4>
										<p>지금은 TV 광고 준비가 막바지에 이르렀는데요, 준비하면서 저희 스스로도 감탄하는 정말 역대급!
											TV 광고입니다. 이렇게 좋은 광고를 저희만 볼 수 없어, 여러 차례 노출시킬 예정이니 조금만 기다려주세요~
										</p>
									</div>
								</div>
							</div>
							<a class="carousel-control-prev" href="#carousel-166681"
								data-slide="prev"><span class="carousel-control-prev-icon"></span>
								<span class="sr-only">Previous</span></a> <a
								class="carousel-control-next" href="#carousel-166681"
								data-slide="next"><span class="carousel-control-next-icon"></span>
								<span class="sr-only">Next</span></a>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-5">
						<table class="table table-hover">
							<thead>
								<tr>
									<th scope="col"></th>
									<th scope="col">공지사항</th>
									<th scope="col"></th>
									<th scope="col"><a href="./notice/noticelist?pg=1&key=&word=">+</a></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${articles}" var="a" varStatus="vs">
					      			<tr onclick = "location.href='./notice/noticedetail?articleno=${a.articleno}'">
					      				<td class = "articleno">${vs.count}</td>
					        			<td>${a.subject}</td>
					        			<c:set var = "regidate2" value = "${fn:split(a.regidate, ' ')}" />
					        			<td class = "articledate">${regidate2[0]}</td>
					      			</tr>
				      			</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="col-md-4">
						<table class="table table-hover">
							<thead>
								<tr>
									<th scope="col"></th>
									<th scope="col">부동산 뉴스</th>
									<th scope="col"></th>
									<th scope="col"><a href="./news?act=newslist">+</a></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="row">1</th>
									<td>집값이올랐음</td>
									<td>20/09/21</td>
								</tr>
								<tr>
									<th scope="row">2</th>
									<td>집값이 더 오름</td>
									<td>20/09/14</td>
								</tr>
								<tr>
									<th scope="row">3</th>
									<td>집값이언제떨...</td>
									<td>20/07/06</td>
								</tr>
								<tr>
									<th scope="row">4</th>
									<td>집을못구할듯</td>
									<td>20/06/23</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="col-md-3">
						<img alt="ad" src="./img/ad.PNG" class="rounded" />
					</div>
				</div>
			</div>
			<div class="col-md-1"></div>
		</div>
	</div>
	<script>
		$(document).ready(function() {
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
		});
	</script>
</body>

</html>