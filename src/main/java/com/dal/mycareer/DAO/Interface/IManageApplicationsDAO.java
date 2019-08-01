package com.dal.mycareer.DAO.Interface;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import com.dal.mycareer.DTO.Application;


public interface IManageApplicationsDAO 
{
	List<Application> getApplications(int jobRecordId);

	boolean updateApplicationStatus(int applicationId, String appStatus);
	public InputStream fetchDocument(int applicationId) throws SQLException;
}
