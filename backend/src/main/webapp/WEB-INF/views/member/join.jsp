<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!-- 회원가입 창 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HappyHouse</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="${root}/css/index.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div id="blackbox">
		<a href="${root}"><img alt="logo" src="${root}/img/logo_text1.png"
			width="50%"></a>
		<form id="joinForm" action="" method="post">
			<div class="container">
				<label for="id"><b>*아이디</b></label> <input type="text"
					placeholder="아이디를 입력해주세요." name="userid" id="userid" required>
				<label for="psw"><b>*비밀번호</b></label> <input type="password"
					placeholder="비밀번호를 입력해주세요." name="userpwd" id="userpwd" required>
				<label for="psw_ok"><b>*비밀번호 확인</b></label> <input type="password"
					placeholder="비밀번호를 다시 입력해주세요." name="userpwd_ok" id="userpwd_ok"
					required> <label for="name"><b>*이름</b></label> <input
					type="text" placeholder="이름을 입력해주세요." name="username" id="username"
					required> <label for="birth"><b>생년월일</b></label> <input
					type="date" placeholder="생년월일을 입력해주세요." name="userbirth"
					id="userbirth"> <label for="phone"><b>휴대폰번호</b></label>
				<input type="number" placeholder="휴대폰번호를 입력해주세요.(01000000000)"
					name="userphone" id="userphone"> <label
					style="font-size: 15px; float: right;">* : 필수 항목</label><br />
				<div class="wrapper">
					<button type="submit" id="registerBtn" onclick="javascript:join();">회원가입</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		function join() {
			if ($("#userid").val() == "") {
				alert("아이디를 입력해주세요.");
				return;
			} else if ($("#userpwd").val() == "") {
				alert("비밀번호를 입력해주세요.");
				return;
			} else if ($("#userpwd_ok").val() != $("#userpwd").val()) {
				alert("비밀번호와 비밀번호확인이 일치하지 않습니다.");
				return;
			} else if ($("#username").val() == "") {
				alert("이름을 입력해주세요.");
				return;
			} else {
				$("#joinForm").attr("action", "${root}/member/join").submit();
			}
		}
	</script>
</body>
</html>