<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="./css/notice.css">
<link rel="stylesheet" href="./css/header_footer.css">
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
	<form name="pageform" id="pageform" method="GET" action="">
		<input type="hidden" name="act" id="act" value="noticelist">
		<input type="hidden" name="pg" id="pg" value="">
		<input type="hidden" name="key" id="key" value="${key}">
		<input type="hidden" name="word" id="word" value="${word}">
	</form>
	<jsp:include page="session.jsp" />
	<header><jsp:include page="mainHeader.jsp" /></header>
	
	<div class="banner">
		<h2>공지사항</h2>
	</div>
	<div class="container" align="center">
		<div class="col-md-10">
			<form id="searchform" method="get" action="">
				<input type="hidden" name="act" id="act" value="noticelist">
	  			<input type="hidden" name="pg" id="pg" value="1">
				<div class="search-container">
					<select class="form-control" id="key" name="key">
						<option value="articleno" selected="selected" >글번호</option>
						<option value="subject">제목</option>
					</select> 
					<input type="text" class="form-control" name="word" id="word" placeholder="글 검색...">
					<button type="button" id="searchBtn" onclick="javascript:searchArticle();"><i class="fa fa-search"></i></button>
				</div>
			</form>
			<!-- admin이면 글작성 버튼 생성 -->
			<c:if test="${userinfo.isadmin}">
			<button id="noticewrite" class="btn-secondary btn-sm" onclick="location.href='./notice?act=noticewrite'">글작성</button>
			</c:if>
			
			<c:if test="${articles.size() != 0}">	
				<table class="table table-hover">
					<thead>
						<tr>
							<th class = "articleno">번호</th>
							<th>제목</th>
							<th class = "articledate">작성일</th>
						</tr>
					</thead>
		    		<tbody>
		    			<c:forEach items="${articles}" var="a">
		      			<tr onclick = "location.href='./notice?act=noticedetail&articleno=${a.articleno}'">
		      				<td class = "articleno">${a.articleno}</td>
		        			<td>${a.subject}</td>
		        			<c:set var = "regidate2" value = "${fn:split(a.regidate, ' ')}" />
		        			<td class = "articledate">${regidate2[0]}</td>
		      			</tr>
		      			</c:forEach>
		    		</tbody>
		  		</table>
		  		<table>
				  	<tr>
					  	<td>
						  	<!-- page 출력부분 -->
						  	${navigation.navigator}
					  	</td>
				  	</tr>
			  	</table>
		  	</c:if>
		  	<c:if test="${articles.size() == 0}">
				<table class="table table-active">
					<tbody>
						<tr class="table-info" align="center">
							<td>작성된 글이 없습니다.</td>
						</tr>
					</tbody>
				</table>
			 </c:if>
		</div>
	</div>
	<script type="text/javascript">
		function searchArticle() {
			$("#searchform").attr("action", "./notice").submit();
		}
		
		function pageMove(pg) { 
			$("#pg").val(pg);
			$("#pageform").attr("action", "./notice").submit();
		}
	</script>
</body>
</html>