package com.dal.mycareer.DAO.Impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dal.mycareer.DAO.Interface.ICoopCordinatorDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.RecruiterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CoopCordinatorDAO implements ICoopCordinatorDAO
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	Connection con = null;
	CallableStatement callableStatement = null;
	
	@Override
	public List<RecruiterRequest> fetchRecruiterRequests() 
	{
		logger.info("CoopCoordinatorDAO: fetchRecruiterRequests method: Entered");
		con = DatabaseConnection.getConnection();
		RecruiterRequest recruiterRequest = null;
		List<RecruiterRequest> requests = new ArrayList<RecruiterRequest>();
		try {
			callableStatement = con.prepareCall("{call fetchRecruiterRequests()}");
			boolean results = callableStatement.execute();
			while (results) 
			{
				ResultSet rs = callableStatement.getResultSet();
				while (rs.next()) 
				{
					recruiterRequest = new RecruiterRequest();
					recruiterRequest.setId(Integer.toString(rs.getInt(1)));
					recruiterRequest.setFirstname(rs.getString(2));
					recruiterRequest.setLastname(rs.getString(3));
					recruiterRequest.setEmail(rs.getString(4));
					recruiterRequest.setCompanyname(rs.getString(5));		
					requests.add(recruiterRequest);
				}

				rs.close();
				results = callableStatement.getMoreResults();
			}
			return requests;
		} 
		catch (SQLException ex) 
		{
			logger.error( "Error Occurred in CoopCoordinatorDAO: fetchRecruiterRequests method:" + ex.getMessage());
			return requests;
		} 
		finally 
		{
			try 
			{
				callableStatement.close();
			}
			catch (SQLException e)
			{
				logger.error( "Error Occurred in CoopCoordinatorDAO: fetchRecruiterRequests method:" + e.getMessage());
			}
			logger.info("CoopCoordinatorDAO: fetchRecruiterRequests method: Exit");
		}
		
	}
	public List<RecruiterRequest> fetchActiveRecruiters()
	{
		con = DatabaseConnection.getConnection();
		RecruiterRequest activeRecruiter = null;
		ResultSet activeEmployerSet = null;
		List <RecruiterRequest> lstActiveRecruiter = new ArrayList<RecruiterRequest>();
		logger.info("DL: fetchActiveRecruiters method started");
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
			DatabaseConnection.closeDatabaseComponents(activeEmployerSet,callableStatement);
		}
		return lstActiveRecruiter;
	}
	
	@Override
	public int approveRequest(int requestId, String username, String password) {
		logger.info("CoopCoordinatorDAO: approveRequest method: Entered");
		con = DatabaseConnection.getConnection();
		try 
		{
			callableStatement = con.prepareCall("{call makeEmployerActive("+requestId+",'"+username+"','"+password+"')}");
			boolean results = callableStatement.execute();
			if (results)
			{
				return 1;
			}
			else
			{
				return 0;
			}
	
			}
		catch (SQLException ex) 
		{
			logger.error( "Error Occurred in CoopCoordinatorDAO: approveRequest method:" + ex.getMessage());
			return 0;
		} 
		finally 
		{
			try 
			{
				callableStatement.close();
			} 
			catch (SQLException e)
			{
				logger.error( "Error Occurred in CoopCoordinatorDAO: approveRequest method:" + e.getMessage());
			}
			logger.info("CoopCoordinatorDAO: approveRequest method: Exit");
		}
	}
		
		@Override
		public int rejectRequest(int requestId) 
		{
			logger.info("CoopCoordinatorDAO: rejectRequest method: Entered");
			con = DatabaseConnection.getConnection();
			try 
			{	
				callableStatement = con.prepareCall("{call rejectEmployer("+requestId+")}");
				boolean results = callableStatement.execute();
				if (results)
				{
					return 1;
				}
				else
				{
					return 0;
				}
		
				}
				catch (SQLException e) 
				{
				logger.error( "Error Occurred in CoopCoordinatorDAO: rejectRequest method:" + e.getMessage());
				return 0;
			}
			finally 
			{
				try 
				{
					callableStatement.close();
				} 
				catch (SQLException e)
				{
					logger.error( "Error Occurred in CoopCoordinatorDAO: rejectRequest method:" + e.getMessage());
				}
				logger.info("CoopCoordinatorDAO: rejectRequest method: Exit");
				
			}
	}
		
			@Override
			public RecruiterRequest fetchRecruiter(int reqID) {
				logger.info("CoopCoordinatorDAO: fetchRecruiter method: Entered");
				con = DatabaseConnection.getConnection();
				RecruiterRequest recruiterRequest = null;
				try {
					callableStatement = con.prepareCall("{call fetchRecruiter("+reqID+")}");
					boolean results = callableStatement.execute();
					ResultSet rs = callableStatement.getResultSet();
					while (rs.next()) 
					{
						recruiterRequest = new RecruiterRequest();
						recruiterRequest.setId(Integer.toString(rs.getInt(1)));
						recruiterRequest.setFirstname(rs.getString(2));
						recruiterRequest.setLastname(rs.getString(3));
						recruiterRequest.setEmail(rs.getString(4));
						recruiterRequest.setCompanyname(rs.getString(5));
					}
					rs.close();
					return recruiterRequest;
				} 
				catch (SQLException e) 
				{
					logger.error( "Error Occurred in CoopCoordinatorDAO: fetchRecruiter method:" + e.getMessage());
					return recruiterRequest;
				} 
				finally 
				{
					try 
					{
						callableStatement.close();
					}
					catch (SQLException e)
					{
						logger.error( "Error Occurred in CoopCoordinatorDAO: fetchRecruiter method:" + e.getMessage());
					}
					logger.info("CoopCoordinatorDAO: fetchRecruiter method: Exit");
				}
			}

	@Override
	public boolean deleteActiveRecruiter(int employerId)
	{
		CallableStatement callStatement = null;
		Connection con = null;
		boolean isDeleted = false;
		logger.info("DL: deleteActiveRecruiter method started");
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
				logger.error("Error Occurred while deleting employer");
			}
		}
		catch(Exception ex)
		{
			isDeleted = false;
			logger.error("Error Occurred in deleteActiveRecruiter :" + ex.getMessage());
		}
		finally
		{
			DatabaseConnection.closeDatabaseComponents(callStatement);
		}
		return isDeleted;
	}

}
