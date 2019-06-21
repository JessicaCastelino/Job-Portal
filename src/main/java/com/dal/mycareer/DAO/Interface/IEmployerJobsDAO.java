package com.dal.mycareer.DAO.Interface;

import java.util.List;

import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;


public interface IEmployerJobsDAO {
	public List<Job> getActiveJobs(int employeeId);
	public JobDetails InsertJobDetails(JobDetails postedJobDetails );
	public boolean updateJobStatus(int jobRecordId);
}
