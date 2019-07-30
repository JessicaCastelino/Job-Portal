package com.dal.mycareer.DAO.Impl;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dal.mycareer.DAO.Interface.IStudentDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.AppliedJob;
import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.DTO.Student;
import com.dal.mycareer.propertiesparser.PropertiesParser;

public class StudentDAO implements IStudentDAO {

	private static final String CALL_WITHDRAW_APPLICATION = "{call withdrawApplication(?)}";
	private static final String CALL_FETCH_STUDENT_DETAILS = "{call fetchStudentDetails(?)}";
	private static final String CALL_APPLY_FOR_JOB = "{call applyForJob(?,?,?,?)}";
	private static final String CALL_GET_APPLIED_JOB_LIST = "{call getAppliedJobList(?)}";
	private static final String CALL_GET_ALL_JOB_LIST = "{call getAllJobList(?,?)}";
	private static final Properties PROPERTY_MAP = PropertiesParser.getPropertyMap();
	private Connection con = null;
	private CallableStatement callableStatement = null;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<JobDetails> getAllJobList(int studID, String type) throws SQLException {
		logger.debug("StudentDAO: getAllJobList method: Entered");
		con = DatabaseConnection.getConnection();
		JobDetails job = null;
		ResultSet rs = null;
		List<JobDetails> jobs = new ArrayList<JobDetails>();
		try {
			callableStatement = con.prepareCall(CALL_GET_ALL_JOB_LIST);
			callableStatement.setInt(1, studID);
			callableStatement.setString(2, type);
			boolean results = callableStatement.execute();
			while (results) {
				rs = callableStatement.getResultSet();
				logger.debug("Jobs fetched:");
				while (rs.next()) {
					job = new JobDetails();
					job.setId(rs.getInt(1));
					logger.debug("ID: "+job.getId());
					job.setJobTitle(rs.getString(2));
					job.setLocation(rs.getString(3));
					job.setNoOfPosition((rs.getInt(4)));
					job.setJobType(rs.getString(5));
					job.setRateOfPay((rs.getInt(6)));
					job.setHourPerWeek((rs.getInt(7)));
					job.setApplicationDeadline(rs.getDate(8));
					job.setJobDescription(rs.getString(9));
					job.setEmployeeId((rs.getInt(12)));
					job.setOrganization(rs.getString(13));
					jobs.add(job);
				}
				results = callableStatement.getMoreResults();
			}
			return jobs;
		} catch (SQLException e) {
			logger.error( "SQLException Occurred in StudentDAO: getAllJobList method:" + e.getMessage());
			throw new SQLException("Error in fetching the job list.");
		} finally {
				DatabaseConnection.closeDatabaseComponents(rs, callableStatement, con);
				logger.debug("StudentDAO: getAllJobList method: Exit");
			
		}
	}

	@Override
	public List<AppliedJob> getAppliedJobList(int studentId) throws SQLException {
		logger.debug("StudentDAO: getAppliedJobList method: Entered");
		con = DatabaseConnection.getConnection();
		AppliedJob job = null;
		ResultSet rs=null;
		List<AppliedJob> appliedJobs = new ArrayList<AppliedJob>();
		try {
			callableStatement = con.prepareCall(CALL_GET_APPLIED_JOB_LIST);
			callableStatement.setInt(1, studentId);
			boolean results = callableStatement.execute();
			while (results) {
				rs = callableStatement.getResultSet();
				logger.debug("Applied jobs fetched:");
				while (rs.next()) {
					job = new AppliedJob();
					job.setId(Integer.toString(rs.getInt(1)));
					logger.debug("ID: "+job.getId());
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
			logger.error( "SQLException Occurred in StudentDAO: getAppliedJobList method:" + e.getMessage());
			throw new SQLException("Error in fetching the applied job list.");
		}  finally {
			DatabaseConnection.closeDatabaseComponents(rs, callableStatement, con);
			logger.debug("StudentDAO: getAppliedJobList method: Exit");
		}
	}

	@Override
	public int applyForJob(InputStream inputStream, int studentId, int jobId) throws SQLException {
		logger.debug("StudentDAO: applyForJob method: Entered");
		con = DatabaseConnection.getConnection();
		try {
			callableStatement = con.prepareCall(CALL_APPLY_FOR_JOB);
			callableStatement.setBinaryStream(1, inputStream);
			callableStatement.setString(2, PROPERTY_MAP.get("Submitted").toString());
			callableStatement.setInt(3, studentId);
			callableStatement.setInt(4, jobId);
			callableStatement.execute();
			logger.debug("Student with ID: "+studentId+" has successfully applied for job with ID: "+jobId+" has been approved");
			return 1;

		} catch (SQLException e) {
			logger.error( "SQLException Occurred in StudentDAO: applyForJob method:" + e.getMessage());
			throw new SQLException("Error while applying for the job.");
		}
		finally {
			DatabaseConnection.closeDatabaseComponents(callableStatement);
			logger.debug("StudentDAO: applyForJob method: Exit");	
		}
		
		
	}

	@Override
	public Student getStudentDetails(String userSessionName) throws SQLException {
		logger.debug("StudentDAO: getStudentDetails method: Entered");
		con = DatabaseConnection.getConnection();
		Student student = null;
		ResultSet rs = null;
		try {
			callableStatement = con.prepareCall(CALL_FETCH_STUDENT_DETAILS);
			callableStatement.setString(1,userSessionName);
			callableStatement.execute();
			rs = callableStatement.getResultSet();
			while (rs.next()) {
				student = new Student();
				student.setId(rs.getInt(1));
				student.setFirstname(rs.getString(2));
				student.setLastname(rs.getString(3));
				student.setBannerid(rs.getString(4));
				student.setEmail(rs.getString(5));
				student.setPhonenumber(rs.getString(6));
				student.setDegree(rs.getString(7));
				student.setDepartment(rs.getString(8));
				student.setProgram(rs.getString(9));
				student.setIsActive(rs.getInt(10));
				logger.debug("Details for student with session name: "+userSessionName+" and ID: "+student.getId()+" successfully fetched");
			}
			return student;
		} catch (SQLException e) {
			logger.error( "SQLException Occurred in StudentDAO: getStudentDetails method:" + e.getMessage());
			throw new SQLException("Error while fetching student details.");
		} finally {
			DatabaseConnection.closeDatabaseComponents(rs, callableStatement, con);
			logger.debug("StudentDAO: getStudentDetails method: Exit");
		}
	}

	@Override
	public int withdrawApplication(int jobId) throws SQLException {
		logger.debug("StudentDAO: withdrawApplication method: Entered");
		con = DatabaseConnection.getConnection();
		try {
			callableStatement = con.prepareCall(CALL_WITHDRAW_APPLICATION);
			callableStatement.setInt(1, jobId);
			boolean results = callableStatement.execute();
			logger.debug("Withdrawn the application with ID: "+jobId);
			return 1;

		} catch (SQLException e) {
			logger.error( "SQLException Occurred in StudentDAO: withdrawApplication method:" + e.getMessage());
			throw new SQLException("Error while withdrawing the job application.");
		}
		finally {
			DatabaseConnection.closeDatabaseComponents(callableStatement);
			logger.debug("StudentDAO: withdrawApplication method: Exit");
		}
	}

	@Override
	public int alreadyApplied(int studentId, int jobId) throws SQLException {
		logger.debug("StudentDAO: alreadyApplied method: Entered");
		con = DatabaseConnection.getConnection();
		ResultSet rs=null;
		try {
			callableStatement = con.prepareCall("{call alreadyApplied(" + studentId + "," + jobId + ")}");
			callableStatement.execute();
			rs = callableStatement.getResultSet();
			rs.next();
			int count =rs.getInt(1);
			if (count>0)
			{
				logger.debug("Student with ID: "+studentId+" has already applied for job with ID: "+jobId);
				return 1;
			}
			else
			{
				logger.debug("Student with ID: "+studentId+" has not yet applied for job with ID: "+jobId);
				return 0;
			}

		} catch (SQLException e) {
			logger.error( "SQLException Occurred in StudentDAO: alreadyApplied method:" + e.getMessage());
			throw new SQLException("Error while checking if the student has already applied for the job.");
		}
		finally {
			DatabaseConnection.closeDatabaseComponents(rs, callableStatement, con);
			logger.debug("StudentDAO: alreadyApplied method: Exit");
		}
	}
}
