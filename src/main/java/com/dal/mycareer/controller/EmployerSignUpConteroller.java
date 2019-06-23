package com.dal.mycareer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dal.mycareer.DAO.Impl.EmployerSignupDAO;
import com.dal.mycareer.DAO.Interface.IEmployerSignupDAO;
import com.dal.mycareer.DTO.EmployerSignupDTO;
import com.dal.mycareer.imodel.IEmployerSignupModel;
import com.dal.mycareer.model.EmployerSignupModel;

@Controller
public class EmployerSignUpConteroller {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/employerSignup")
	public String submitEmployerRequest(@Valid @ModelAttribute("esignup") EmployerSignupDTO esignup,
			BindingResult result, Model model, HttpServletRequest request) {
		LOGGER.info("Submit employer's request -- Started");

		IEmployerSignupModel empSignupModel = new EmployerSignupModel();

		IEmployerSignupDAO eSignupDAO = new EmployerSignupDAO();

		model = empSignupModel.submitEmployerRequest(model, esignup, eSignupDAO);

		LOGGER.info("Submit employer's request -- End");
		return model.asMap().get("view").toString();
	}
}
