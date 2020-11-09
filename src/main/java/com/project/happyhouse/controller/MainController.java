package com.project.happyhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping(value = "/main")
	public String main( ) {

		System.out.println("main");
		return "index";
	}
	
	@GetMapping(value = "/search")
	public String search( ) {

		System.out.println("search");
		return "index";
	}
	
	@GetMapping(value = "/searchdata")
	public String searchdata( ) {

		System.out.println("searchdata");
		return "index";
	}
	
	@GetMapping(value = "/detail")
	public String detail( ) {

		System.out.println("detail");
		return "index";
	}
	
	@GetMapping(value = "/hospital")
	public String hospital( ) {

		System.out.println("hospital");
		return "index";
	}

	
	

}
