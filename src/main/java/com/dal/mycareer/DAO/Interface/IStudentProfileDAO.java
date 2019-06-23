package com.dal.mycareer.DAO.Interface;

import com.dal.mycareer.DTO.StudentProfileDTO;

public interface IStudentProfileDAO {

	public StudentProfileDTO getStudentProfile(String email);

}
