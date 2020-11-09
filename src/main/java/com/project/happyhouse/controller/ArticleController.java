package com.project.happyhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.happyhouse.model.service.ArticleService;

@Controller
@RequestMapping("/notice")
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
		return "noticelist";
	}
	
	
	@GetMapping(value = "/noticedetail/{no}") // 글 상세 보기?
	public String noticedetail(@PathVariable int no) {
		articleService.getnoticedetail(no);
		System.out.println("noticedetail");
		return "redirect:/noticelist";
	}
	
	
	@GetMapping(value = "/noticewrite") // 글쓰기 페이지 이동 
	public String noticewrite( ) {
		articleService.noticewrite(noticeDto);
		System.out.println("noticewrite");
		return "noticewrite";
	}
	
	
	@PostMapping(value = "/noticewriteaf") // 글쓰기 엑션
	public String noticewriteaf( ) {
		articleService.noticewrite(noticeDto);
		System.out.println("noticewriteaf");
		return "redirect:/noticelist";
	}
	
	
	@DeleteMapping(value = "/noticedelete")
	public String noticedelete( ) {
		articleService.noticedelete(articleno);
		System.out.println("noticedelete");
		return "redirect:/noticelist";
	}
	
}