package com.dal.mycareer.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.dal.mycareer.imodel.IRoleModel;

public class RoleModel implements IRoleModel {

	@Override
	public Model getBasePage(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();

		if (session.getAttribute("sessionName") != null && request.getSession().getAttribute("home") != null) {
			if (session.getAttribute("role").toString().equals(model.asMap().get("role").toString())) {
				model.addAttribute("view", model.asMap().get("reqPage").toString());
			} else {
				model.addAttribute("view", session.getAttribute("nextPage").toString());
			}
		} else if (model.asMap().get("reqPage").toString().equalsIgnoreCase("homepage")) {
			model.addAttribute("view", "homepage");
		} else {
			model.addAttribute("view", "redirect:/homepage");
		}

		return model;
	}

}
