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
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	Connection con = null;
	CallableStatement callableStatement = null;
	@Override
	public List<RecruiterRequest> fetchRecruiterRequests() {
		con = DatabaseConnection.getConnection();
		RecruiterRequest recruiterRequest = null;
		List<RecruiterRequest> requests = new ArrayList<RecruiterRequest>();
		try {
			callableStatement = con.prepareCall("{call fetchRecruiterRequests()}");
			boolean results = callableStatement.execute();
			while (results) {
				ResultSet rs = callableStatement.getResultSet();
				while (rs.next()) {
					recruiterRequest = new RecruiterRequest();
					System.out.println("***********"+rs.getInt(1));
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return requests;
		} finally {
			try {
				callableStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public List<RecruiterRequest> fetchActiveRecruiters()
	{
		con = DatabaseConnection.getConnection();
		RecruiterRequest activeRecruiter = null;
		List <RecruiterRequest> lstActiveRecruiter = new ArrayList<RecruiterRequest>();
		try
		{
			callableStatement = con.prepareCall("{call fetchActiveRecruiters()}");
			ResultSet activeEmployerSet = callableStatement.executeQuery();
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
			LOGGER.error( "Error Occurred in fetchActiveRecruiters :" + ex.getMessage());
		}
		finally
		{
			try {
				callableStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lstActiveRecruiter;
	}
	
	@Override
	public int approveRequest(int requestId, String username, String password) {
		con = DatabaseConnection.getConnection();
		try {
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
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			try {
				callableStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
		@Override
		public int rejectRequest(int requestId) {
			con = DatabaseConnection.getConnection();
			try {
				
				callableStatement = con.prepareCall("{call rejectEmployer("+requestId+")}");
				boolean results = callableStatement.execute();
				if (results)
					return 1;
				else
					return 0;
		
				}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			} finally {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}

}
