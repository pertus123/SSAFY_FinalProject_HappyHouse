package com.project.happyhouse.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
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

@RestController
@RequestMapping("/qnareply")
public class QnaReplyController {
	
	@Autowired
	private QnaReplyService qnaReplyService;

	@GetMapping(value = "/replylist",  headers = { "Content-type=application/json" })
	public List<QnaReplyDto> replylist(@RequestBody QnaReplyDto articleDto)
			throws SQLException {
		List<QnaReplyDto> article = qnaReplyService.qnaGetreplylist(articleDto);
		return article;
	}

	@CrossOrigin(origins="http://localhost:8081")
	@PostMapping(value = "/replywrite",  headers = { "Content-type=application/json" }) // 글쓰기 엑션
	public ResponseEntity replywrite(@RequestBody QnaReplyDto articleDto) {
		qnaReplyService.qnaReplywrite(articleDto);
		System.out.println("noticewriteaf");
		return new ResponseEntity("succ", HttpStatus.OK);
	}

	@DeleteMapping(value = "/replydelete/{articleno}",  headers = { "Content-type=application/json" }) // articleno/articleno
	public ResponseEntity replydelete(@PathVariable int articleno) {
		qnaReplyService.qnaReplydelete(articleno);
		System.out.println("noticedelete");
		return new ResponseEntity("succ", HttpStatus.OK);
	}
	
	@PutMapping(value="/replyupdate",  headers = { "Content-type=application/json" })
	public ResponseEntity replyupdate(@RequestBody QnaReplyDto articleDto) {
		qnaReplyService.qnaReplyupdate(articleDto);
		return new ResponseEntity("succ", HttpStatus.OK);
	}
}