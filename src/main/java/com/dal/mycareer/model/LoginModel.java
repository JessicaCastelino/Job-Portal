package com.dal.mycareer.model;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.dal.mycareer.DAO.Interface.ILoginDAO;
import com.dal.mycareer.DTO.UserLogin;
import com.dal.mycareer.imodel.ILoginModel;

public class LoginModel implements ILoginModel {

	// To verify if the user is authentic and authorized to view the page
	public Model verifyLogin(Model model, UserLogin ulogin, ILoginDAO loginDAO, HttpServletRequest request) {

		boolean isValidUser = loginDAO.isValidUser(ulogin);
		if (isValidUser && ulogin.getRole().equalsIgnoreCase("employer")) {
			model.addAttribute("nextPage", "redirect:/employerHome");
			model.addAttribute("isValidUser", isValidUser);
		} else if (isValidUser && ulogin.getRole().equalsIgnoreCase("student")) {
			model.addAttribute("nextPage", "redirect:/studentHome");
			model.addAttribute("isValidUser", isValidUser);
		} else if (isValidUser && ulogin.getRole().equalsIgnoreCase("co-opAdmin")) {
			model.addAttribute("nextPage", "redirect:/adminHome");
			model.addAttribute("isValidUser", isValidUser);
		} else {
			model.addAttribute("nextPage", "redirect:/homepage");
			model.addAttribute("isValidUser", isValidUser);
		}

		return model;
	}

}
