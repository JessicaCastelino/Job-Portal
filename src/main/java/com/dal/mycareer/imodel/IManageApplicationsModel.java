package com.dal.mycareer.imodel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.dal.mycareer.DTO.Application;

public interface IManageApplicationsModel 
{
	public List<Application> getApplications(int jobRecordId);

	public boolean updateApplicationStatus(int applicationId, String appStatus);
	
	public void downloadFile(int applicationId, HttpServletResponse response) throws IOException, SQLException;
}