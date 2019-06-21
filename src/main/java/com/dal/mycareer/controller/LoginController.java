package com.dal.mycareer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dal.mycareer.DAO.Impl.LoginDAO;
import com.dal.mycareer.DAO.Interface.ILoginDAO;
import com.dal.mycareer.DTO.UserLogin;
import com.dal.mycareer.imodel.ILoginModel;
import com.dal.mycareer.model.LoginModel;

@Controller
public class LoginController {

	private static final String USERNAME = "username";
	private static final String SESSION_NAME = "sessionName";

	private static final String USER_LOGIN = "userLogin";

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String Login(@Valid @ModelAttribute(USER_LOGIN) UserLogin ulogin, BindingResult result, Model model,
			HttpServletRequest request) {

		LOGGER.info("Routed to Homepage");

		if (result.hasErrors()) {

			return "homepage";

		} else {

			ILoginModel loginModel = new LoginModel();
			ILoginDAO loginDAO = new LoginDAO();

			model = loginModel.verifyLogin(model, ulogin, loginDAO, request);

			if (model.asMap().get("isValidUser").toString().equalsIgnoreCase("true")) {
				String userSessionName = request.getParameter(USERNAME);
				HttpSession session = request.getSession();
				session.setAttribute(SESSION_NAME, userSessionName);
				session.setAttribute("home", model.asMap().get("view").toString());
				session.setAttribute("role", model.asMap().get("role").toString());
				session.setAttribute("nextPage", model.asMap().get("nextPage").toString());
			}
			String page = model.asMap().get("nextPage").toString();
			return page;

		}

	}

}
