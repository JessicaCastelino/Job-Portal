package com.dal.mycareer.imodel;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.dal.mycareer.DAO.Interface.IStudentDAO;

public interface IStudentModel {

	public Model fetchJobs(Model model, HttpServletRequest request, IStudentDAO dao) throws SQLException;

	public Model viewJobs(Model model, int jobId, HttpServletRequest request, IStudentDAO dao);

	public Model applyJob(Model model, MultipartFile file, HttpServletRequest request, int jobId, IStudentDAO dao) throws SQLException;

	public Model withdrawApplication(Model model, int jobId, HttpServletRequest request, IStudentDAO dao) throws SQLException;

	public Model filterJobs(Model model, HttpServletRequest request, String location, String jobType, IStudentDAO dao);
	
	public Model jobApplicationExists(Model model, HttpServletRequest request, int jobId, IStudentDAO dao) throws SQLException;
}
