package com.dal.mycareer.model;

import org.springframework.ui.Model;

import com.dal.mycareer.DAO.Interface.ILoginDAO;
import com.dal.mycareer.DTO.UserLogin;
import com.dal.mycareer.imodel.ILoginModel;

public class LoginModel implements ILoginModel {
	
	//To verify if the user is authentic and authorized to view the page 
	public Model verifyLogin(Model model, UserLogin ulogin,ILoginDAO loginDAO) {
		
		loginDAO.isValidUser(ulogin);
		
		return model;
	}

}
