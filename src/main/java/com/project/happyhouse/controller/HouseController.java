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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.happyhouse.model.ArticleDto;
import com.project.happyhouse.model.HospitalDto;
import com.project.happyhouse.model.HouseDealDto;
import com.project.happyhouse.model.SearchBean;
import com.project.happyhouse.model.service.ArticleService;
import com.project.happyhouse.model.service.HouseService;

@Controller
@RequestMapping("/house")
public class HouseController {
	
	@Autowired
	private HouseService houseService;
	private ArticleService articleService;
	
	@GetMapping(value = "/")
	public String hello( ) {

		System.out.println("3");
		return "index";
	}
	
	@GetMapping(value = "/main") // 글 상세 보기?
	public String main(@PathVariable int no, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		int currentPage = 1;
		int sizePerPage = 4;
		System.out.println(currentPage + " " + sizePerPage);
		try {
			map.put("currentPage", currentPage);
			map.put("sizePerPage", sizePerPage);
			
			List<ArticleDto> articles = articleService.getnoticelist(map);
			model.addAttribute("articles", articles);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("main");
		return "redirect:/";
	}
	
	@GetMapping(value = "/search") 
	public String search(HttpServletRequest request, Model model) {
		int dealType = Integer.parseInt(request.getParameter("dealType"));
		int searchType = Integer.parseInt(request.getParameter("searchType"));
		String keyword = request.getParameter("keyword");
		SearchBean bean = new SearchBean();
		bean.setDealType(dealType);
		bean.setSearchType(searchType);
		bean.setKeyword(keyword);
		model.addAttribute("searchbean", bean);
		
		System.out.println("search");
		return "search";
	}
	
	
	@ResponseBody
	@GetMapping(value = "/searchdata", headers = { "Content-type=application/json" })
	public List<HouseDealDto> searchdata(HttpServletRequest request, HttpServletResponse response) {
		int dealType = Integer.parseInt(request.getParameter("dealType"));
		int searchType = Integer.parseInt(request.getParameter("searchType"));
		String keyword = request.getParameter("keyword");

		List<HouseDealDto> list = null;
		
		try {
			SearchBean bean = new SearchBean();
			bean.setDealType(dealType);
			bean.setSearchType(searchType);
			bean.setKeyword(keyword);
			request.setAttribute("searchbean", bean);
			list = houseService.search(bean);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "매물 검색 중 문제가 발생했습니다.");
		} 
		
		System.out.println("searchdata");
		return list;
	}
	
	
	@ResponseBody
	@GetMapping(value = "/detail", headers = { "Content-type=application/json" })
	public HouseDealDto detail(HttpServletRequest request) {
//		PrintWriter out = response.getWriter();
		HouseDealDto dto = null;
	//	JSONObject obj = new JSONObject();
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			houseService.getDealInfo(no);
			//dto = HouseServiceImpl.getHouseService().getDealInfo(no);
			dto = houseService.getDealInfo(no);
			
		} catch (Exception e) {
		//	obj = new JSONObject();
		//	obj.put("message_code", "-1");
			e.printStackTrace();
		} 
		/*finally {
			out.print(obj.toJSONString());
			out.close();
		}*/
	//	articleService.noticewrite(noticeDto);
		System.out.println("detail");
		return dto;
	}
	
	@ResponseBody
	@GetMapping(value = "/hospital", headers = { "Content-type=application/json" })
	public List<HospitalDto> hospital(HttpServletRequest request) {
		String type = request.getParameter("type");
		String code = request.getParameter("code");

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
			request.setAttribute("msg", "매물 검색 중 문제가 발생했습니다.");
		}
		
		System.out.println("hospital");
		return list;
	}
}
