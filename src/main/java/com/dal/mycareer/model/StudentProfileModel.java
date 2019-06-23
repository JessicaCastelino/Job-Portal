package com.dal.mycareer.model;

import org.springframework.ui.Model;

import com.dal.mycareer.DAO.Interface.IStudentProfileDAO;
import com.dal.mycareer.DTO.StudentProfileDTO;
import com.dal.mycareer.imodel.IStudentProfileModel;

public class StudentProfileModel implements IStudentProfileModel {

	public Model getStudentProfile(Model model, String email, IStudentProfileDAO studProfileDAO) {

		StudentProfileDTO spDTO = studProfileDAO.getStudentProfile(email);

		model.addAttribute("profileData", spDTO);

		return model;
	}
}
