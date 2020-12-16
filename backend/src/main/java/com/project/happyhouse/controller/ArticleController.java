package com.project.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;

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

import com.project.happyhouse.model.ArticleDto;
import com.project.happyhouse.model.QnaArticleDto;
import com.project.happyhouse.model.service.ArticleService;

import io.swagger.annotations.ApiOperation;

//http://localhost:8197/project/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/notice")
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	private static final Logger logger = LoggerFactory.getLogger(QnaArticleController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

//	@GetMapping(value = "/")
//	public String hello() {
//		System.out.println("3");
//		return "index";
//	}

	@ApiOperation(value = "가장최근 공지사항 4개의 정보를 반환한다.", response = List.class)
	@GetMapping(value = "/mainnoticelist")
	public ResponseEntity<List<ArticleDto>> mainnoticelist() throws SQLException {
		logger.debug("MainNoticeList - 호출 ");

		return new ResponseEntity<List<ArticleDto>>(articleService.getmainnoticelist(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "모든 공지사항의 정보를 반환한다.", response = List.class)
	@GetMapping(value = "/noticelist")
	public ResponseEntity<List<ArticleDto>> noticelist() throws SQLException {
		logger.debug("NoticeList - 호출 ");

		return new ResponseEntity<List<ArticleDto>>(articleService.getnoticelist(), HttpStatus.OK);
	}

	@ApiOperation(value = "글번호에 해당하는 공지사항글의 정보를 반환한다.", response = QnaArticleDto.class)
	@GetMapping(value = "/noticedetail/{articleno}")
	public ResponseEntity<ArticleDto> noticedetail(@PathVariable int articleno) {
		logger.debug("noticedetail - 호출 : articleno = " + articleno);
		return new ResponseEntity<ArticleDto>(articleService.getnoticedetail(articleno), HttpStatus.OK);
	}

	@ApiOperation(value = "새로운 공지사항글을 작성한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping(value = "/noticewrite")
	public ResponseEntity<String> noticewrite(@RequestBody ArticleDto articleDto) {
		logger.debug("noticewrite - 호출 : articleDto = " + articleDto);
		if(articleService.noticewrite(articleDto)>0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 공지사항글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping(value = "/noticedelete/{articleno}")
	public ResponseEntity<String> noticedelete(@PathVariable int articleno) {
		logger.debug("noticedelete - 호출 : articleno = " + articleno);
		if (articleService.noticedelete(articleno) > 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 공지사항글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping(value = "/noticeupdate")
	public ResponseEntity<String> noticeupdate(@RequestBody ArticleDto articleDto) {
		logger.debug("noticeupdate - 호출 : articleDto = " + articleDto);
		if (articleService.noticeupdate(articleDto) > 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}