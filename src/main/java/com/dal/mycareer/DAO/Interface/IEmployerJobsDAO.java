package com.dal.mycareer.DAO.Interface;

import java.util.List;

import com.dal.mycareer.DTO.Job;



public interface IEmployerJobsDAO {
	public List<Job> getActiveJobs(int employeeId);
}
