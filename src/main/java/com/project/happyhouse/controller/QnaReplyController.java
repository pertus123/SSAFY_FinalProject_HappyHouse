package com.project.happyhouse.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.happyhouse.model.QnaArticleDto;
import com.project.happyhouse.model.QnaReplyDto;
import com.project.happyhouse.model.service.ArticleService;
import com.project.happyhouse.model.service.QnaArticleService;
import com.project.happyhouse.model.service.QnaReplyService;

import io.swagger.annotations.ApiOperation;

//http://localhost:8197/project/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/qnareply")
public class QnaReplyController {
	
	private static final Logger logger = LoggerFactory.getLogger(QnaArticleController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private QnaReplyService qnaReplyService;

	@ApiOperation(value = "해당 QnA글의 댓글목록를 반환한다.", response = List.class)
	@GetMapping(value = "/replylist/{articleno}")
	public ResponseEntity<List<QnaReplyDto>> replylist(@PathVariable int articleno) throws SQLException {
		logger.debug("QnaReplyList - 호출 : articleno = " + articleno);
		return new ResponseEntity<List<QnaReplyDto>>(qnaReplyService.qnaGetreplylist(articleno), HttpStatus.OK);
	}

	//@CrossOrigin(origins="http://localhost:8081")
	@ApiOperation(value = "새로운 댓글을 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping(value = "/replywrite") // 글쓰기 엑션
	public ResponseEntity<String> replywrite(@RequestBody QnaReplyDto qnaReplyDto) {
		logger.debug("QnaWriteReply - 호출 : articleDto = " + qnaReplyDto);
		if (qnaReplyService.qnaReplywrite(qnaReplyDto) > 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "댓글번호에 해당하는 댓글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping(value = "/replydelete/{replyno}")
	public ResponseEntity<String> replydelete(@PathVariable int replyno) {
		logger.debug("QnaDeleteReply - 호출 : replyno = " + replyno);
		if (qnaReplyService.qnaReplydelete(replyno) > 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "댓글 번호에 해당하는 댓글정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping(value="/replyupdate",  headers = { "Content-type=application/json" })
	public ResponseEntity<String> replyupdate(@RequestBody QnaReplyDto qnaReplyDto) {
		logger.debug("QnaUpdateReply - 호출 : qnaReplyDto = " + qnaReplyDto);
		if (qnaReplyService.qnaReplyupdate(qnaReplyDto) > 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}