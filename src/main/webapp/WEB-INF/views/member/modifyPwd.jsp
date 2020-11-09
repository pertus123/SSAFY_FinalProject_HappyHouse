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

	<div id="bgc">
		<div class="row">
			<div class="col-md-3" id="side">
				<ul class="nav nav-tabs flex-column">
					<li class="nav-item"><a class="nav-link "
						href="./user?act=inform">개인 정보 수정</a></li>
					<li class="nav-item"><a class="nav-link" href="./user?act=pwd">비밀번호
							변경</a></li>
				</ul>
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-5">
				<form id="modifyForm" method="post">
					<input type="hidden" name="act" id="act" value="pwdaf">
					<div class="container-fluid border">
						<div class="form-group">
							<label for="pwd">새로운 비밀번호</label> <input type="password"
								class="form-control" id="new_pwd" name="new_pwd">
						</div>
						<div class="form-group">
							<label for="pwd">새로운 비밀번호 확인</label> <input type="password"
								class="form-control" id="new_pwd_ok">
						</div>
						<button type="submit" class="btn btn-block"
							onclick="javascript:modify();">UPDATE</button>
					</div>
				</form>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>
	<script type="text/javascript">
		function modify() {
			if ($("#new_pwd").val() == "") {
				alert("새로운 비밀번호을 입력해주세요.");
				return;
			} else if ($("#new_pwd").val() != $("#new_pwd_ok").val()){
				alert("비밀번호를 확인해주세요.");
				return
			} else {
				$("#modifyForm").attr("action", "./user").submit();
			}
		}
	</script>
</body>

</html>