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
		<img alt="logo" src="${root}/img/logo_text1.png" width="50%">
		<form id="findForm" action="" method="post">
			<div class="container">
				<label for="id"><b>아이디를 이용한 비밀번호 변경하기</b></label> <input type="text"
					placeholder="본인의 아이디를 입력해주세요." name="userid" id="userid">
				<div class="wrapper">
					<button type="button" onclick="javascript:find();">아이디 인증하기</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		function find() {
			if (document.getElementById("userid").value == "") {
				alert("아이디를 입력해주세요.");
				return;
			} else {
				$("#findForm").attr("action", "${root}/member/idValidate").submit();
			}
		}
	</script>
</body>
</html>