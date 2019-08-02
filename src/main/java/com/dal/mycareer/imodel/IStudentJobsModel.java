package com.dal.mycareer.imodel;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.dal.mycareer.DAO.Interface.IStudentDetailsDAO;
import com.dal.mycareer.DAO.Interface.IStudentJobsDAO;

import org.springframework.ui.Model;

public interface IStudentJobsModel {

	public Model fetchStudentSpecificJobs(Model model, HttpServletRequest request, IStudentJobsDAO dao, IStudentDetailsDAO studentDetailsDao) throws SQLException;
	public Model filterStudentSpecificJobList(Model model, HttpServletRequest request, String location, String jobType);
}
