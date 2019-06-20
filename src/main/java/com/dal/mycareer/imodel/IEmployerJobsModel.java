package com.dal.mycareer.imodel;

import java.util.List;

import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;

public interface IEmployerJobsModel {
	public List<Job> getActiveJobs(int employeeId);
	public JobDetails InsertJobDetails(JobDetails postedJobDetails );
}
