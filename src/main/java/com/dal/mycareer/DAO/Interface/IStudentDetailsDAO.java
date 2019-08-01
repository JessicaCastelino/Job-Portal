package com.dal.mycareer.DAO.Interface;

import java.sql.SQLException;

import com.dal.mycareer.DTO.Student;

public interface IStudentDetailsDAO
{
	public Student getStudentDetails(String userSessionName) throws SQLException;

}
