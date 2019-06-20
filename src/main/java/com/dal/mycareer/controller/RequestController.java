package com.dal.mycareer.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/homepage")
	public String loadHome(Model model, HttpServletRequest request) {

		LOGGER.info("Routed to Homepage");

		return "homepage";
	}

	@RequestMapping("/studentHome")
	public String studentHome(Model model, HttpServletRequest request) {

		LOGGER.info("Routed to StudentHome");

		if (request.getSession().getAttribute("sessionName") != null) {
			return "studentHome";
		} else {
			return "redirect:/homepage";
		}

	}

	@RequestMapping("/employerHome")
	public String employerHome(Model model, HttpServletRequest request) {

		LOGGER.info("Routed to EmployerHome");

		if (request.getSession().getAttribute("sessionName") != null) {
			return "employerHome";
		} else {
			return "redirect:/homepage";
		}

	}

	@RequestMapping("/adminHome")
	public String adminHome(Model model, HttpServletRequest request) {

		LOGGER.info("Routed to AdminHome");

		if (request.getSession().getAttribute("sessionName") != null) {
			return "adminHome";
		} else {
			return "redirect:/homepage";
		}

	}

}
