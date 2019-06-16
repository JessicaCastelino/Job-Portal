package com.dal.mycareer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestController {
	
	@RequestMapping("/homepage")
	public String loadHome() {
		
		return "homepage";
	}

}
