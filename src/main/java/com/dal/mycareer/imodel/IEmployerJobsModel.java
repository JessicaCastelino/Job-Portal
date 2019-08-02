package com.dal.mycareer.imodel;

import java.sql.SQLException;
import java.util.List;

import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;

public interface IEmployerJobsModel 
{
	public List<Job> getActiveJobs(String username) throws SQLException;
	public JobDetails InsertJobDetails(JobDetails postedJobDetails,String currentUser ) throws SQLException;
	public List<Job> getClosedJobs(String username) throws SQLException;
	public JobDetails viewPostedJobDetails (int jobId) throws SQLException;
	public boolean updateJobDetails(JobDetails updatedJobDetails) throws SQLException;
}
