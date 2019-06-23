package com.dal.mycareer.imodel;

import org.springframework.ui.Model;

import com.dal.mycareer.DAO.Interface.IEmployerSignupDAO;
import com.dal.mycareer.DTO.EmployerSignupDTO;

public interface IEmployerSignupModel {
	public Model submitEmployerRequest(Model model, EmployerSignupDTO eSignUp, IEmployerSignupDAO eSignupDAO);
}
