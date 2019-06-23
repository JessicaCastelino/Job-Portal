package com.dal.mycareer.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.dal.mycareer.DAO.Interface.IEmployerSignupDAO;
import com.dal.mycareer.DTO.EmployerSignupDTO;
import com.dal.mycareer.imodel.IEmployerSignupModel;

public class EmployerSignupModel implements IEmployerSignupModel {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public Model submitEmployerRequest(Model model, EmployerSignupDTO eSignUp, IEmployerSignupDAO eSignupDAO) {

		LOGGER.info("Submit employer's request MODEL -- Started");

		boolean successful = eSignupDAO.submitEmployerRequest(eSignUp);
		model.addAttribute("view", "empSignupConfirmation");
		if (successful) {
			model.addAttribute("success", successful);
		} else {
			model.addAttribute("success", successful);
		}

		LOGGER.info("Submit employer's request MODEL -- Ended");
		return model;

	}

}
