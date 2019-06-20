package com.dal.mycareer.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dal.mycareer.DAO.Interface.IEmployerJobsDAO;
import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.imodel.IEmployerJobsModel;

@Service
public class EmployerJobsModel implements IEmployerJobsModel {
	@Autowired
	IEmployerJobsDAO employerJobsDAO;
	
	public List<Job> getActiveJobs(int employeeId) {
		return employerJobsDAO.getActiveJobs(employeeId);
	}
}
