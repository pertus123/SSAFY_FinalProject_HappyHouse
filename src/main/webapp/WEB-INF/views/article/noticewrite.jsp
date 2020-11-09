<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="./css/noticeDetail.css">
<link rel="stylesheet" href="./css/header_footer.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<jsp:include page="../includes/session.jsp" />
	<header><jsp:include page="../includes/mainHeader.jsp" /></header>

	<div class="banner">
		<h2>공지사항</h2>
	</div>
	<div class="container" align="center">
		<div class="col-md-10">
			<form id="writeform" method="post" action="">
				<input type="hidden" name="act" id="act" value="noticewriteaf">
				<div class="content">
					<table>
						<thead>
							<tr>
								<th>제목</th>
								<td>
									<input type="text" class="form-control" id = "subj" name="subject">
								</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>내용</th>
								<td>
									<textarea class="form-control" rows="15" id = "cont" name="content"></textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			<div class="buttonDiv">
				<button type="submit" class="btn btn-lg btn-primary" onclick="javascript:writeArticle();" style="background-color: #DBC3AB; border: 0;">글작성</button>
				<button type="button" class="btn btn-secondary btn-lg" onclick="location.href='./notice?act=noticelist&pg=1&key=&word='" style="border: 0;">글목록</button>
			</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function writeArticle() {
			if($("#subj").val() == "") {
				alert("제목을 입력해주세요.");
				return;
			} else if($("#cont").val() == "") {
				alert("내용을 입력해주세요");
				return;
			} else {
				$("#writeform").attr("action", "./notice").submit();
			}
		  }
	</script>
</body>
</html>