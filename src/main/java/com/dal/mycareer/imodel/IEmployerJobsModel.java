package com.dal.mycareer.imodel;

import java.util.List;

import com.dal.mycareer.DTO.Job;

public interface IEmployerJobsModel {
	public List<Job> getActiveJobs(int employeeId);
}
