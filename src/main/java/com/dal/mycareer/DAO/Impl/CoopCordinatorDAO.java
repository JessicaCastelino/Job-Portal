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

import com.dal.mycareer.DAO.Interface.ICoopCordinatorDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.RecruiterRequest;

@Repository
public class CoopCordinatorDAO implements ICoopCordinatorDAO
{
	private static final String CALL_FETCH_RECRUITER = "{call fetchRecruiter(?)}";
	private static final String CALL_REJECT_EMPLOYER = "{call rejectEmployer(?)}";
	private static final String CALL_MAKE_EMPLOYER_ACTIVE = "{call makeEmployerActive(?,?,?)}";
	private static final String CALL_FETCH_RECRUITER_REQUESTS = "{call fetchRecruiterRequests()}";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	Connection con = null;
	CallableStatement callableStatement = null;
	
	@Override
	public List<RecruiterRequest> fetchRecruiterRequests() throws SQLException 
	{
		
		logger.debug("CoopCoordinatorDAO: fetchRecruiterRequests method: Entered");
		con = DatabaseConnection.getConnection();
		RecruiterRequest recruiterRequest = null;
		ResultSet rs = null;
		List<RecruiterRequest> requests = new ArrayList<RecruiterRequest>();
		try {
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
					logger.debug("ID: "+recruiterRequest.getId());
					recruiterRequest.setFirstname(rs.getString(2));
					recruiterRequest.setLastname(rs.getString(3));
					recruiterRequest.setEmail(rs.getString(4));
					recruiterRequest.setCompanyname(rs.getString(5));		
					requests.add(recruiterRequest);
				}
				results = callableStatement.getMoreResults();
			}
			//return requests;
			throw new SQLException("Error in fetching recruiter requests.");
			
		} 
		catch (SQLException ex) 
		{
			logger.error( "SQLException Occurred in CoopCoordinatorDAO: fetchRecruiterRequests method:" + ex.getMessage());
			throw new SQLException("Error in fetching recruiter requests.");
		} 
		finally {
			DatabaseConnection.closeDatabaseComponents(rs, callableStatement, con);
			logger.debug("CoopCoordinatorDAO: fetchRecruiterRequests method: Exit");
		}
		
	}
	public List<RecruiterRequest> fetchActiveRecruiters()
	{
		con = DatabaseConnection.getConnection();
		RecruiterRequest activeRecruiter = null;
		ResultSet activeEmployerSet = null;
		List <RecruiterRequest> lstActiveRecruiter = new ArrayList<RecruiterRequest>();
		logger.debug("DL: fetchActiveRecruiters method started");
		try
		{
			callableStatement = con.prepareCall("{call fetchActiveRecruiters()}");
			activeEmployerSet = callableStatement.executeQuery();
			while(activeEmployerSet.next())
			{
				activeRecruiter = new RecruiterRequest();
				activeRecruiter.setId(Integer.toString(activeEmployerSet.getInt("id")));
				activeRecruiter.setFirstname(activeEmployerSet.getString("firstname"));
				activeRecruiter.setLastname(activeEmployerSet.getString("lastname"));
				activeRecruiter.setEmail(activeEmployerSet.getString("email"));
				activeRecruiter.setDesignation(activeEmployerSet.getString("designation"));
				activeRecruiter.setCompanyname(activeEmployerSet.getString("companyName"));
				lstActiveRecruiter.add(activeRecruiter);
			}
		}
		catch(Exception ex)
		{
			logger.error( "Error Occurred in fetchActiveRecruiters :" + ex.getMessage());
		}
		finally
		{
			DatabaseConnection.closeDatabaseComponents(activeEmployerSet,callableStatement, con);
		}
		return lstActiveRecruiter;
	}
	
	@Override
	public int approveRequest(int requestId, String username, String password) throws SQLException {
		logger.debug("CoopCoordinatorDAO: approveRequest method: Entered");
		con = DatabaseConnection.getConnection();
		try 
		{
			callableStatement = con.prepareCall(CALL_MAKE_EMPLOYER_ACTIVE);
			callableStatement.setInt(1, requestId);
			callableStatement.setString(2, username);
			callableStatement.setString(3, password);
			callableStatement.execute();
			logger.debug("Recruiter Requests with ID: "+requestId+" has been approved");
			return 1;
			}
		catch (SQLException ex) 
		{
			logger.error( "SQLException Occurred in CoopCoordinatorDAO: approveRequest method:" + ex.getMessage());
			throw new SQLException("Error while approving employer's request.");
		} 
		finally {
			DatabaseConnection.closeDatabaseComponents(callableStatement);
			logger.debug("CoopCoordinatorDAO: approveRequest method: Exit");
		}
	}
		
		@Override
		public int rejectRequest(int requestId) throws SQLException 
		{
			logger.debug("CoopCoordinatorDAO: rejectRequest method: Entered");
			con = DatabaseConnection.getConnection();
			try 
			{	
				callableStatement = con.prepareCall(CALL_REJECT_EMPLOYER);
				callableStatement.setInt(1, requestId);
				callableStatement.execute();
				logger.debug("Recruiter Requests with ID: "+requestId+" has been rejected");
				return 1;		
				}
				catch (SQLException e) 
				{
				logger.error( "SQLException Occurred in CoopCoordinatorDAO: rejectRequest method:" + e.getMessage());
				throw new SQLException("Error while rejecting the employer's request.");
			}
			finally {
				DatabaseConnection.closeDatabaseComponents(callableStatement);
				logger.debug("CoopCoordinatorDAO: rejectRequest method: Exit");
			}
	}
		
			@Override
			public RecruiterRequest fetchRecruiter(int reqID) throws SQLException {
				logger.debug("CoopCoordinatorDAO: fetchRecruiter method: Entered");
				con = DatabaseConnection.getConnection();
				RecruiterRequest recruiterRequest = null;
				ResultSet rs = null;
				try {
					callableStatement = con.prepareCall(CALL_FETCH_RECRUITER);
					callableStatement.setInt(1, reqID);
					callableStatement.execute();
					rs = callableStatement.getResultSet();
					while (rs.next()) 
					{
						recruiterRequest = new RecruiterRequest();
						recruiterRequest.setId(Integer.toString(rs.getInt(1)));
						logger.debug("Recruiter details for ID: "+recruiterRequest.getId()+" is fetched");
						recruiterRequest.setFirstname(rs.getString(2));
						recruiterRequest.setLastname(rs.getString(3));
						recruiterRequest.setEmail(rs.getString(4));
						recruiterRequest.setCompanyname(rs.getString(5));
					}
					return recruiterRequest;
				} 
				catch (SQLException e) 
				{
					logger.error( "SQLException Occurred in CoopCoordinatorDAO: fetchRecruiter method:" + e.getMessage());
					throw new SQLException("Error while fetching the employer details.");
				} 
				finally {
					DatabaseConnection.closeDatabaseComponents(rs, callableStatement, con);
					logger.debug("CoopCoordinatorDAO: fetchRecruiter method: Exit");
				}
			}

	@Override
	public boolean deleteActiveRecruiter(int employerId)
	{
		CallableStatement callStatement = null;
		Connection con = null;
		boolean isDeleted = false;
		logger.debug("DL: deleteActiveRecruiter method started");
		try
		{
			con = DatabaseConnection.getConnection();
			callStatement = con.prepareCall("{call sp_deleteActiveEmployer(?)}");
			callStatement.setInt("employerId", employerId);
			int rowDeleted = callStatement.executeUpdate();
			if (rowDeleted > 0)
			{
				isDeleted = true;
			}
			else
			{
				isDeleted = false;
				logger.error("SQLException Occurred while deleting employer");
			}
		}
		catch(Exception ex)
		{
			isDeleted = false;
			logger.error("SQLException Occurred in deleteActiveRecruiter :" + ex.getMessage());
		}
		finally
		{
			DatabaseConnection.closeDatabaseComponents(callStatement);
		}
		return isDeleted;
	}

}
