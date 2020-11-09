package com.project.happyhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.happyhouse.model.service.ArticleService;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	@GetMapping(value = "/")
	public String hello( ) {
		System.out.println("3");
		return "index";
	}
	
	
	@GetMapping(value = "/noticelist") // 글 목록 보기
	public String noticelist( ) {
		articleService.getnoticelist(currentPage, sizePerPage, key, word);
		System.out.println("noticelist");
		return "index";
	}
	
	
	@GetMapping(value = "/noticedetail") // 글 상세 보기?
	public String noticedetail( ) {
		articleService.getnoticedetail(articleno);
		System.out.println("noticedetail");
		return "index";
	}
	
	
	@GetMapping(value = "/noticewrite") // 글쓰기 페이지 이동 
	public String noticewrite( ) {
		articleService.noticewrite(noticeDto);
		System.out.println("noticewrite");
		return "index";
	}
	
	
	@PostMapping(value = "/noticewriteaf") // 글쓰기 엑션
	public String noticewriteaf( ) {
		articleService.noticewrite(noticeDto);
		System.out.println("noticewriteaf");
		return "index";
	}
	
	
	@DeleteMapping(value = "/noticedelete")
	public String noticedelete( ) {
		articleService.noticedelete(articleno);
		System.out.println("noticedelete");
		return "index";
	}
	
	
	
	
	
	
	
	
	
	
	
	

}