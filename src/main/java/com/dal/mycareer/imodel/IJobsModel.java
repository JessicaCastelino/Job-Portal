package com.dal.mycareer.imodel;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.dal.mycareer.DAO.Interface.IJobsDAO;

import org.springframework.ui.Model;

public interface IJobsModel {
	
    public boolean updateJobStatus(int jobRecordId, boolean jobStatus);
    public Model fetchJob(Model model, int jobId, HttpServletRequest request, IJobsDAO dao) throws SQLException;
}