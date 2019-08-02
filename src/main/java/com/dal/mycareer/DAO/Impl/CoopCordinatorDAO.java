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
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private Connection con = null;
	private CallableStatement callableStatement = null;

	

	public List<RecruiterRequest> fetchActiveRecruiters() 
	{
		con = DatabaseConnection.getConnection();
		RecruiterRequest activeRecruiter = null;
		ResultSet activeEmployerSet = null;
		List<RecruiterRequest> lstActiveRecruiter = new ArrayList<RecruiterRequest>();
		logger.debug("DL: fetchActiveRecruiters method started");
		try 
		{
			callableStatement = con.prepareCall("{call fetchActiveRecruiters()}");
			activeEmployerSet = callableStatement.executeQuery();
			while (activeEmployerSet.next()) 
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
		catch (Exception ex) 
		{
			logger.error("Error Occurred in fetchActiveRecruiters :" + ex.getMessage());
		} 
		finally 
		{
			DatabaseConnection.closeDatabaseComponents(activeEmployerSet, callableStatement, con);
		}
		return lstActiveRecruiter;
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
			} else 
			{
				isDeleted = false;
				logger.error("SQLException Occurred while deleting employer");
			}
		} 
		catch (Exception ex) 
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
