package com.dal.mycareer.imodel;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.dal.mycareer.DAO.Interface.IStudentApplicationDAO;
import com.dal.mycareer.DAO.Interface.IStudentDetailsDAO;
import com.dal.mycareer.DAO.Interface.IStudentJobsDAO;

public interface IStudentApplicationModel
{
	public Model submitJobApplication(Model model, MultipartFile file, HttpServletRequest request, int jobId, IStudentDetailsDAO studentDao, IStudentApplicationDAO studentApplicationDao, IStudentJobsDAO studentJobDao) throws SQLException, IOException;
	public Model withdrawJobApplication(Model model, int jobId, HttpServletRequest request, IStudentDetailsDAO studentDao, IStudentApplicationDAO studentApplicationDao, IStudentJobsDAO studentJobDao) throws SQLException;
	public Model checkApplicationExists(Model model, HttpServletRequest request, int jobId, IStudentDetailsDAO studentDao, IStudentApplicationDAO studentApplicationDao) throws SQLException;
}
