<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="${root}/css/noticeDetail.css">
<link rel="stylesheet" href="${root}/css/header_footer.css">
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
	<header><jsp:include page="../includes/mainHeader.jsp" /></header>

	<div class="banner">
		<h2>공지사항</h2>
	</div>
	<div class="container" align="center">
		<div class="col-md-10">
			<div class="content">
				<table>
					<thead>
						<tr>
							<td id="subject">${article.subject}</td>
						</tr>
						<tr>
							<td id="date">${article.regidate}</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td id="content">${article.content}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<button type="button" class="btn-secondary btn-lg" onclick="location.href='${root}/notice/noticelist?pg=1&key=&word='">글목록</button>
				<c:if test="${userinfo.isadmin}">
				<button type="button" class="btn-secondary btn-lg" onclick="location.href='${root}/notice/noticedelete?articleno=${article.articleno}'">글삭제</button>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>