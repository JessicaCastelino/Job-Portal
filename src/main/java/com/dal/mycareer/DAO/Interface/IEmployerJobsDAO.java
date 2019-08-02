package com.dal.mycareer.DAO.Interface;

import java.sql.SQLException;
import java.util.List;

import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;


public interface IEmployerJobsDAO 
{
	public List<Job> getActiveJobs(String username, List<Job> jobs) throws SQLException;
	public JobDetails InsertJobDetails(JobDetails postedJobDetails, String currentUser) throws SQLException;
	public List<Job> getClosedJobs(String username, List<Job> jobs) throws SQLException;
	public JobDetails viewPostedJobDetails(JobDetails jobDetails) throws SQLException;
	public boolean updatejobDetails(JobDetails updatedJobDetails) throws SQLException;
}
