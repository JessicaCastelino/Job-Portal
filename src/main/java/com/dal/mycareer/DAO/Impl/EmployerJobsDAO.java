package com.dal.mycareer.DAO.Impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dal.mycareer.DAO.Interface.IEmployerJobsDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class EmployerJobsDAO implements IEmployerJobsDAO 
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<Job> getActiveJobs(String username,List<Job> jobs) 
	{
		return fetchJobByStatus(username, true, jobs);
	}
	
	@Override
	public JobDetails InsertJobDetails(JobDetails postedJobDetails,String currentUser) 
	{
		CallableStatement callStatement = null;
		Connection con= null;
		logger.info("DL: InsertJobDetails method started");
		try
		{
		 con  = DatabaseConnection.getConnection();
		 callStatement = con.prepareCall("{call sp_insertjobdetails(?,?,?,?,?,?,?,?,?,?)}"); 
		 callStatement.setString("jobTitle", postedJobDetails.getJobTitle());
		 callStatement.setString("jobLocation", postedJobDetails.getLocation());
		 callStatement.setString("jobType", postedJobDetails.getJobType());	
		 callStatement.setString("noOfPosition", Integer.toString(postedJobDetails.noOfPosition));
		 callStatement.setString("rateOfPay", Integer.toString(postedJobDetails.rateOfPay));
		 callStatement.setString("hourPerWeek", Integer.toString(postedJobDetails.hourPerWeek));
		 callStatement.setString("jobDescription", postedJobDetails.jobDescription);
		 callStatement.setString("emailId", currentUser);
		 callStatement.setDate("applicationDeadline", postedJobDetails.getApplicationDeadline());
		 callStatement.registerOutParameter(10, java.sql.Types.INTEGER);
		 int rowsAffected = callStatement.executeUpdate();
		 if (rowsAffected > 0)
		 {
		 int jobId = callStatement.getInt(10);
		 insertJobRequirement(jobId,postedJobDetails.getSelectedCourseIds());
		 }
		 else
		 {
			logger.error( "Error Occurred in InsertJobDetails while inserting record");
		 }
		}
		catch(Exception ex)
		{
			logger.error( "Error Occurred in InsertJobDetails :" + ex.getMessage());
		}
		finally
		{
			DatabaseConnection.closeDatabaseComponents(callStatement);
		}
		return postedJobDetails;
	}

	public JobDetails viewPostedJobDetails(JobDetails jobDetails)
	{
		CallableStatement callStatement = null;
		Connection con= null;
		ResultSet result=null;
		List <Integer> lstCourseList = new ArrayList<Integer>();
		logger.info("DL: viewPostedJobDetails method started");
		try
		{
			con  = DatabaseConnection.getConnection();
			callStatement = con.prepareCall("{CALL getPostedJobDetails(?)}");
			callStatement.setInt("jobId", jobDetails.getId());
		    result = callStatement.executeQuery();
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
			String preRequisiteCourses = result.getString("prerequisitecourses");
			String[] courseArray = preRequisiteCourses.trim().split(",");			
			for (String course : courseArray)
			{
				lstCourseList.add(Integer.parseInt(course.trim()));	
			}
			jobDetails.setSelectedCourseIds(lstCourseList);
			}
		}
		catch(Exception ex)
		{
			logger.error( "Error Occurred in viewPostedJob :" + ex.getMessage());
		}
		finally
		{
			DatabaseConnection.closeDatabaseComponents(result, callStatement);
		}
		return jobDetails;
	}

	@Override
	public List<Job> getClosedJobs(String username, List<Job> jobs) 
	{
		logger.info("DL: getClosedJobs method started");
		return fetchJobByStatus(username, false, jobs);		
	}

	private List<Job> fetchJobByStatus(String username, boolean isActive, List<Job> jobs) 
	{
		Connection conn = null;
		String procedureName = "";
		ResultSet result  = null;
		CallableStatement statement = null;
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
			conn = DatabaseConnection.getConnection();
			statement = conn.prepareCall("{CALL " + procedureName +"(?)}");
			statement.setString("employerUserName", username);
			result = statement.executeQuery();
			Job job;
			while(result.next()) 
			{
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
		} 
		catch (SQLException e) 
		{
			logger.error("Exception occurred at EmployerJobsDAO:fetchJobByStatus " + e.getMessage());
		}
		finally
		{
			DatabaseConnection.closeDatabaseComponents(result, statement);
		}
		return jobs;
	}

	@Override
	public boolean updatejobDetails(JobDetails updatedJobDetails) 
	{
		boolean isJobDetailsUpdated = false;
		CallableStatement callStatement = null;
		Connection con= null;
		logger.info("DL: updatejobDetails method started");
		try
		{
			con = DatabaseConnection.getConnection();
			callStatement = con.prepareCall("{CALL updatejobdetails (?,?,?,?,?,?,?,?,?)}");
			callStatement.setString("jobId", Integer.toString(updatedJobDetails.getId()));
			callStatement.setString("jobTitle", updatedJobDetails.getJobTitle());
			callStatement.setString("location", updatedJobDetails.getLocation());
			callStatement.setString("jobType", updatedJobDetails.getJobType());	
			callStatement.setString("noOfPosition", Integer.toString(updatedJobDetails.noOfPosition));
			callStatement.setString("rateOfPay", Integer.toString(updatedJobDetails.rateOfPay));
			callStatement.setString("hourPerWeek", Integer.toString(updatedJobDetails.hourPerWeek));
			callStatement.setDate("applicationDeadline", (updatedJobDetails.getApplicationDeadline()));
			callStatement.setString("jobDescription", updatedJobDetails.jobDescription);
			int rowAffected = callStatement.executeUpdate();
			
			if (rowAffected > 0)
			{
				isJobDetailsUpdated = insertJobRequirement(updatedJobDetails.getId(),updatedJobDetails.getSelectedCourseIds());
			}
			else
		 	{
				isJobDetailsUpdated = false;
				logger.error( "Error Occurred in updatejobDetails while updating record");
		 	}
		}
		catch(Exception ex)
		{
			logger.error( "Error Occurred in updatejobDetails :" + ex.getMessage());
		}
		finally
		{
			DatabaseConnection.closeDatabaseComponents(callStatement);
		}
		return isJobDetailsUpdated;
	}
	public boolean insertJobRequirement(int jobId, List<Integer> prerequisiteCourses)
	{
		boolean isQuerySuccess = false;
		CallableStatement callStatement = null;
		Connection con= null;
		logger.info("DL: insertJobRequirement method started");
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
			if (rowAffected > 0)
			{
				isQuerySuccess= true;
				logger.info( "Records inserted successfully in insertJobRequirement Method");
			}
			else
		 	{
				isQuerySuccess= false;
				logger.error( "Error Occurred in insertJobRequirement method while inserting record");
		 	}	 
		 }
		catch (Exception ex)
		{
			logger.error( "Error Occurred in insertJobRequirement :" + ex.getMessage());
		}
		finally
		{
			DatabaseConnection.closeDatabaseComponents(callStatement);
		}
		return isQuerySuccess;
	}
}
