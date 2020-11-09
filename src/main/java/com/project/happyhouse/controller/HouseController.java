package com.project.happyhouse.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.happyhouse.model.ArticleDto;
import com.project.happyhouse.model.HouseDealDto;
import com.project.happyhouse.model.SearchBean;
import com.project.happyhouse.model.service.ArticleService;
import com.project.happyhouse.model.service.HouseService;

@Controller
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
		//articleService.getnoticedetail(no);
		Map<String, Object> map = new HashMap<String, Object>();
		int currentPage = 1;
		int sizePerPage = 4;
		System.out.println(currentPage + " " + sizePerPage);
		try {
		//	List<NoticeDto> articles = NoticeServiceImpl.getNoticeService().getnoticelist(currentPage, sizePerPage,	null, null);
			map.put("currentPage", currentPage);
			map.put("sizePerPage", sizePerPage);
			
			List<ArticleDto> articles = articleService.getnoticelist(map);
			model.addAttribute("articles", articles);
			
		//	request.setAttribute("articles", articles); // c7
		//	RequestDispatcher dispatcher = request.getRequestDispatcher("./main.jsp");
		//	dispatcher.forward(request, response); // c7
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("main");
		return "redirect:/";
	}
	
	@GetMapping(value = "/search") 
	public String search(HttpServletRequest request, Model model) {
	//	String path = "/search.jsp";
//		Map<String, String[]> map = request.getParameterMap();
//		Set<String> set = map.keySet();
//		for(String k:set) {
//			System.out.println(k+" "+Arrays.toString(map.get(k)));
//		}
		int dealType = Integer.parseInt(request.getParameter("dealType"));
		int searchType = Integer.parseInt(request.getParameter("searchType"));
		String keyword = request.getParameter("keyword");
		SearchBean bean = new SearchBean();
		bean.setDealType(dealType);
		bean.setSearchType(searchType);
		bean.setKeyword(keyword);
		model.addAttribute("searchbean", bean);
	//	request.setAttribute("searchbean", bean);
	//	request.getRequestDispatcher(path).forward(request, response);
		
		System.out.println("search");
		return "search";
	}
	
	
	
	@GetMapping(value = "/searchdata")
	public String searchdata(HttpServletRequest request, HttpServletResponse response) {
	//	articleService.noticewrite(noticeDto);
//		Map<String, String[]> map = request.getParameterMap();
//		Set<String> set = map.keySet();
//		for(String k:set) {
//			System.out.println(k+" "+Arrays.toString(map.get(k)));
//		}
		int dealType = Integer.parseInt(request.getParameter("dealType"));
		int searchType = Integer.parseInt(request.getParameter("searchType"));
		String keyword = request.getParameter("keyword");

		List<HouseDealDto> list = null;
		PrintWriter out = response.getWriter();
		JSONArray arr = new JSONArray();
		try {
			SearchBean bean = new SearchBean();
			bean.setDealType(dealType);
			bean.setSearchType(searchType);
			bean.setKeyword(keyword);
			request.setAttribute("searchbean", bean);
			list = HouseServiceImpl.getHouseService().search(bean);
			for (HouseDealDto dto : list) {
				JSONObject obj = new JSONObject();
				obj.put("no", dto.getNo());
				obj.put("dong", dto.getDong());
				obj.put("aptName", dto.getAptName());
				obj.put("code", dto.getGunguCode());
				obj.put("buildYear", dto.getBuildYear());
				obj.put("jubun", dto.getJibun());
				obj.put("lat", dto.getLat());
				obj.put("lng", dto.getLng());
				arr.add(obj);
			}
		} catch (Exception e) {
			arr = new JSONArray();
			JSONObject obj = new JSONObject();
			obj.put("message_code", "-1");
			arr.add(obj);
			e.printStackTrace();
			request.setAttribute("msg", "매물 검색 중 문제가 발생했습니다.");
		} finally {
			out.print(arr.toJSONString());
			out.close();
		}
		
		
		
		
		
		System.out.println("searchdata");
		return "searchdata";
	}
	
	
	
	
	
	@PostMapping(value = "/detail") // 글쓰기 엑션
	public String noticewriteaf( ) {
		PrintWriter out = response.getWriter();
		HouseDealDto dto = null;
		JSONObject obj = new JSONObject();
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			dto = HouseServiceImpl.getHouseService().getDealInfo(no);
			obj.put("no", dto.getNo());
			obj.put("dong", dto.getDong());
			obj.put("aptName", dto.getAptName());
			obj.put("code", dto.getGunguCode());
			obj.put("dealAmount", dto.getDealAmount());
			obj.put("buildYear", dto.getBuildYear());
			obj.put("area", dto.getArea());
			obj.put("floor", dto.getFloor());
			obj.put("jibun", dto.getJibun());
		} catch (Exception e) {
			obj = new JSONObject();
			obj.put("message_code", "-1");
			e.printStackTrace();
		} finally {
			out.print(obj.toJSONString());
			out.close();
		}
	//	articleService.noticewrite(noticeDto);
		System.out.println("detail");
		return "redirect:/detail";
	}
	
	
	
	@GetMapping(value = "/hospital") // 글쓰기 페이지 이동 
	public String noticewrite( ) {
	//	articleService.noticewrite(noticeDto);
//		Map<String, String[]> map = request.getParameterMap();
//		Set<String> set = map.keySet();
//		for(String k:set) {
//			System.out.println(k+" "+Arrays.toString(map.get(k)));
//		}
		String type = request.getParameter("type");
		String code = request.getParameter("code");

		System.out.println("메인" + type + " " + code);

		List<HospitalDto> list = null;
		PrintWriter out = response.getWriter();
		JSONArray arr = new JSONArray();
		try {
			list = HouseServiceImpl.getHouseService().getHospital(type, code);
			for (HospitalDto dto : list) {
				JSONObject obj = new JSONObject();
				obj.put("name", dto.getName());
				obj.put("address", dto.getAddress());
				obj.put("weekdayop", dto.getWeekdayop());
				obj.put("tel", dto.getTel());
				obj.put("lat", dto.getLat());
				obj.put("lng", dto.getLng());
				arr.add(obj);
			}
		} catch (Exception e) {
			arr = new JSONArray();
			JSONObject obj = new JSONObject();
			obj.put("message_code", "-1");
			arr.add(obj);
			e.printStackTrace();
			request.setAttribute("msg", "매물 검색 중 문제가 발생했습니다.");
		} finally {
			out.print(arr.toJSONString());
			out.close();
		}
		System.out.println("hospital");
		return "hospital";
	}
}
