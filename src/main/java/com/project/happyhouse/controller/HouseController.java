package com.project.happyhouse.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.happyhouse.model.CommerceDto;
import com.project.happyhouse.model.HospitalDto;
import com.project.happyhouse.model.HouseDealDto;
import com.project.happyhouse.model.SearchBean;
import com.project.happyhouse.model.mapper.HouseMapper;
import com.project.happyhouse.model.service.HouseService;

import io.swagger.annotations.ApiOperation;

@RestController
public class HouseController {
	@Autowired
	private HouseService houseService;
	private static final Logger logger = LoggerFactory.getLogger(QnaArticleController.class);
	
	@ApiOperation(value = "거래종류, 지역, 키워드를 통해 아파트 정보를 전환한다.", response = List.class)
	@GetMapping(value = "/searchdata/", headers = { "Content-type=application/json" })
	public  ResponseEntity<List<HouseDealDto>> searchdata(HttpServletRequest request) {
		logger.info("search - 호출 ");
	//	System.out.println(request.getParameter("dealType"));
	//	System.out.println(request.getParameter("searchType"));
	//	System.out.println(request.getParameter("keyword"));
	//	System.out.println("1");
		int dealType = Integer.parseInt(request.getParameter("dealType"));
		int searchType = Integer.parseInt(request.getParameter("searchType"));
		String keyword = request.getParameter("keyword");
		
		SearchBean bean = new SearchBean();
		bean.setDealType(dealType);
		bean.setSearchType(searchType);
		bean.setKeyword(keyword);
		
		try {
			if(bean.getSearchType() == 0) return new ResponseEntity<List<HouseDealDto>>(houseService.searchZero(bean), HttpStatus.OK);
			else return new ResponseEntity<List<HouseDealDto>>(houseService.searchOne(bean), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	@ApiOperation(value = "아파트 세부 정보를 전환한다.", response = HouseDealDto.class)
	@GetMapping(value = "/detail/{no}", headers = { "Content-type=application/json" })
	public ResponseEntity<HouseDealDto> detail(@PathVariable int no) {
		logger.info("searchdata - 세부 사항 호출 ");
		try {
			return new ResponseEntity<HouseDealDto>(houseService.getDealInfo(no), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	@ApiOperation(value = "병원, 진료소 정보를 반환한다.", response = HospitalDto.class)
	@GetMapping(value = "/hospital", headers = { "Content-type=application/json" })
	public  ResponseEntity<List<HospitalDto>> hospital(HttpServletRequest request) {
		logger.info("hospital - 병원 호출 ");
		String type = request.getParameter("type");
		String code = request.getParameter("code");
		
		try {
			List<HospitalDto> test = houseService.getHospital(type, code);
			
			System.out.println(test.get(0));
			System.out.println(type + " " + code + " "+ "성공");
			return new ResponseEntity<List<HospitalDto>>(houseService.getHospital(type, code), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(type + " " + code + " " + "실패");
		return null;
	}
	
	/*
	 	@Override
	public List<CommerceDto> getCommerce(String dongName) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(HouseMapper.class).getCommerce(dongName);
	}
	 * */
	
	
	@ApiOperation(value = "상권정보를 동 조건으로 받는다.", response = HospitalDto.class)
	@GetMapping(value = "/commerce/{dongName}", headers = { "Content-type=application/json" })
	public  ResponseEntity<List<CommerceDto>> commerce(@PathVariable String dongName) {
		logger.info("commerce - 상권 호출 ");
//		String type = request.getParameter("type");
//		String code = request.getParameter("code");
		
		try {
		//	List<HospitalDto> test = houseService.getHospital(type, code);
		//	System.out.println(test.get(0));
		//	System.out.println(type + " " + code + " "+ "성공");
			return new ResponseEntity<List<CommerceDto>>(houseService.getCommerce(dongName), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(type + " " + code + " " + "실패");
		return null;
	}
	
	
	
}








//@GetMapping(value = "/")
//public String hello(Model model ) {
//	Map<String, Object> map = new HashMap<String, Object>();
//	int currentPage = 1;
//	int sizePerPage = 4;
//	System.out.println(currentPage + " " + sizePerPage);
//	try {
//		map.put("currentPage", currentPage);
//		map.put("sizePerPage", sizePerPage);
//		map.put("StartProductNo", 0);
//
////우선 임시로
////		List<ArticleDto> articles = articleService.getnoticelist(map);
////		model.addAttribute("articles", articles);
//
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	
//	System.out.println("main");
//	return "index";
//
//	//System.out.println("3");
//	//return "index";
//}

//@GetMapping(value = "/main") // 글 상세 보기?
//public String main(@PathVariable int no, Model model) {
//	Map<String, Object> map = new HashMap<String, Object>();
//	int currentPage = 1;
//	int sizePerPage = 4;
//	System.out.println(currentPage + " " + sizePerPage);
//	try {
//		map.put("currentPage", currentPage);
//		map.put("sizePerPage", sizePerPage);
//		
//		List<ArticleDto> articles = articleService.getnoticelist(map);
//		model.addAttribute("articles", articles);
//
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	
//	System.out.println("main");
//	return "redirect:/";
//}

//@ApiOperation(value = "거래종류, 지역, 키워드를 통해 정보를 전환한다.", response = List.class)
//@PostMapping(value = "/search") 
//public String search(HttpServletRequest request, Model model) throws Exception {
//	System.out.println("1");
//	int dealType = Integer.parseInt(request.getParameter("dealType"));
//	int searchType = Integer.parseInt(request.getParameter("searchType"));
//	String keyword = request.getParameter("keyword");
//	SearchBean bean = new SearchBean();
//	bean.setDealType(dealType);
//	bean.setSearchType(searchType);
//	bean.setKeyword(keyword);
//	model.addAttribute("searchbean", bean);
//	//List<HouseDealDto> list  = houseService.search(bean);
//	System.out.println("ww");
//	System.out.println(bean);
//	System.out.println("ww");
//	System.out.println("search");
//	//model.addAttribute("searchbean", list);
//	return "search";
//}