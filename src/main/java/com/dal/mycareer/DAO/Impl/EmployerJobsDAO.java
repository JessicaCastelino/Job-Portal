package com.dal.mycareer.DAO.Impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dal.mycareer.DAO.Interface.IEmployerJobsDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;

@Repository
public class EmployerJobsDAO implements IEmployerJobsDAO {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<Job> getActiveJobs(String username) {
		return fetchJobByStatus(username, true);
	}
	
	@Override
	public JobDetails InsertJobDetails(JobDetails postedJobDetails) 
	{
		CallableStatement callStatement = null;
		Connection con= null;
		try
		{
		 con  = DatabaseConnection.getConnection();
		 callStatement = con.prepareCall("{call sp_insertjobdetails(?,?,?,?,?,?,?,?)}"); 
		 callStatement.setString("jobTitle", postedJobDetails.getJobTitle());
		 callStatement.setString("jobLocation", postedJobDetails.getLocation());
		 callStatement.setString("jobType", postedJobDetails.getJobType());	
		 callStatement.setString("noOfPosition", Integer.toString(postedJobDetails.noOfPosition));
		 callStatement.setString("rateOfPay", Integer.toString(postedJobDetails.rateOfPay));
		 callStatement.setString("hourPerWeek", Integer.toString(postedJobDetails.hourPerWeek));
		 callStatement.setString("jobDescription", postedJobDetails.jobDescription);
		 callStatement.registerOutParameter(8, java.sql.Types.INTEGER);
		 int rowsAffected = callStatement.executeUpdate();
		 if (rowsAffected > 0)
		 {
		 int jobId = callStatement.getInt(8);
		 insertJobRequirement(jobId,postedJobDetails.getSelectedCourseIds());
		 }
		 else
		 {
			LOGGER.error( "Error Occurred in InsertJobDetails while inserting record");
		 }
		}
		catch(Exception ex)
		{
			LOGGER.error( "Error Occurred in InsertJobDetails :" + ex.getMessage());
		}
		
		return postedJobDetails;
	}

	public JobDetails viewPostedJobDetails(int jobId)
	{
		CallableStatement callStatement = null;
		Connection con= null;
		JobDetails jobDetails=null;
		try
		{
			jobDetails = new JobDetails();
			con  = DatabaseConnection.getConnection();
			callStatement = con.prepareCall("{CALL getPostedJobDetails(?)}");
			callStatement.setInt("jobId", jobId);
			ResultSet result = callStatement.executeQuery();
			while(result.next())
			{
			jobDetails.setId(result.getInt("id"));
			jobDetails.setJobTitle(result.getString("jobTitle"));
			jobDetails.setLocation(result.getString("location"));
			jobDetails.setNoOfPosition(result.getInt("openPosition"));
			jobDetails.setJobType(result.getString("jobType"));
			jobDetails.setRateOfPay(result.getInt("rateofPay"));
			jobDetails.setHourPerWeek(result.getInt("hoursPerWeek"));
			jobDetails.setApplicationDeadline(result.getDate("applicationDeadline"));
			jobDetails.setJobDescription(result.getString("jobDescription"));
			}
		}
		catch(Exception ex)
		{
			LOGGER.error( "Error Occurred in viewPostedJob :" + ex.getMessage());
		}
		return jobDetails;
	}

	@Override
	public List<Job> getClosedJobs(String username) {
		return fetchJobByStatus(username, false);		
	}

	List<Job> fetchJobByStatus(String username, boolean isActive) {
		List<Job> jobs = null;
		Connection conn = null;
		String procedureName = "";
		if(isActive)
		{
			procedureName = "getActiveJobsForEmployer";
		}
		else
		{
			procedureName = "getClosedJobsForEmployer";
		}
		try {
			conn = DatabaseConnection.getConnection();
			CallableStatement statement = conn.prepareCall("{CALL " + procedureName +"(?)}");
			statement.setString("employerUserName", username);
			ResultSet result = statement.executeQuery();
			Job job;
			jobs = new ArrayList<>();
			while(result.next()) {
				job = new Job();
				job.setId(result.getInt("id"));
				job.setJobTitle(result.getString("jobTitle"));
				job.setJobType(result.getString("jobType"));
				job.setLocation(result.getString("location"));
				job.setOrganization(result.getString("organization"));
				job.setApplicationDeadline(result.getDate("applicationDeadline"));
				job.setRequiredCourses(result.getString("requiredCourses"));
				jobs.add(job);
			}
		} catch (SQLException e) {
			LOGGER.error("Exception occurred at EmployerJobsDAO:fetchJobByStatus " + e.getMessage());
			e.printStackTrace();
		}
		return jobs;
	}

	@Override
	public boolean updatejobDetails(JobDetails updatedJobDetails) {
		boolean isJobDetailsUpdated = false;
		CallableStatement callStatement = null;
		Connection con= null;
		try
		{
			con = DatabaseConnection.getConnection();
			callStatement = con.prepareCall("{CALL updatejobdetails (?,?,?,?,?,?,?,?)}");
			callStatement.setString("jobId", Integer.toString(updatedJobDetails.getId()));
			callStatement.setString("jobTitle", updatedJobDetails.getJobTitle());
			callStatement.setString("location", updatedJobDetails.getLocation());
			callStatement.setString("jobType", updatedJobDetails.getJobType());	
			callStatement.setString("noOfPosition", Integer.toString(updatedJobDetails.noOfPosition));
			callStatement.setString("rateOfPay", Integer.toString(updatedJobDetails.rateOfPay));
			callStatement.setString("hourPerWeek", Integer.toString(updatedJobDetails.hourPerWeek));
			callStatement.setString("jobDescription", updatedJobDetails.jobDescription);
			int rowAffected = callStatement.executeUpdate();
			
			if (rowAffected > 0)
			{
				isJobDetailsUpdated = insertJobRequirement(updatedJobDetails.getId(),updatedJobDetails.getSelectedCourseIds());
			}
			else
		 	{
				isJobDetailsUpdated = false;
			LOGGER.error( "Error Occurred in updatejobDetails while updating record");
		 	}
		}
		catch(Exception ex)
		{
			LOGGER.error( "Error Occurred in updatejobDetails :" + ex.getMessage());
		}
		return isJobDetailsUpdated;
	}
	public boolean insertJobRequirement(int jobId, List <Integer> prerequisiteCourses)
	{
		boolean isQuerySuccess = false;
		CallableStatement callStatement = null;
		Connection con= null;
		try
		{
			con = DatabaseConnection.getConnection();
			String courses = prerequisiteCourses.stream().map(n-> n.toString())
			.collect(Collectors.joining(","));
			System.out.println(courses);
			callStatement = con.prepareCall("{call insertjobRequirementRecord(?,?)}");
			callStatement.setString("courseIds",courses); 
			callStatement.setString("jobRecordId", Integer.toString(jobId));
			int rowAffected = callStatement.executeUpdate();
		// for (int courseId : prerequisiteCourses) 
		// {
		// 	callStatement = con.prepareCall("{call insertjobRequirementRecord(?,?)}");
		// 	callStatement.setString("jobRecordId", Integer.toString(jobId));
		// 	callStatement.setString("preReqCourseId",Integer.toString(courseId)); 
		// 	int rowAffected = callStatement.executeUpdate();
		// 	if (rowAffected > 0)
		// 	{
		// 		isQuerySuccess= true;
		// 	}
		// 	else
		//  	{
		// 		isQuerySuccess= false;
		// 	LOGGER.error( "Error Occurred in InsertJobDetails while inserting record");
		//  	}
		//  } 
		 }
		catch (Exception ex)
		{
			LOGGER.error( "Error Occurred in insertJobRequirement :" + ex.getMessage());
		}
		return isQuerySuccess;
	}
}
