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
import com.project.happyhouse.model.service.ArticleService;
import com.project.happyhouse.model.service.QnaArticleService;

@RestController
@RequestMapping("/qnanotice")
public class QnaArticleController {
	@Autowired
	private QnaArticleService articleService;

	@GetMapping(value = "/noticelist",  headers = { "Content-type=application/json" })
	public List<QnaArticleDto> noticelist(Model model, @RequestParam(defaultValue = "1") int pg, String key, String word)
			throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
	//	key = "";
	//	word = "";
		System.out.println(pg + " ||" + key + "|| " + word + "wow");
		//map.put("currentPage", pg);
		//map.put("sizePerPage", 10);
		//map.put("StartProductNo", (pg - 1) * (int) map.get("sizePerPage"));
		map.put("key", key);
		map.put("word", word);

		List<QnaArticleDto> article = articleService.qnaGetnoticelist(map);
		model.addAttribute("articles", article);
		model.addAttribute("navigation", articleService.qnaMakePageNavigation(map));
		model.addAttribute("StartProductNo", map.get("StartProductNo"));
		model.addAttribute("key", key);
		model.addAttribute("word", word);

		return article;
	}

	@GetMapping(value = "/noticedetail/{articleno}",  headers = { "Content-type=application/json" }) // 글 상세 보기?
	public QnaArticleDto noticedetail(@PathVariable int articleno, Model model) {
		System.out.println(articleno);
		System.out.println("zzz");
		QnaArticleDto articleDto = articleService.qnaGetnoticedetail(articleno);
		System.out.println(articleDto + "wow");
		model.addAttribute("article", articleDto);
		return articleDto;
	}

	@CrossOrigin(origins="http://localhost:8081")
	@PostMapping(value = "/noticewriteaf",  headers = { "Content-type=application/json" }) // 글쓰기 엑션
	public ResponseEntity noticewriteaf(@RequestBody QnaArticleDto articleDto) {
		System.out.println("글쓰기");
		System.out.println(articleDto);
		System.out.println("1");
		articleService.qnaNoticewrite(articleDto);
		System.out.println("noticewriteaf");
		return new ResponseEntity("succ", HttpStatus.OK);
	}

	@DeleteMapping(value = "/noticedelete/{articleno}",  headers = { "Content-type=application/json" }) // articleno/articleno
	public ResponseEntity noticedelete(@PathVariable int articleno) {
		System.out.println(articleno);
		articleService.qnaNoticedelete(articleno);
		System.out.println("noticedelete");
		return new ResponseEntity("succ", HttpStatus.OK);
	}
	
	@PutMapping(value="/noticeupdate",  headers = { "Content-type=application/json" })
	public ResponseEntity noticeupdate(@RequestBody QnaArticleDto articleDto) {
		articleService.qnaNoticeupdate(articleDto);
		return new ResponseEntity("succ", HttpStatus.OK);
	}
}