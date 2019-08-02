package com.dal.mycareer.DAO.Impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dal.mycareer.DAO.Interface.IRecruiterRegistrationRequestDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.RecruiterRequest;

public class RecruiterRegistrationRequestDAO implements IRecruiterRegistrationRequestDAO
{
	private static final String CALL_FETCH_RECRUITER = "{call fetchRecruiter(?)}";
	private static final String CALL_REJECT_EMPLOYER = "{call rejectEmployer(?)}";
	private static final String CALL_MAKE_EMPLOYER_ACTIVE = "{call makeEmployerActive(?,?,?)}";
	private static final String CALL_FETCH_RECRUITER_REQUESTS = "{call fetchRecruiterRequests()}";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private Connection con = null;
	private CallableStatement callableStatement = null;
	
	
	@Override
	public List<RecruiterRequest> fetchAllRecruiterRequests() throws SQLException
	{
		logger.debug("RecruiterRegistrationRequestDAO: fetchAllRecruiterRequests method: Entered");
		con = DatabaseConnection.getConnection();
		RecruiterRequest recruiterRequest = null;
		ResultSet rs = null;
		List<RecruiterRequest> requests = new ArrayList<RecruiterRequest>();
		try 
		{
			callableStatement = con.prepareCall(CALL_FETCH_RECRUITER_REQUESTS);
			boolean results = callableStatement.execute();
			while (results) 
			{
				rs = callableStatement.getResultSet();
				logger.debug("Recruiter Requests fetched:");
				while (rs.next()) 
				{
					recruiterRequest = new RecruiterRequest();
					recruiterRequest.setId(Integer.toString(rs.getInt(1)));
					logger.debug("ID: " + recruiterRequest.getId());
					recruiterRequest.setFirstname(rs.getString(2));
					recruiterRequest.setLastname(rs.getString(3));
					recruiterRequest.setEmail(rs.getString(4));
					recruiterRequest.setCompanyname(rs.getString(5));
					requests.add(recruiterRequest);
				}
				results = callableStatement.getMoreResults();
			}
			 return requests;
		} 
		catch (SQLException ex) 
		{
			logger.error("SQLException Occurred in RecruiterRegistrationRequestDAO: fetchAllRecruiterRequests method:" + ex.getMessage());
			throw new SQLException("Error in fetching recruiter requests.");
		} 
		finally 
		{
			DatabaseConnection.closeDatabaseComponents(rs, callableStatement, con);
			logger.debug("RecruiterRegistrationRequestDAO: fetchAllRecruiterRequests method: Exit");
		}


		
	}

	@Override
	public RecruiterRequest fetchRecruiterRequest(int reqID) throws SQLException
	{
		logger.debug("RecruiterRegistrationRequestDAO: fetchRecruiter method: Entered");
		con = DatabaseConnection.getConnection();
		RecruiterRequest recruiterRequest = null;
		ResultSet rs = null;
		try 
		{
			callableStatement = con.prepareCall(CALL_FETCH_RECRUITER);
			callableStatement.setInt(1, reqID);
			callableStatement.execute();
			rs = callableStatement.getResultSet();
			while (rs.next()) 
			{
				recruiterRequest = new RecruiterRequest();
				recruiterRequest.setId(Integer.toString(rs.getInt(1)));
				logger.debug("Recruiter details for ID: " + recruiterRequest.getId() + " is fetched");
				recruiterRequest.setFirstname(rs.getString(2));
				recruiterRequest.setLastname(rs.getString(3));
				recruiterRequest.setEmail(rs.getString(4));
				recruiterRequest.setCompanyname(rs.getString(5));
			}
			return recruiterRequest;
		} 
		catch (SQLException e) 
		{
			logger.error("SQLException Occurred in RecruiterRegistrationRequestDAO: fetchRecruiter method:" + e.getMessage());
			throw new SQLException("Error while fetching the employer details.");
		} 
		finally 
		{
			DatabaseConnection.closeDatabaseComponents(rs, callableStatement, con);
			logger.debug("RecruiterRegistrationRequestDAO: fetchRecruiter method: Exit");
		}
	}

	@Override
	public int approveRequest(int requestId, String username, String password) throws SQLException
	{
		logger.debug("RecruiterRegistrationRequestDAO: approveRequest method: Entered");
		con = DatabaseConnection.getConnection();
		try 
		{
			callableStatement = con.prepareCall(CALL_MAKE_EMPLOYER_ACTIVE);
			callableStatement.setInt(1, requestId);
			callableStatement.setString(2, username);
			callableStatement.setString(3, password);
			callableStatement.execute();
			logger.debug("Recruiter Requests with ID: " + requestId + " has been approved");
			return 1;
		} 
		catch (SQLException ex) 
		{
			logger.error("SQLException Occurred in RecruiterRegistrationRequestDAO: approveRequest method:" + ex.getMessage());
			throw new SQLException("Error while approving employer's request.");
		} 
		finally 
		{
			DatabaseConnection.closeDatabaseComponents(callableStatement);
			logger.debug("RecruiterRegistrationRequestDAO: approveRequest method: Exit");
		}
	}

	@Override
	public int rejectRequest(int requestId) throws SQLException
	{
		logger.debug("RecruiterRegistrationRequestDAO: rejectRequest method: Entered");
		con = DatabaseConnection.getConnection();
		try 
		{
			callableStatement = con.prepareCall(CALL_REJECT_EMPLOYER);
			callableStatement.setInt(1, requestId);
			callableStatement.execute();
			logger.debug("Recruiter Requests with ID: " + requestId + " has been rejected");
			return 1;
		} 
		catch (SQLException e) 
		{
			logger.error("SQLException Occurred in RecruiterRegistrationRequestDAO: rejectRequest method:" + e.getMessage());
			throw new SQLException("Error while rejecting the employer's request.");
		} 
		finally 
		{
			DatabaseConnection.closeDatabaseComponents(callableStatement);
			logger.debug("RecruiterRegistrationRequestDAO: rejectRequest method: Exit");
		}
	}

}
