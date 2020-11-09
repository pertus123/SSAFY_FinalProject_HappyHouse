<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>내 정보 수정</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="${root}/css/header_footer.css">
<link rel="stylesheet" href="${root}/css/modifyInform.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>

<body>
	<jsp:include page="../includes/session.jsp" />
	<header><jsp:include page="../includes/userHeader.jsp" /></header>

	<div class="row">
		<div class="col-md-3">
			<ul class="nav nav-tabs flex-column" id="side">
				<li class="nav-item"><a class="nav-link "
					href="./user?act=inform">개인 정보 수정</a></li>
				<li class="nav-item"><a class="nav-link" href="./user?act=pwd">비밀번호
						변경</a></li>
			</ul>
		</div>
		<div class="col-md-1"></div>
		<div class="col-md-5">
			<form id="modifyForm" method="post">
				<input type="hidden" name="act" id="act" value="leaveaf">
				<div class="container-fluid border">
					<h3>회원 탈퇴</h3>
					<br>
					<span>
						- 회원탈퇴 후 모든 정보는 삭제되며, 복구가 불가능합니다.<br><br>
						- 회원탈퇴를 원하시면 '회원탈퇴'를 하단에 적어주십시오.<br>
					</span>
					<br>
					<div class="form-group">
						<input type="text" class="form-control" id="leaveInput">
					</div>
					<button type="submit" class="btn btn-block" onclick="javascript:leave();">탈퇴하기</button>
				</div>

			</form>

		</div>
		<div class="col-md-3"></div>
	</div>
	<script type="text/javascript">
		function leave() {
			if ($("#leaveInput").val() == "") {
				alert("단어를 입력해주세요.");
				return;
			}
			else if ($("#leaveInput").val() != "회원탈퇴") {
				alert("올바른단어를 입력해주세요.");
				return;
			} 
			else {
				$("#modifyForm").attr("action", "./user").submit();
			}
		}
	</script>
</body>

</html>