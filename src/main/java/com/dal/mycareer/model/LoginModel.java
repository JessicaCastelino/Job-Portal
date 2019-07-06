package com.dal.mycareer.model;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.dal.mycareer.DAO.Interface.ILoginDAO;
import com.dal.mycareer.DTO.UserLogin;
import com.dal.mycareer.imodel.ILoginModel;
import com.dal.mycareer.propertiesparser.PropertiesParser;

public class LoginModel implements ILoginModel {

	private static final String REDIRECT = "redirect:/";

	// To verify if the user is authentic and authorized to view the page
	public Model verifyLogin(Model model, UserLogin ulogin, ILoginDAO loginDAO, HttpServletRequest request) {

		boolean isValidUser = loginDAO.isValidUser(ulogin);
		if (isValidUser && ulogin.getRole().equalsIgnoreCase("employer")) {
			model.addAttribute("nextPage", REDIRECT + PropertiesParser.getPropertyMap().getProperty("employerHome"));
			model.addAttribute("isValidUser", isValidUser);
			model.addAttribute("view", PropertiesParser.getPropertyMap().getProperty("employerHome"));
			model.addAttribute("role","employer");
		} else if (isValidUser && ulogin.getRole().equalsIgnoreCase("student")) {
			model.addAttribute("nextPage", REDIRECT + PropertiesParser.getPropertyMap().getProperty("studentHome"));
			model.addAttribute("isValidUser", isValidUser);
			model.addAttribute("view", PropertiesParser.getPropertyMap().getProperty("studentHome"));
			model.addAttribute("role","student");
		} else if (isValidUser && ulogin.getRole().equalsIgnoreCase("co-op admin")) {
			model.addAttribute("nextPage", REDIRECT + PropertiesParser.getPropertyMap().getProperty("adminHome"));
			model.addAttribute("isValidUser", isValidUser);
			model.addAttribute("view", PropertiesParser.getPropertyMap().getProperty("adminHome"));
			model.addAttribute("role","admin");
		} else {
			model.addAttribute("nextPage", REDIRECT + PropertiesParser.getPropertyMap().getProperty("homepage"));
			model.addAttribute("isValidUser", isValidUser);
			model.addAttribute("view", PropertiesParser.getPropertyMap().getProperty("homepage"));
			model.addAttribute("error", " you have entered invalid username, password or role !");
			model.addAttribute("isInvalid", true);
		}

		return model;
	}

}
