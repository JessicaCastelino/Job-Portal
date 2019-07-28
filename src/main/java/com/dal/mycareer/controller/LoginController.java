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

	// Class level constants
	private static final String ERROR = "error";
	private static final String NEXT_PAGE = "nextPage";
	private static final String ROLE = "role";
	private static final String TRUE = "true";
	private static final String IS_VALID_USER = "isValidUser";
	private static final String VIEW = "view";
	private static final String HOME = "home";
	private static final String HOMEPAGE = "homepage";
	private static final String USERNAME = "username";
	private static final String SESSION_NAME = "sessionName";

	private static final String USER_LOGIN = "userLogin";

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String Login(@Valid @ModelAttribute(USER_LOGIN) UserLogin ulogin, BindingResult result, Model model,
			HttpServletRequest request) {

		logger.info("Login request : Start");

		if (result.hasErrors()) {

			logger.error("Incorrect from data entered by user");

			return HOMEPAGE;

		} else {

			
			ILoginModel loginModel = new LoginModel();
			ILoginDAO loginDAO = new LoginDAO();

			model = loginModel.verifyLogin(model, ulogin, loginDAO, request);
			String page = null;

			if (model.asMap().get(IS_VALID_USER).toString().equalsIgnoreCase(TRUE)) {
				String userSessionName = request.getParameter(USERNAME);
				HttpSession session = request.getSession();
				session.setAttribute(SESSION_NAME, userSessionName);
				session.setAttribute(HOME, model.asMap().get(VIEW).toString());
				session.setAttribute(ROLE, model.asMap().get(ROLE).toString());
				session.setAttribute(NEXT_PAGE, model.asMap().get(NEXT_PAGE).toString());
			}
			if (model.containsAttribute(ERROR)) {

				logger.error("Incorrect URL entered by user");
				page = HOMEPAGE;

			} else {
				page = model.asMap().get(NEXT_PAGE).toString();
			}

			logger.info("Login request : END");
			return page;

		}

	}

}
