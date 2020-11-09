package com.project.happyhouse.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.happyhouse.model.ArticleDto;
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
	
	//@GetMapping(value = "/noticelist&pg= {no}&key= {key}&word= {word}") // 글 목록 보기
	@GetMapping(value = "/noticelist") 
	public String noticelist( Model model, @RequestParam(defaultValue="1") int no, String key, String word) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("111111111111111111111");
		System.out.println(no + " ||" + key + "|| " + word + "wow");
        map.put("currentPage", no);
        map.put("sizePerPage", 10);
        map.put("key", key);
        map.put("word", word);

        model.addAttribute("articles",articleService.getnoticelist(map));
        model.addAttribute("navigation",articleService.makePageNavigation(map));
		
	//	articleService.getnoticelist(map);
		
	//	articleService.getnoticelist(currentPage, sizePerPage, key, word);
		System.out.println("noticelist");
		return "article/noticeList";
	}
	
	@GetMapping(value = "/noticedetail") // 글 상세 보기?
	public String noticedetail(int articleno, Model model) {
		System.out.println(articleno);
		ArticleDto articleDto = articleService.getnoticedetail(articleno);
		System.out.println(articleDto + "wow");
		model.addAttribute("article", articleDto);
		return "article/noticedetail";
	}
	
	
	@GetMapping(value = "/noticewrite") // 글쓰기 페이지 이동 
	public String noticewrite( ) {
	//	articleService.noticewrite(noticeDto);
		System.out.println("noticewrite");
		return "article/noticewrite";
	}
	
	
	@PostMapping(value = "/noticewriteaf") // 글쓰기 엑션
	public String noticewriteaf(ArticleDto articleDto) {
		System.out.println(articleDto);
		System.out.println("1");
		articleService.noticewrite(articleDto);
		System.out.println("noticewriteaf");
		return "redirect:/notice/noticelist";
	}
	
	
	@GetMapping(value = "/noticedelete")// articleno/articleno
	public String noticedelete(int articleno ) {
		System.out.println(articleno);
		articleService.noticedelete(articleno);
		System.out.println("noticedelete");
		return "redirect:/notice/noticelist";
	}
	
}