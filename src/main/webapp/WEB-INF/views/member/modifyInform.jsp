<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>내 정보 수정</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="./css/header_footer.css">
<link rel="stylesheet" href="./css/modifyInform.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>

<body>
	<jsp:include page="session.jsp" />
	<header><jsp:include page="userHeader.jsp" /></header>

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
				<input type="hidden" name="act" id="act" value="informaf">
				<div class="container-fluid border">
					<div class="form-group">
						<label for="usrname">*이름</label> 
						<input type="text" class="form-control" name="username" value='${user.username}'>
					</div>
					<div class="form-group">
						<label for="birth">생년월일</label> 
						<input type="date" class="form-control" name="userbirth" value='${user.userbirth}'>
					</div>
					<div class="form-group">
						<label for="phone">핸드폰</label> 
						<input type="number" class="form-control" name="userphone" value='${user.userphone}'>
					</div>
					<!-- <div class="form-group">
                        <label for="email">이메일</label>
                        <input type="email" class="form-control" id="email" value="eespacio95@gmail.com">
                    </div>
                    <div class="form-group">
                        <label for="addr">거주지</label>
                        <input type="address" class="form-control" id="addr" value="충남 아산시 어쩌구">
                    </div> -->
					<button type="submit" class="btn btn-block" onclick="javascript:modify();">UPDATE</button>
				</div>
				<div>
				<a id="wantleave" href="./user?act=leave">회원탈퇴를 진행하고싶은신가요..?</a>
				</div>
			</form>
		</div>
		<div class="col-md-3"></div>
	</div>
	<script type="text/javascript">
		function modify() {
			if ($("#userid").val() == "") {
				alert("이름을 입력해주세요.");
				return;
			} else {
				$("#modifyForm").attr("action", "./user").submit();
			}
		}
	</script>
</body>

</html>