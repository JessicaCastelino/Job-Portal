package com.dal.mycareer.DAO.Impl;

import java.sql.CallableStatement;
import java.sql.Connection;

import com.dal.mycareer.DAO.Interface.IJobsDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class JobsDAO implements IJobsDAO {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    @Override
	public boolean updateJobStatus(int jobRecordId, boolean jobStatus) 
	{
		boolean isUpdateSuccess = false;
		CallableStatement callStatement = null;
		Connection con= null;
		try
		{
		 con  = DatabaseConnection.getConnection();
		 callStatement = con.prepareCall("{call sp_updateJobStatus(?, ?)}"); 
         callStatement.setInt("jobRecordId", jobRecordId);
         callStatement.setBoolean("status", jobStatus);
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
			LOGGER.error( "Error Occurred in updateJobStatus :" + ex.getMessage());
		}
		
		return isUpdateSuccess;
	}
}