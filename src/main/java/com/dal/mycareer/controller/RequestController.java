package com.dal.mycareer.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dal.mycareer.imodel.IRoleModel;
import com.dal.mycareer.model.RoleModel;
import com.dal.mycareer.propertiesparser.PropertiesParser;

@Controller
public class RequestController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/homepage")
	public String loadHome(Model model, HttpServletRequest request) {

		LOGGER.info("Routed to Homepage");

		model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("homepage").toString());
		model.addAttribute("role", "guest");

		IRoleModel roleModel = new RoleModel();

		model = roleModel.getBasePage(model, request);

		return model.asMap().get("view").toString();

	}

	/*
	 * @RequestMapping("/studentHome") public String studentHome(Model model,
	 * HttpServletRequest request) {
	 * 
	 * LOGGER.info("Routed to StudentHome");
	 * 
	 * model.addAttribute("reqPage",
	 * PropertiesParser.getPropertyMap().get("studentHome").toString());
	 * model.addAttribute("role", "student");
	 * 
	 * IRoleModel roleModel = new RoleModel();
	 * 
	 * model = roleModel.getBasePage(model, request);
	 * 
	 * return model.asMap().get("view").toString(); }
	 */

	@RequestMapping("/employerHome")
	public String employerHome(Model model, HttpServletRequest request) {

		LOGGER.info("Routed to EmployerHome");

		model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("employerHome").toString());
		model.addAttribute("role", "employer");

		IRoleModel roleModel = new RoleModel();

		model = roleModel.getBasePage(model, request);

		return model.asMap().get("view").toString();

	}

	@RequestMapping("/adminHome")
	public String adminHome(Model model, HttpServletRequest request) {

		LOGGER.info("Routed to AdminHome");

		model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("adminHome").toString());
		model.addAttribute("role", "admin");

		IRoleModel roleModel = new RoleModel();

		model = roleModel.getBasePage(model, request);

		return model.asMap().get("view").toString();
	}

}
