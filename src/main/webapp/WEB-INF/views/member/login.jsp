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
		<form id="loginForm" action="" method="post">
			<div class="container">
				<label for="id"><b>ID</b></label> <input type="text"
					placeholder="아이디를 입력해주세요." name="userid" id="userid" value="${saveid}"> <label
					for="psw"><b>Password</b></label> <input type="password"
					placeholder="비밀번호를 입력해주세요." name="userpwd" id="userpwd">
				<div class="wrapper">
					<button type="submit" onclick="javascript:login();">로그인</button>
				</div>
				<div class="row">
					<div class="col-5 pt-1">
						<label for="idsave"> <input type="checkbox"
							id="idsave" name="idsave" value="saveok" ${idck}> 아이디 저장
						</label>
					</div>
					<div class="col-7 pt-1">
						<span class="psw">Forgot <a href="#">password?</a></span>
					</div>
				</div>
				<div class="signup">
					<p>
						아직 회원이 아니신가요? <a href="./member/join">회원가입</a>
					</p>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		function login() {
			if (document.getElementById("userid").value == "") {
				alert("아이디를 입력해주세요.");
				return;
			} else if (document.getElementById("userpwd").value == "") {
				alert("비밀번호를 입력해주세요.");
				return;
			} else {
				$("#loginForm").attr("action", "./member/login").submit();
			}
		}
	</script>
</body>
</html>