package com.dal.mycareer.DAO.Impl;

import java.util.HashMap;
import java.util.Map;

import com.dal.mycareer.DAO.Interface.IJobsDAO;
import com.dal.mycareer.JDBC.JdbcManager;
import com.dal.mycareer.JDBC.UpdateHandler;

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
			LOGGER.error("Error Occurred in updateJobStatus :" + ex.getMessage());
		}
		return isUpdateSuccess;
	}
}