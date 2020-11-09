<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>회원 관리</title>
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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
	<jsp:include page="../includes/session.jsp" />
	<nav class="navbar navbar-expand-lg">
		<!-- Brand/logo -->
		<a class="navbar-brand" href="${root}"> <img
			src="${root}/img/logo.png" alt="logo" style="width: 150px;">
		</a>
		<!-- Toggler/collapsibe Button -->
		<img src="./img/menu_button.png" width="70px"
			class="navbar-toggler ml-auto" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">

		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<!-- Links -->
			<ul class="navbar-nav">
				<li class="nav-item ">
					<h5>
						<a class="nav-link" href="${root}/notice/noticelist?pg=1&key=&word=">공지사항 관리</a>
					</h5>
				</li>
				<li class="nav-item ">
					<h5>
						<a class="nav-link" href="#">뉴스 관리</a>
					</h5>
				</li>
				<li class="nav-item ">
					<h5>
						<a class="nav-link" href="${root}/member/userList">회원 관리</a>
					</h5>
				</li>
			</ul>
			<div class="hb ml-auto">
				<button id="logout" class="navbar-nav btn btn-secondary btn-sm"
					onclick="location.href='${root}/member/logout'" style="float: left">&nbsp;&nbsp;로그아웃</button>
			</div>
		</div>
	</nav>

	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<form id="userSearchForm" action="" method="get">
				<div class="search-container">
					<select class="custom-select form-control" id="select" name="select">
						<option value="idsearch" selected>ID로 검색</option>
						<option value="namesearch">이름으로 검색</option>
					</select> <input type="text" id="search" name="search" placeholder="회원 검색...">
					<button type="submit" id="searchBtn" onclick="javascript:search();"><i class="fa fa-search"></i></button>
				</div>
			</form>
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">회원</th>
						<th scope="col">ID</th>
						<th scope="col">이름</th>
						<th scope="col">생년월일</th>
						<th scope="col">핸드폰번호</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${members}" var="m" varStatus="vs">
						<tr onclick = "./notice?act=noticedetail&articleno='${a.articleno}'">
							<th scope="row">${vs.count}</th>
							<td>${m.userid}</td>
							<td>${m.username}</td>
							<td>${m.userbirth}</td>
							<td>${m.userphone}</td>
							<td><button type="button" class="msgbtn" dear='${m.userid}'>쪽지보내기</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="col-md-2"></div>
	
	<!-- The Modal -->
	<div class="modal fade" id="messageModal">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h5 class="modal-title">쪽지 보내기</h5>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body">
	      	<h6 id="receiver">받는 이 : </h6>
	        <textarea cols="50%" rows="10px"></textarea>
	      </div>
	
	      <!-- Modal footer -->
	      <div class="modal-footer">
	      	<button type="button" class="btn ok" data-dismiss="modal" onclick="alert('쪽지를 전송했습니다.')">쪽지 전송</button>
	        <button type="button" class="btn cancel" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
	<script type="text/javascript">
		function search() {
			if ($("#search").val() == "") {
				alert("검색어를 입력하세요");
				return;
			} else {
				$("#userSearchForm").attr("action", "${root}/member/userSearch").submit();
			}
		}
		
		$(".msgbtn").click(function(){
			var dearid = $(this).attr("dear");
			if(!(dearid==null||dearid=="")){
				$("#receiver").text("받는 이 : "+dearid)
			}
			$("#messageModal").modal();
		});
	</script>
</body>
</html>