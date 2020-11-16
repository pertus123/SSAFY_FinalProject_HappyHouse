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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.happyhouse.model.ArticleDto;
import com.project.happyhouse.model.service.ArticleService;

@RestController
@RequestMapping("/qnanotice")
public class QnaArticleController {
	@Autowired
	private ArticleService articleService;

/*	@GetMapping(value = "/")
	public String hello() {
		System.out.println("3");
		return "index";
	}*/

	// @GetMapping(value = "/noticelist&pg= {no}&key= {key}&word= {word}") // 글 목록
	// 보기
	@GetMapping(value = "/noticelist",  headers = { "Content-type=application/json" })
	public List<ArticleDto> noticelist(Model model, @RequestParam(defaultValue = "1") int pg, String key, String word)
			throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		key = "";
		word = "";
		System.out.println(pg + " ||" + key + "|| " + word + "wow");
		map.put("currentPage", pg);
		map.put("sizePerPage", 10);
		map.put("StartProductNo", (pg - 1) * (int) map.get("sizePerPage"));
		map.put("key", key);
		map.put("word", word);

		List<ArticleDto> article = articleService.qnaGetnoticelist(map);
		model.addAttribute("articles", article);
		model.addAttribute("navigation", articleService.qnaMakePageNavigation(map));
		model.addAttribute("StartProductNo", map.get("StartProductNo"));
		model.addAttribute("key", key);
		model.addAttribute("word", word);

		return article;
	}

	@GetMapping(value = "/noticedetail/{articleno}",  headers = { "Content-type=application/json" }) // 글 상세 보기?
	public ArticleDto noticedetail(@PathVariable int articleno, Model model) {
		System.out.println(articleno);
		System.out.println("zzz");
		ArticleDto articleDto = articleService.qnaGetnoticedetail(articleno);
		System.out.println(articleDto + "wow");
		model.addAttribute("article", articleDto);
		return articleDto;
	}

	@GetMapping(value = "/noticewrite") // 글쓰기 페이지 이동
	public String noticewrite() {
		System.out.println("noticewrite");
		return "article/noticewrite";
	}

	@CrossOrigin(origins="http://localhost:8081")
	@PostMapping(value = "/noticewriteaf",  headers = { "Content-type=application/json" }) // 글쓰기 엑션
	public ResponseEntity noticewriteaf(@RequestBody ArticleDto articleDto) {
		System.out.println("글쓰기");
		System.out.println(articleDto);
		System.out.println("1");
		articleService.qnaNoticewrite(articleDto);
		System.out.println("noticewriteaf");
	//	return "redirect:/notice/noticelist";
		return new ResponseEntity("succ", HttpStatus.OK);
	}

	@GetMapping(value = "/noticedelete",  headers = { "Content-type=application/json" }) // articleno/articleno
	public String noticedelete(int articleno) {
		System.out.println(articleno);
		articleService.qnaNoticedelete(articleno);
		System.out.println("noticedelete");
		return "redirect:/notice/noticelist";
	}
	
	@GetMapping(value="/noticeupdate")
	public String noticeupdate(int articleno, Model model) {
		ArticleDto articleDto = articleService.qnaGetnoticedetail(articleno);
		model.addAttribute("article", articleDto);
		return "article/noticeupdate";
	}

	@PostMapping(value="/noticeupdate")
	public String noticeupdate(ArticleDto articleDto) {
		articleService.qnaNoticeupdate(articleDto);
		return "redirect:/notice/noticedetail?articleno="+articleDto.getArticleno();
	}
}