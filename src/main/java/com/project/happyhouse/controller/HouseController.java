package com.project.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.happyhouse.model.ArticleDto;
import com.project.happyhouse.model.HospitalDto;
import com.project.happyhouse.model.HouseDealDto;
import com.project.happyhouse.model.SearchBean;
import com.project.happyhouse.model.service.ArticleService;
import com.project.happyhouse.model.service.HouseService;

@Controller
public class HouseController {
	
	@Autowired
	private HouseService houseService;
	@Autowired
	private ArticleService articleService;
	
	@GetMapping(value = "/")
	public String hello(Model model ) {
		Map<String, Object> map = new HashMap<String, Object>();
		int currentPage = 1;
		int sizePerPage = 4;
		System.out.println(currentPage + " " + sizePerPage);
		try {
			map.put("currentPage", currentPage);
			map.put("sizePerPage", sizePerPage);
			map.put("StartProductNo", 0);

// 우선 임시로
//			List<ArticleDto> articles = articleService.getnoticelist(map);
//			model.addAttribute("articles", articles);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("main");
		return "index";

		//System.out.println("3");
		//return "index";
	}
	
//	@GetMapping(value = "/main") // 글 상세 보기?
//	public String main(@PathVariable int no, Model model) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		int currentPage = 1;
//		int sizePerPage = 4;
//		System.out.println(currentPage + " " + sizePerPage);
//		try {
//			map.put("currentPage", currentPage);
//			map.put("sizePerPage", sizePerPage);
//			
//			List<ArticleDto> articles = articleService.getnoticelist(map);
//			model.addAttribute("articles", articles);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println("main");
//		return "redirect:/";
//	}
	
	@PostMapping(value = "/search") 
	public String search(HttpServletRequest request, Model model) throws Exception {
		System.out.println("1");
		int dealType = Integer.parseInt(request.getParameter("dealType"));
		int searchType = Integer.parseInt(request.getParameter("searchType"));
		String keyword = request.getParameter("keyword");
		SearchBean bean = new SearchBean();
		bean.setDealType(dealType);
		bean.setSearchType(searchType);
		bean.setKeyword(keyword);
		model.addAttribute("searchbean", bean);
		//List<HouseDealDto> list  = houseService.search(bean);
		System.out.println("ww");
		System.out.println(bean);
		System.out.println("ww");
		System.out.println("search");
		//model.addAttribute("searchbean", list);
		return "search";
	}
	
	
	//@ResponseBody
	@GetMapping(value = "/searchdata", headers = { "Content-type=application/json" })
	public @ResponseBody List<HouseDealDto> searchdata( SearchBean bean) {
//		int dealType = Integer.parseInt(request.getParameter("dealType"));
//		int searchType = Integer.parseInt(request.getParameter("searchType"));
//		String keyword = request.getParameter("keyword");

		List<HouseDealDto> list = null;
		System.out.println("search1");
	//	System.out.println(dealType + " " + searchType + " " + keyword);
		try {
			System.out.println("s2");
	//		SearchBean bean = new SearchBean();
	//		bean.setDealType(dealType);
	//		bean.setSearchType(searchType);
	//		bean.setKeyword(keyword);
	//		request.setAttribute("searchbean", bean);
			System.out.println(bean + "Zzzzzzzzz");
//			list = houseService.search(bean);
			if(bean.getSearchType() == 0) {
				list = houseService.searchZero(bean);
			}
			else {
				list = houseService.searchOne(bean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		//	request.setAttribute("msg", "매물 검색 중 문제가 발생했습니다.");
		} 
		
		System.out.println("searchdata");
		System.out.println(list);
		return list;
	}
	
	
	@ResponseBody
	@GetMapping(value = "/detail", headers = { "Content-type=application/json" })
	public HouseDealDto detail(HttpServletRequest request) {
		HouseDealDto dto = null;
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			houseService.getDealInfo(no);
			dto = houseService.getDealInfo(no);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("detail");
		return dto;
	}
	
	
	@GetMapping(value = "/hospital", headers = { "Content-type=application/json" })
	public @ResponseBody List<HospitalDto> hospital(String type, String code) {
	//	String type = request.getParameter("type");
	//	String code = request.getParameter("code");

		System.out.println("메인" + type + " " + code);

		List<HospitalDto> list = null;
		//PrintWriter out = response.getWriter();
		//JSONArray arr = new JSONArray();
		try {
			list = houseService.getHospital(type, code);
		//			HouseServiceImpl.getHouseService().getHospital(type, code);
	
		} catch (Exception e) {
			//arr = new JSONArray();
		//	JSONObject obj = new JSONObject();
		//	obj.put("message_code", "-1");
		//	arr.add(obj);
			e.printStackTrace();
		//	request.setAttribute("msg", "매물 검색 중 문제가 발생했습니다.");
		}
		System.out.println(list);
		
		System.out.println("hospital");
		return list;
	}
}