<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!-- 로그인 창 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HappyHouse</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="./css/index.css" rel="stylesheet" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<c:if test="${userinfo == null}">
		<c:if test="${cookie.user_id.value ne null}">
			<c:set var="idck" value=" checked=\"checked\"" />
			<c:set var="saveid" value="${cookie.user_id.value}" />
		</c:if>
		<!-- 나중에 경로바꾸기 -->
		<%@ include file="/WEB-INF/views/member/login.jsp"%>
	</c:if>
	<c:if test="${userinfo != null}">
		<%@ include file="/WEB-INF/views/main.jsp"%>
	</c:if>
</body>
</html>