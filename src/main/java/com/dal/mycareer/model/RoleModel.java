package com.dal.mycareer.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.dal.mycareer.imodel.IRoleModel;

public class RoleModel implements IRoleModel {

	// Class level constants
	private static final String NEXT_PAGE = "nextPage";
	private static final String REDIRECT_HOMEPAGE = "redirect:/homepage";
	private static final String HOMEPAGE = "homepage";
	private static final String LOGOUT = "logout";
	private static final String VIEW = "view";
	private static final String REQ_PAGE = "reqPage";
	private static final String ROLE = "role";
	private static final String SESSION_NAME = "sessionName";
	private static final String HOME = "home";
	
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Model getBasePage(Model model, HttpServletRequest request) {

		logger.info("Get base page : START");

		HttpSession session = request.getSession();

		if (session.getAttribute(SESSION_NAME) != null && request.getSession().getAttribute(HOME) != null) {
			if (session.getAttribute(ROLE).toString().equals(model.asMap().get(ROLE).toString())) {
				model.addAttribute(VIEW, model.asMap().get(REQ_PAGE).toString());
			} else if (model.asMap().get(REQ_PAGE).toString().equalsIgnoreCase(LOGOUT)) {
				model.addAttribute(VIEW, LOGOUT);
			} else {
				model.addAttribute(VIEW, session.getAttribute(NEXT_PAGE).toString());
			}
		} else if (model.asMap().get(REQ_PAGE).toString().equalsIgnoreCase(HOMEPAGE)) {
			model.addAttribute(VIEW, HOMEPAGE);
		} else {
			model.addAttribute(VIEW, REDIRECT_HOMEPAGE);
		}

		logger.info("Get base page : END");
		return model;
	}


}
