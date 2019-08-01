package com.dal.mycareer.DAO.Impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dal.mycareer.DAO.Interface.IJobsDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.JDBC.JdbcManager;
import com.dal.mycareer.JDBC.UpdateHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class JobsDAO implements IJobsDAO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private Connection con = null;
	private CallableStatement callableStatement = null;
	private static final String CALL_FETCH_JOB = "{call fetchJob(?)}";
	
	@Override
	public boolean updateJobStatus(int jobRecordId, boolean jobStatus) 
	{
		boolean isUpdateSuccess = false;
		try 
		{
			JdbcManager jdbcManager = new UpdateHandler();
			Map<String, Object> inputParameters = new HashMap<>();
			inputParameters.put("jobRecordId", jobRecordId);
			inputParameters.put("status", jobStatus);
			Map<String, Integer> res = jdbcManager.executeProcedure("{call sp_updateJobStatus(?, ?)}", "", null,
					inputParameters);
			int rowsAffected = res.get("rowsAffected");
			if (rowsAffected > 0) 
			{
				isUpdateSuccess = true;
			} 
			else 
			{
				isUpdateSuccess = false;
			}
		} 
		catch (Exception ex) 
		{
			logger.error("Error Occurred in updateJobStatus :" + ex.getMessage());
		}
		return isUpdateSuccess;
	}
	@Override
	public JobDetails fetchJob(int jobId) throws SQLException {
		logger.debug("JobsDAO: fetchJob method: Entered");
		con = DatabaseConnection.getConnection();
		JobDetails job = new JobDetails();
		ResultSet rs = null;
		List<JobDetails> jobs = new ArrayList<JobDetails>();
		try {
			callableStatement = con.prepareCall(CALL_FETCH_JOB);
			callableStatement.setInt(1, jobId);
			callableStatement.execute();
				rs = callableStatement.getResultSet();
				logger.debug("Jobs fetched:");
				while (rs.next()) {
					job.setId(rs.getInt(1));
					logger.debug("Fetched Job with ID: "+job.getId());
					job.setJobTitle(rs.getString(2));
					job.setLocation(rs.getString(3));
					job.setNoOfPosition((rs.getInt(4)));
					job.setJobType(rs.getString(5));
					job.setRateOfPay((rs.getInt(6)));
					job.setHourPerWeek((rs.getInt(7)));
					job.setApplicationDeadline(rs.getDate(8));
					job.setJobDescription(rs.getString(9));
					job.setEmployeeId((rs.getInt(12)));
					job.setOrganization(rs.getString(14));
					jobs.add(job);
			}
			return job;
		} catch (SQLException e) {
			logger.error( "SQLException Occurred in JobsDAO: fetchJob method:" + e.getMessage());
			throw new SQLException("Error in fetching the job details.");
		} finally {
				DatabaseConnection.closeDatabaseComponents(rs, callableStatement, con);
				logger.debug("JobsDAO: fetchJob method: Exit");
			
		}
	}
	
}