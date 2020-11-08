package com.project.happyhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	
	@GetMapping(value = "/")
	public String hello( ) {

		System.out.println("3");
		return "index";
	}

}
