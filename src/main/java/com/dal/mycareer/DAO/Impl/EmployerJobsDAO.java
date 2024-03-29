package com.dal.mycareer.DAO.Impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dal.mycareer.DAO.Interface.IEmployerJobsDAO;
import com.dal.mycareer.DAO.Interface.IPrerequisiteCoursesDAO;
import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.JDBC.InsertHandler;
import com.dal.mycareer.JDBC.JdbcManager;
import com.dal.mycareer.JDBC.SelectHandler;
import com.dal.mycareer.JDBC.UpdateHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployerJobsDAO implements IEmployerJobsDAO 
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private Map <String, Integer> procResults;
	
	@Autowired
	private IPrerequisiteCoursesDAO preReqDAO;
	
	@Override
	public List<Job> getActiveJobs(String username, List<Job> jobs) throws SQLException
	{
		return fetchJobByStatus(username, true, jobs);
	}
	
	@Override
	public JobDetails InsertJobDetails(JobDetails postedJobDetails, String currentUser) throws SQLException
	{
		logger.debug("DL: InsertJobDetails method started");
		try
		{
			Map<String, Object> additionalParam = new HashMap<>();
			additionalParam.put("emailId", currentUser);
			JdbcManager jdbcManager = new InsertHandler(); 
			procResults = jdbcManager.executeProcedure("{call sp_insertjobdetails(?,?,?,?,?,?,?,?,?,?)}",
						"jobDetailsMapper", postedJobDetails, additionalParam);
			if(procResults.get("rowsAffected") > 0) 
			{
					int jobId = procResults.get("10");
					preReqDAO.insertJobPrerequisiteCourses(jobId, postedJobDetails.getSelectedCourseIds());
			} 
			else 
			{
					logger.error("Error Occurred in InsertJobDetails while inserting record");
			}
			logger.debug("DL: InsertJobDetails method ended");
		} 
		catch (Exception ex) 
		{
			logger.error("Error Occurred in InsertJobDetails :" + ex.getMessage());
			throw new SQLException("Error in InsertJobDetails");
		}
		return postedJobDetails;
	}

	public JobDetails viewPostedJobDetails(JobDetails jobDetails) throws SQLException
	{
		logger.debug("DL: viewPostedJobDetails method started");
		try
		{
			JdbcManager jdbcManager = new SelectHandler();
			Map<String, Object> inputParameters = new HashMap<>();
			inputParameters.put("jobId", jobDetails.getId());
			jdbcManager.executeProcedure("{CALL getPostedJobDetails(?)}", "jobDetailsMapper", jobDetails, inputParameters);
			logger.info("DL: viewPostedJobDetails method ended");
		}
		catch(Exception ex)
		{
			logger.error( "Error Occurred in viewPostedJob :" + ex.getMessage());
			throw new SQLException("Error in viewPostedJobDetails");
		}
		return jobDetails;
	}

	@Override
	public List<Job> getClosedJobs(String username, List<Job> jobs) throws SQLException
	{
		logger.info("DL: getClosedJobs method started");
		return fetchJobByStatus(username, false, jobs);		
	}

	private List<Job> fetchJobByStatus(String username, boolean isActive, List<Job> jobs)  throws SQLException
	{
		String procedureName = "";
		logger.info("fetchJobByStatus method started");
		if(isActive)
		{
			logger.info("Setting the procedure name for getting Active jobs -fetchJobByStatus Method");
			procedureName = "getActiveJobsForEmployer";
		}
		else
		{
			logger.info("Setting the procedure name for getting closed jobs -fetchJobByStatus Method");
			procedureName = "getClosedJobsForEmployer";
		}
		try 
		{
			JdbcManager jdbcManager = new SelectHandler();
			Map<String, Object> inputParameters = new HashMap<>();
			inputParameters.put("employerUserName", username);
			jdbcManager.executeProcedure("{CALL " + procedureName + "(?)}", "jobsMapper", jobs, inputParameters);
		} 
		catch (Exception e) 
		{
			logger.error("Exception occurred at EmployerJobsDAO:fetchJobByStatus " + e.getMessage());
			throw new SQLException("Error in fetchJobByStatus");
		}
		return jobs;
	}

	@Override
	public boolean updatejobDetails(JobDetails updatedJobDetails) throws SQLException
	{
		boolean isJobDetailsUpdated = false;
		logger.debug("DL: updatejobDetails method started");
		try
		{
			JdbcManager jdbcManager = new UpdateHandler();
			Map<String, Object> inputParameters = new HashMap<>();
			inputParameters.put("jobId", updatedJobDetails.getId());
			Map<String, Integer> res = jdbcManager.executeProcedure("{CALL updatejobdetails (?,?,?,?,?,?,?,?,?)}", "jobDetailsMapper", updatedJobDetails, inputParameters);
			int rowAffected = res.get("rowsAffected");
			if (rowAffected > 0)
			{
				isJobDetailsUpdated = preReqDAO.insertJobPrerequisiteCourses(updatedJobDetails.getId(), updatedJobDetails.getSelectedCourseIds());
			}
			else
		 	{
				isJobDetailsUpdated = false;
				logger.error( "Error Occurred in updatejobDetails while updating record");
			 }
			 logger.debug("DL: updatejobDetails method ended");
		}
		catch(Exception ex)
		{
			logger.error( "Error Occurred in updatejobDetails :" + ex.getMessage());
			throw new SQLException("Error in updatejobDetails");
		}
		return isJobDetailsUpdated;
	}
	

}
