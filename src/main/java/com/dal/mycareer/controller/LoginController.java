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

	private static final String USER_LOGIN = "userLogin";

	private static final String SESSION_NAME = "sessionName";

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String Login(@Valid @ModelAttribute(USER_LOGIN) UserLogin ulogin, BindingResult result, Model model,
			HttpServletRequest request) {

		LOGGER.info("Routed to Homepage");

		if (result.hasErrors()) {

			return "homepage";

		} else {

			String userSessionName = request.getParameter("username");
			HttpSession session = request.getSession();
			session.setAttribute(SESSION_NAME, userSessionName);

			model.addAttribute(USER_LOGIN, ulogin);
			
			ILoginModel loginModel = new LoginModel();
			ILoginDAO loginDAO = new LoginDAO();
			
			Model controllerModel = loginModel.verifyLogin(model, ulogin, loginDAO);
			
			

			return "homepage";

		}

	}

}
