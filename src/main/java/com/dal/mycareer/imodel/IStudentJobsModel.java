package com.dal.mycareer.imodel;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.dal.mycareer.DAO.Interface.IStudentDetailsDAO;
import com.dal.mycareer.DAO.Interface.IStudentJobsDAO;

public interface IStudentJobsModel {

	public Model fetchStudentSpecificJobs(Model model, HttpServletRequest request, IStudentJobsDAO dao, IStudentDetailsDAO studentDetailsDao) throws SQLException;
	public Model filterStudentSpecificJobList(Model model, HttpServletRequest request, String location, String jobType);
}
