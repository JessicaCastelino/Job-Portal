package com.dal.mycareer.DAO.Impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	public List<Job> getActiveJobs(int employeeId) {
		List<Job> activeJobs = null;
		Connection conn = DatabaseConnection.getConnection();
		try {
			CallableStatement statement = conn.prepareCall("{CALL getActiveJobsForEmployer(?)}");
			statement.setInt("employerid", employeeId);
			ResultSet result = statement.executeQuery();
			Job job;
			activeJobs = new ArrayList<>();
			while(result.next()) {
				job = new Job();
				job.id = result.getInt("id");
				job.jobId = result.getString("jobId");
				job.jobTitle = result.getString("jobTitle");
				job.jobType = result.getString("jobType");
				job.location = result.getString("location");
				job.organization = result.getString("organization");
				job.applicationDeadline = result.getDate("applicationDeadline");
				activeJobs.add(job);
			}
		} catch (SQLException e) {
			LOGGER.error("Exception occurred at EmployerJobsDAO:getActiveJobs " + e.getMessage());
			e.printStackTrace();
		}
		return activeJobs;
	}
	
	public JobDetails InsertJobDetails(JobDetails postedJobDetails) {
		CallableStatement callStatement = null;
		Connection con= null;
		try
		{
		 con  = DatabaseConnection.getConnection();
		 callStatement = con.prepareCall("{call sp_insertjobdetails(?,?,?,?,?,?,?)}"); 
		 callStatement.setString("jobTitle", postedJobDetails.jobTitle);
		 callStatement.setString("jobLocation", postedJobDetails.location);
		 callStatement.setString("jobType", postedJobDetails.jobType);
		 callStatement.setString("noOfPosition", Integer.toString(postedJobDetails.noOfPosition));
		 callStatement.setString("rateOfPay", Integer.toString(postedJobDetails.rateOfPay));
		 callStatement.setString("hourPerWeek", Integer.toString(postedJobDetails.hourPerWeek));
		 callStatement.setString("jobDescription", postedJobDetails.jobDescription);
		 callStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			LOGGER.error( "Error Occurred in InsertJobDetails :" + ex.getMessage());
		}
		
		return postedJobDetails;
	}

	@Override
	public boolean updateJobStatus(int jobRecordId) 
	{
		boolean isUpdateSuccess = false;
		CallableStatement callStatement = null;
		Connection con= null;
		try
		{
		 con  = DatabaseConnection.getConnection();
		 callStatement = con.prepareCall("{call sp_closeactivejob(?)}"); 
		 callStatement.setInt("id", jobRecordId);
		 int rowsAffected = callStatement.executeUpdate();
		 if(rowsAffected > 0)
		 {
			isUpdateSuccess = true;
		 }
		 else
		 {
			 isUpdateSuccess = false;
		 }
		}
		catch(Exception ex)
		{
			LOGGER.error( "Error Occurred in InsertJobDetails :" + ex.getMessage());
		}
		
		return isUpdateSuccess;
	}
}
