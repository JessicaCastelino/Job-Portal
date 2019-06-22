package com.dal.mycareer.DAO.Interface;

import java.util.List;

import com.dal.mycareer.DTO.Application;


public interface IManageApplicationsDAO 
{
	List<Application> getApplications(int jobRecordId);
}
