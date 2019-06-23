package com.dal.mycareer.DAO.Interface;

import java.util.List;

import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;


public interface IEmployerJobsDAO 
{
	public List<Job> getActiveJobs(String username);
	public JobDetails InsertJobDetails(JobDetails postedJobDetails );
	public List<Job> getClosedJobs(String username);
	public JobDetails viewPostedJobDetails(int jobId);
	public boolean updatejobDetails(JobDetails updatedJobDetails);
}
