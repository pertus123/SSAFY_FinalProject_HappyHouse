package com.project.happyhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.happyhouse.model.service.ArticleService;
import com.project.happyhouse.model.service.HouseService;

@Controller
public class HouseController {
	
	@Autowired
	private HouseService houseService;
	
	@GetMapping(value = "/")
	public String hello( ) {

		System.out.println("3");
		return "index";
	}

}
