package com.dal.mycareer.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dal.mycareer.DAO.Interface.IEmployerJobsDAO;
import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.imodel.IEmployerJobsModel;

@Service
public class EmployerJobsModel implements IEmployerJobsModel {
	@Autowired
	IEmployerJobsDAO employerJobsDAO;
	
	public List<Job> getActiveJobs(int employerId) 
	{
		return employerJobsDAO.getActiveJobs(employerId);
	}
	
	public JobDetails InsertJobDetails(JobDetails postedJobDetails) {
		return employerJobsDAO.InsertJobDetails(postedJobDetails);
	}

	@Override
	public boolean updateJobStatus(int jobRecordId) 
	{
		return employerJobsDAO.updateJobStatus(jobRecordId);
	}

	@Override
	public List<Job> getClosedJobs(int employerId) {
		return employerJobsDAO.getClosedJobs(employerId);
	}
}
