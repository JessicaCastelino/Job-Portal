package com.dal.mycareer.model;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.dal.mycareer.DAO.Interface.ILoginDAO;
import com.dal.mycareer.DTO.UserLogin;
import com.dal.mycareer.imodel.ILoginModel;
import com.dal.mycareer.propertiesparser.PropertiesParser;

public class LoginModel implements ILoginModel {

	private static final String REDIRECT = "redirect:/";

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// To verify if the user is authentic and authorized to view the page
	public Model verifyLogin(Model model, UserLogin ulogin, ILoginDAO loginDAO, HttpServletRequest request) {
		
		logger.info("Verify Login : START");
		
		boolean isValidUser = loginDAO.isValidUser(ulogin);
		if (isValidUser && ulogin.getRole().equalsIgnoreCase("employer")) {
			model.addAttribute("nextPage", REDIRECT + PropertiesParser.getPropertyMap().getProperty("employerHome"));
			model.addAttribute("isValidUser", isValidUser);
			model.addAttribute("view", PropertiesParser.getPropertyMap().getProperty("employerHome"));
			model.addAttribute("role", "employer");

			logger.debug(ulogin.getUsername() + "is loggedIn as" + ulogin.getRole());

		} else if (isValidUser && ulogin.getRole().equalsIgnoreCase("student")) {
			model.addAttribute("nextPage", REDIRECT + PropertiesParser.getPropertyMap().getProperty("studentHome"));
			model.addAttribute("isValidUser", isValidUser);
			model.addAttribute("view", PropertiesParser.getPropertyMap().getProperty("studentHome"));
			model.addAttribute("role", "student");

			logger.debug(ulogin.getUsername() + " is loggedIn as " + ulogin.getRole());

		} else if (isValidUser && ulogin.getRole().equalsIgnoreCase("co-op Admin")) {
			model.addAttribute("nextPage", REDIRECT + PropertiesParser.getPropertyMap().getProperty("adminHome"));
			model.addAttribute("isValidUser", isValidUser);
			model.addAttribute("view", PropertiesParser.getPropertyMap().getProperty("adminHome"));
			model.addAttribute("role", "admin");

			logger.debug(ulogin.getUsername() + " is loggedIn as " + ulogin.getRole());

		} else {
			model.addAttribute("nextPage", REDIRECT + PropertiesParser.getPropertyMap().getProperty("homepage"));
			model.addAttribute("isValidUser", isValidUser);
			model.addAttribute("view", PropertiesParser.getPropertyMap().getProperty("homepage"));
			model.addAttribute("error", " you have entered invalid username, password or role !");
			model.addAttribute("isInvalid", true);

			logger.debug("Invalid login for : " + ulogin.getUsername());
		}

		logger.info("Verify Login : END");
		
		return model;
	}

}
