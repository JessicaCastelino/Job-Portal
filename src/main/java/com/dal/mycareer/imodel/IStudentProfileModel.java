package com.dal.mycareer.imodel;

import org.springframework.ui.Model;

import com.dal.mycareer.DAO.Interface.IStudentProfileDAO;

public interface IStudentProfileModel {

	public Model getStudentProfile(Model model, String email, IStudentProfileDAO studProfileDAO);

}
