package com.dal.mycareer.model;

import java.util.ArrayList;
import java.util.List;

import com.dal.mycareer.DAO.Interface.IEmployerJobsDAO;
import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.imodel.IEmployerJobsModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployerJobsModel implements IEmployerJobsModel 
{
	@Autowired
	private IEmployerJobsDAO employerJobsDAO;

	List<Job> jobs = null; 
	private final Logger logger = LoggerFactory.getLogger(this.getClass());	
	public EmployerJobsModel()
	{

	}

	public EmployerJobsModel(IEmployerJobsDAO employerJobsDAO)
	{
		this.employerJobsDAO = employerJobsDAO;
	}

	public List<Job> getActiveJobs(String username) 
	{
		logger.info("BL: getActiveJobs method started for user-" + username);
		jobs = new ArrayList<>();
		return employerJobsDAO.getActiveJobs(username, jobs);
	}
	
	@Override
	public JobDetails InsertJobDetails(JobDetails postedJobDetails,String currentUser) 
	{
		logger.info("BL: InsertJobDetails method started for user-" + currentUser);
		return employerJobsDAO.InsertJobDetails(postedJobDetails, currentUser);
	}

	@Override
	public List<Job> getClosedJobs(String username) 
	{
		logger.info("BL: getClosedJobs method started for user-" + username);
		jobs = new ArrayList<>();
		return employerJobsDAO.getClosedJobs(username, jobs);
	}
	
	@Override
	public JobDetails viewPostedJobDetails(int jobId) 
	{
		logger.info("BL: viewPostedJobDetails method started for jobId-" + jobId);
		JobDetails jobDetails = new JobDetails();
		jobDetails.setId(jobId);
		return employerJobsDAO.viewPostedJobDetails(jobDetails);
	}

	@Override
	public boolean updateJobDetails(JobDetails updatedJobDetails)
	{
		logger.info("BL: updateJobDetails method started for jobId-" + updatedJobDetails.getId());
		return employerJobsDAO.updatejobDetails(updatedJobDetails);
	}
}
