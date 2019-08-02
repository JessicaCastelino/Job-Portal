package com.dal.mycareer.JDBC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import com.dal.mycareer.DBConnection.DatabaseConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class JdbcManager
{
    private Connection con = null;
	private CallableStatement callStatement = null;
	private Map<String, Integer> procResults;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    public Map<String, Integer> executeProcedure(String procedureName, String mapperObjectName, Object dtoObject, Map<String, Object> additionalParam)
    {
        try
        {
            createDBConnection();
            prepareProcedureCall(procedureName);
            procResults = executeProc(callStatement, mapperObjectName, dtoObject, additionalParam);
        }
        catch(Exception ex)
        {
            logger.error("Error occurred while executing the statement " + procedureName, ex.getMessage());
        }
        finally
        {
            DatabaseConnection.closeConnection(con);
        }
		return procResults;
	}
	 
    private void createDBConnection()
    {
        con  = DatabaseConnection.getConnection();
	}
	
    private void prepareProcedureCall(String procedureName)
    {
        try
        {
        callStatement = con.prepareCall(procedureName);
        }
        catch(SQLException ex)
        {
			logger.error( "Error Occurred in prepareProcedureCall :" + ex.getMessage());
        }
	}
	
    public abstract Map<String, Integer> executeProc(CallableStatement callStatement, String mapperObjectName, Object dtoObject, Map<String,Object>  additionalParam);
}