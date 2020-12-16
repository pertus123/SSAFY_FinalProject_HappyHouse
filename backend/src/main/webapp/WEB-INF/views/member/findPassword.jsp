<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<c:if test="${cookie.user_id.value ne null}">
	<c:set var="idck" value=" checked=\"checked\"" />
	<c:set var="saveid" value="${cookie.user_id.value}" />
</c:if>
<!-- 로그인 창 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HappyHouse</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="${root}/css/index.css" rel="stylesheet" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div id="blackbox">
		<img alt="logo" src="./img/logo_text1.png" width="50%">
		<form id="updateForm" action="" method="post">
			<input type = "hidden" name="userid" value ="${userid}" />
			<div class="container">
				<label for="id"><b>Password</b></label> 
				<input type="password" placeholder="새로운 비밀번호를 입력해주세요." name="new_userpwd" id="new_userpwd"> 
				<label for="psw"><b>Password 확인</b></label> 
				<input type="password" placeholder="비밀번호를 다시 입력해주세요." name="new_userpwd_ok" id="new_userpwd_ok">
				<div class="wrapper">
					<button type="button" onclick="javascript:updatePassword();">비밀번호 변경하기</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		function updatePassword() {
			if (document.getElementById("new_userpwd").value == "") {
				alert("변경할 비밀번호를 입력해주세요.");
				return;
			} else if (document.getElementById("new_userpwd").value != document.getElementById("new_userpwd_ok").value) {
				alert("비밀번호를 확인해주세요.");
				return;
			} else {
				$("#updateForm").attr("action", "${root}/member/findPassword").submit();
			}
		}
	</script>
</body>
</html>