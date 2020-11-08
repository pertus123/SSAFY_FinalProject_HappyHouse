<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="root" value="${pageContext.request.contextPath}" />
<fmt:requestEncoding value="utf-8" />
<nav class="navbar navbar-expand-lg">
        <!-- Brand/logo -->
        <a class="navbar-brand" href="./main?act=main"> <img src="./img/logo.png" alt="logo" style="width: 150px;">
        </a>
        <!-- Toggler/collapsibe Button -->
        <img src="./img/menu_button.png" width="70px" class="navbar-toggler ml-auto" type="button"
            data-toggle="collapse" data-target="#collapsibleNavbar">

        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <!-- Links -->
            <ul class="navbar-nav">
                <li class="nav-item ">
                    <h5><a class="nav-link" href ="./user?act=inform">내 정보 수정</a></h5>
                </li>
                <li class="nav-item">
                    <h5><a class="nav-link" href ="./user?act=interestloc">관심 지역</a></h5>
                </li>
                <li class="nav-item">
                    <h5><a class="nav-link" href ="./interestdeal.jsp">관심 매물</a></h5>
                </li>
            </ul>
            <div class="hb ml-auto custom-header">
            	<span>반갑습니다&nbsp;&nbsp;<br>${userinfo.username}(${userinfo.userid})님&nbsp;&nbsp;</span>
            	<c:if test="${userinfo.isadmin}">
					<button id="modifyuser" class="navbar-nav btn btn-secondary btn-sm"
						onclick="location.href='./user?act=modifyuser'"
						style="float: left; margin-right: 10px">&nbsp;&nbsp;회원관리</button>
				</c:if>
                <button id="logout" class="navbar-nav btn btn-secondary btn-sm" onclick="location.href='./user?act=logout'"
                    style="float: left">&nbsp;&nbsp;로그아웃</button>
            </div>
        </div>
    </nav>