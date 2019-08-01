package com.dal.mycareer.imodel;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.dal.mycareer.DAO.Interface.IJobsDAO;
import com.dal.mycareer.DAO.Interface.IStudentJobsDAO;

public interface IJobsModel {
	
    public boolean updateJobStatus(int jobRecordId, boolean jobStatus);
    public Model fetchJob(Model model, int jobId, HttpServletRequest request, IJobsDAO dao) throws SQLException;
}