package com.dal.mycareer.model;

import java.util.List;

import com.dal.mycareer.DAO.Interface.IManageApplicationsDAO;
import com.dal.mycareer.DTO.Application;
import com.dal.mycareer.imodel.IManageApplicationsModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ManageApplicantsModel implements IManageApplicationsModel {
	@Autowired
	IManageApplicationsDAO applicationsManagerDAO;

	@Override
	public List<Application> getApplications(int jobRecordId) {
		return applicationsManagerDAO.getApplications(jobRecordId);
	}

	@Override
	public boolean updateApplicationStatus(int applicationId, String appStatus) {
		return applicationsManagerDAO.updateApplicationStatus(applicationId, appStatus);
	}
	
}
