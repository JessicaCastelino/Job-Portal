package com.dal.mycareer.imodel;

import java.util.List;

import com.dal.mycareer.DTO.Application;

public interface IManageApplicationsModel 
{
	public List<Application> getApplications(int jobRecordId);

	public boolean updateApplicationStatus(int applicationId, String appStatus);
	
	//public boolean downloadFile(int applicationId);
}