package com.project.happyhouse.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.happyhouse.model.QnaArticleDto;
import com.project.happyhouse.model.service.QnaArticleService;

import io.swagger.annotations.ApiOperation;

//http://localhost:8197/project/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/qnanotice")
public class QnaArticleController {

	private static final Logger logger = LoggerFactory.getLogger(QnaArticleController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnaArticleService articleService;

	@ApiOperation(value = "모든 QnA글의 정보를 반환한다.", response = List.class)
	@GetMapping(value = "/qnanoticelist")
	public ResponseEntity<List<QnaArticleDto>> qnanoticelist() throws SQLException {
		logger.info("QnaNoticeList - 호출 ");

		return new ResponseEntity<List<QnaArticleDto>>(articleService.qnaGetnoticelist(), HttpStatus.OK);
	}

	@ApiOperation(value = "글번호에 해당하는 QnA글의 정보를 반환한다.", response = QnaArticleDto.class)
	@GetMapping(value = "/qnanoticedetail/{articleno}")
	public ResponseEntity<QnaArticleDto> qnanoticedetail(@PathVariable int articleno) {
		logger.info("QnaDetailBoard - 호출 : articleno = " + articleno);
		return new ResponseEntity<QnaArticleDto>(articleService.qnaGetnoticedetail(articleno), HttpStatus.OK);
	}

	// @CrossOrigin(origins="http://localhost:8081")'
	@ApiOperation(value = "새로운 QnA글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping(value = "/qnanoticewrite")
	public ResponseEntity<String> qnanoticewrite(@RequestBody QnaArticleDto articleDto) {
		logger.info("QnaWriteBoard - 호출 : articleDto = " + articleDto);
		if (articleService.qnaNoticewrite(articleDto) > 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 QnA글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping(value = "/qnanoticedelete/{articleno}")
	public ResponseEntity<String> noticedelete(@PathVariable int articleno) {
		logger.info("QnaDeleteBoard - 호출 : articleno = " + articleno);
		if (articleService.qnaNoticedelete(articleno) > 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 QnA글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping(value = "/qnanoticeupdate")
	public ResponseEntity<String> qnanoticeupdate(@RequestBody QnaArticleDto articleDto) {
		logger.info("QnaUpdateBoard - 호출 : articleDto = " + articleDto);
		if (articleService.qnaNoticeupdate(articleDto) > 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}