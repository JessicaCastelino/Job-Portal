package com.dal.mycareer.controller;

import java.util.Properties;

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

	private static final Properties PROPERTY_MAP = PropertiesParser.getPropertyMap();
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/homepage")
	public String loadHome(Model model, HttpServletRequest request) {

		logger.info("Routed to Homepage");

		model.addAttribute("reqPage", PROPERTY_MAP.get("homepage").toString());
		model.addAttribute("role", "guest");

		IRoleModel roleModel = new RoleModel();

		model = roleModel.getBasePage(model, request);

		return model.asMap().get("view").toString();

	}

	@RequestMapping("/employerHome")
	public String employerHome(Model model, HttpServletRequest request) {

		logger.info("Routed to EmployerHome");

		model.addAttribute("reqPage", PROPERTY_MAP.get("employerHome").toString());
		model.addAttribute("role", "employer");

		IRoleModel roleModel = new RoleModel();

		model = roleModel.getBasePage(model, request);
		model.addAttribute("jobTypes", PROPERTY_MAP.get("jobTypes").toString().split(","));
		return model.asMap().get("view").toString();

	}

	@RequestMapping("/logout")
	public String logout(Model model, HttpServletRequest request) {

		logger.info("Routed to Logout");

		model.addAttribute("reqPage", PROPERTY_MAP.get("logout").toString());
		model.addAttribute("role", "guest");

		IRoleModel roleModel = new RoleModel();

		model = roleModel.getBasePage(model, request);
		
		//Invalidate Session
		request.getSession().invalidate();
		logger.info("Session Invalidated and Logged out");

		return model.asMap().get("view").toString();
	}

}
