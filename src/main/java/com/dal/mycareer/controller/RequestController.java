package com.dal.mycareer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/homepage")
	public String loadHome() {
		LOGGER.info("Routed to Homepage");
		return "homepage";
	}

}
