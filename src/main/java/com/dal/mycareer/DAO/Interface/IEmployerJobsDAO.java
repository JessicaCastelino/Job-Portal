package com.dal.mycareer.DAO.Interface;

import java.util.List;

import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;


public interface IEmployerJobsDAO 
{
	public List<Job> getActiveJobs(String username, List<Job> jobs);
	public JobDetails InsertJobDetails(JobDetails postedJobDetails, String currentUser);
	public List<Job> getClosedJobs(String username, List<Job> jobs);
	public JobDetails viewPostedJobDetails(JobDetails jobDetails);
	public boolean updatejobDetails(JobDetails updatedJobDetails);
}
