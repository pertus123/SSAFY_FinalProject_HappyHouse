package com.project.happyhouse.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	//@GetMapping(value = "/noticelist&pg= {no}&key= {key}&word= {word}") // 글 목록 보기
	@GetMapping(value = "/noticelist&pg={no}&key={key}&word={word}") 
	public String noticelist(Model model, @PathVariable int no, @PathVariable String key, @PathVariable String word) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("currentPage", no);
        map.put("sizePerPage", 10);
        map.put("key", key);
        map.put("word", word);

        model.addAttribute("articles",articleService.getnoticelist(map));
        model.addAttribute("navigation",articleService.makePageNavigation(map));
		
	//	articleService.getnoticelist(map);
		
	//	articleService.getnoticelist(currentPage, sizePerPage, key, word);
		System.out.println("noticelist");
		return "noticelist";
	}
	
	
	@GetMapping(value = "/noticedetail/{no}") // 글 상세 보기?
	public String noticedetail(@PathVariable int no) {
		//articleService.getnoticedetail(no);
		System.out.println("noticedetail");
		return "redirect:/noticelist";
	}
	
	
	@GetMapping(value = "/noticewrite") // 글쓰기 페이지 이동 
	public String noticewrite( ) {
	//	articleService.noticewrite(noticeDto);
		System.out.println("noticewrite");
		return "noticewrite";
	}
	
	
	@PostMapping(value = "/noticewriteaf") // 글쓰기 엑션
	public String noticewriteaf( ) {
	//	articleService.noticewrite(noticeDto);
		System.out.println("noticewriteaf");
		return "redirect:/noticelist";
	}
	
	
	@DeleteMapping(value = "/noticedelete")
	public String noticedelete( ) {
	//	articleService.noticedelete(articleno);
		System.out.println("noticedelete");
		return "redirect:/noticelist";
	}
	
}