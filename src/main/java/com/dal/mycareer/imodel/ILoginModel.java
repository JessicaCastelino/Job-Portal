package com.dal.mycareer.imodel;

import org.springframework.ui.Model;

import com.dal.mycareer.DAO.Interface.ILoginDAO;
import com.dal.mycareer.DTO.UserLogin;

public interface ILoginModel {
	
	//To verify if the user is authentic and authorized to view the page 
	public Model verifyLogin(Model model, UserLogin ulogin,ILoginDAO loginDAO);

}
