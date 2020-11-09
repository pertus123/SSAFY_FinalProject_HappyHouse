<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="root" value="${pageContext.request.contextPath}" />
<fmt:requestEncoding value="utf-8" />
<nav class="navbar navbar-expand-lg">
	<!-- Brand/logo -->
	<a class="navbar-brand" href="${root}"> <img
		src="${root}/img/logo.png" alt="logo" style="width: 150px;">
	</a>
	<div class="hb ml-auto custom-header">
		<span>반갑습니다&nbsp;&nbsp;<br>${userinfo.username}(${userinfo.userid})님&nbsp;&nbsp;</span>
		<c:if test="${userinfo.isadmin}">
			<button id="modifyuser" class="navbar-nav btn btn-secondary btn-sm"
				onclick="location.href='${root}/member/userList'"
				style="float: left; margin-right: 10px">&nbsp;&nbsp;회원관리</button>
		</c:if>
		<button id="mypage" class="navbar-nav btn btn-secondary btn-sm"
			onclick="location.href='${root}/member/userInform'"
			style="float: left; margin-right: 10px">&nbsp;&nbsp;마이페이지</button>
		<button id="logout" class="navbar-nav btn btn-secondary btn-sm"
			onclick="location.href='${root}/member/logout'" style="float: right">&nbsp;&nbsp;로그아웃</button>
	</div>
</nav>