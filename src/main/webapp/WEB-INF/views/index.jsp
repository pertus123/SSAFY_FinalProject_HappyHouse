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