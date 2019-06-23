package com.dal.mycareer.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dal.mycareer.DAO.Interface.IEmployerJobsDAO;
import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.imodel.IEmployerJobsModel;

@Service
public class EmployerJobsModel implements IEmployerJobsModel 
{
	@Autowired
	IEmployerJobsDAO employerJobsDAO;
	
	public List<Job> getActiveJobs(String username) 
	{
		return employerJobsDAO.getActiveJobs(username);
	}
	@Override
	public JobDetails InsertJobDetails(JobDetails postedJobDetails) 
	{
		return employerJobsDAO.InsertJobDetails(postedJobDetails);
	}

	@Override
	public List<Job> getClosedJobs(String username) {
		return employerJobsDAO.getClosedJobs(username);
	}
	
	@Override
	public JobDetails viewPostedJobDetails(int jobId) 
	{
		return employerJobsDAO.viewPostedJobDetails(jobId);
	}

	@Override
	public boolean updateJobDetails(JobDetails updatedJobDetails)
	{
		return employerJobsDAO.updatejobDetails(updatedJobDetails);
	}
}
