package com.dal.mycareer.DAO.Impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dal.mycareer.DAO.Interface.IStudentJobsDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.AppliedJob;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.configlogic.ConfigLogicClassLoader;

public class StudentJobsDAO implements IStudentJobsDAO {
	private static final String CALL_GET_APPLIED_JOB_LIST = "{call getAppliedJobList(?)}";
	private Connection con = null;
	private CallableStatement callableStatement = null;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<JobDetails> getAllJobList(int studID, String type) throws SQLException {
		
		logger.debug("StudentJobsDAO: getAllJobList method: Entered");
		con = DatabaseConnection.getConnection();
		
		JobDetails job = null;
		ResultSet rs = null;
		List<JobDetails> jobs = new ArrayList<JobDetails>();
		try {
			callableStatement = con.prepareCall(ConfigLogicClassLoader.getJobClass());
			callableStatement.setInt(1, studID);
			callableStatement.setString(2, type);
			boolean results = callableStatement.execute();
			logger.debug("Jobs fetched:");
			while (results) {
				rs = callableStatement.getResultSet();
				while (rs.next()) {
					job = new JobDetails();
					job.setId(rs.getInt(1));
					logger.debug("ID: " + job.getId());
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
				results = callableStatement.getMoreResults();
			}
			return jobs;
		} catch (SQLException e) {
			logger.error("SQLException Occurred in StudentJobsDAO: getAllJobList method:" + e.getMessage());
			throw new SQLException("Error in fetching the job list.");
		} finally {
			DatabaseConnection.closeDatabaseComponents(rs, callableStatement, con);
			logger.debug("StudentJobsDAO: getAllJobList method: Exit");

		}
	}

	@Override
	public List<AppliedJob> getAppliedJobList(int studentId) throws SQLException {
		logger.debug("StudentJobsDAO: getAppliedJobList method: Entered");
		con = DatabaseConnection.getConnection();
		AppliedJob job = null;
		ResultSet rs = null;
		List<AppliedJob> appliedJobs = new ArrayList<AppliedJob>();
		try {
			callableStatement = con.prepareCall(CALL_GET_APPLIED_JOB_LIST);
			callableStatement.setInt(1, studentId);
			boolean results = callableStatement.execute();
			logger.debug("Applied jobs fetched:");
			while (results) {
				rs = callableStatement.getResultSet();
				while (rs.next()) {
					job = new AppliedJob();
					job.setId(Integer.toString(rs.getInt(1)));
					logger.debug("ID: " + job.getId());
					job.setDocument(rs.getBinaryStream(2));
					job.setApplicationStatus(rs.getString(3));
					job.setStudentId(Integer.toString(rs.getInt(4)));
					job.setJobId(Integer.toString(rs.getInt(5)));
					job.setJobTitle(rs.getString(7));
					job.setLocation(rs.getString(8));
					job.setOpenPosition(Integer.toString(rs.getInt(9)));
					job.setJobType(rs.getString(10));
					job.setRateOfPay(Integer.toString(rs.getInt(11)));
					job.setHoursPerWeek(Integer.toString(rs.getInt(12)));
					job.setApplicationDeadline(rs.getString(13));
					job.setJobDescription(rs.getString(14));
					job.setAdditionalInformation(rs.getString(15));
					job.setJobStatus(rs.getString(16));
					job.setEmployeeId(Integer.toString(rs.getInt(17)));
					job.setTerm(rs.getString(18));
					job.setOrganization(rs.getString(19));
					appliedJobs.add(job);
				}
				results = callableStatement.getMoreResults();
			}
			return appliedJobs;
		} catch (SQLException e) {
			logger.error("SQLException Occurred in StudentJobsDAO: getAppliedJobList method:" + e.getMessage());
			throw new SQLException("Error in fetching the applied job list.");
		} finally {
			DatabaseConnection.closeDatabaseComponents(rs, callableStatement, con);
			logger.debug("StudentJobsDAO: getAppliedJobList method: Exit");
		}
	}

}
