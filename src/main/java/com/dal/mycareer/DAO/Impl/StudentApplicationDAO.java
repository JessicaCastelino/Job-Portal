package com.dal.mycareer.DAO.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.dal.mycareer.DAO.Interface.IStudentApplicationDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.propertiesparser.PropertiesParser;

public class StudentApplicationDAO implements IStudentApplicationDAO
{
	private static final String CALL_ALREADY_APPLIED = "{call alreadyApplied(?,?)}";
	private static final String CALL_WITHDRAW_APPLICATION = "{call withdrawApplication(?)}";
	private static final String CALL_APPLY_FOR_JOB = "{call applyForJob(?,?,?,?)}";
	private Connection con = null;
	private static final Properties PROPERTY_MAP = PropertiesParser.getPropertyMap();
	private CallableStatement callableStatement = null;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public int submitJobApplication(MultipartFile file, int studentId, int jobId) throws SQLException, IOException
	{
		logger.debug("StudentApplicationDAO: submitJobApplication method: Entered");
		con = DatabaseConnection.getConnection();
		try {
			callableStatement = con.prepareCall(CALL_APPLY_FOR_JOB);
			callableStatement.setBinaryStream(1,file.getInputStream());
			callableStatement.setString(2, PROPERTY_MAP.get("Submitted").toString());
			callableStatement.setInt(3, studentId);
			callableStatement.setInt(4, jobId);
			callableStatement.execute();
			logger.debug("Student with ID: "+studentId+" has successfully applied for job with ID: "+jobId+" has been approved");
			return 1;

		} catch (SQLException e) {
			logger.error( "SQLException Occurred in StudentApplicationDAO: submitJobApplication method:" + e.getMessage());
			throw new SQLException("Error while applying for the job.");
		} catch (IOException e)
		{
			logger.error( "IOException Occurred in StudentApplicationDAO: submitJobApplication method:" + e.getMessage());
			throw new IOException("Error while applying for the job.");
		}
		finally {
			DatabaseConnection.closeDatabaseComponents(callableStatement);
			logger.debug("StudentApplicationDAO: submitJobApplication method: Exit");	
		}
		
	}

	@Override
	public int withdrawJobApplication(int jobId) throws SQLException
	{
		logger.debug("StudentApplicationDAO: withdrawJobApplication method: Entered");
		con = DatabaseConnection.getConnection();
		try {
			callableStatement = con.prepareCall(CALL_WITHDRAW_APPLICATION);
			callableStatement.setInt(1, jobId);
			callableStatement.execute();
			logger.debug("Withdrawn the application with ID: "+jobId);
			return 1;

		} catch (SQLException e) {
			logger.error( "SQLException Occurred in StudentApplicationDAO: withdrawJobApplication method:" + e.getMessage());
			throw new SQLException("Error while withdrawing the job application.");
		}
		finally {
			DatabaseConnection.closeDatabaseComponents(callableStatement);
			logger.debug("StudentApplicationDAO: withdrawJobApplication method: Exit");
		}
	}

	@Override
	public int checkApplicationExists(int studentId, int jobId) throws SQLException
	{
		logger.debug("StudentApplicationDAO: checkApplicationExists method: Entered");
		con = DatabaseConnection.getConnection();
		ResultSet rs=null;
		try {
			callableStatement = con.prepareCall(CALL_ALREADY_APPLIED);
			callableStatement.setInt(1, studentId);
			callableStatement.setInt(2, jobId);
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
			logger.error( "SQLException Occurred in StudentApplicationDAO: checkApplicationExists method:" + e.getMessage());
			throw new SQLException("Error while checking if the student has already applied for the job.");
		}
		finally {
			DatabaseConnection.closeDatabaseComponents(rs, callableStatement, con);
			logger.debug("StudentApplicationDAO: checkApplicationExists method: Exit");
		}
	}

}
