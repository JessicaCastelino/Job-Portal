package com.dal.mycareer.imodel;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface IRoleModel {

	public Model getBasePage(Model model, HttpServletRequest request);
}
