package com.dal.mycareer.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.dal.mycareer.DAO.Interface.IStudentProfileDAO;
import com.dal.mycareer.DTO.StudentProfileDTO;
import com.dal.mycareer.imodel.IStudentProfileModel;

public class StudentProfileModel implements IStudentProfileModel {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public Model getStudentProfile(Model model, String email, IStudentProfileDAO studProfileDAO) {

		logger.info("Get student profile : START");
		
		StudentProfileDTO spDTO = studProfileDAO.getStudentProfile(email);

		model.addAttribute("profileData", spDTO);

		logger.info("Get student profile : END");
		return model;
	}
}
