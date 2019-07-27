package com.dal.mycareer.JDBC;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Map;

import com.dal.mycareer.DBConnection.DatabaseConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class jdbcManager
{
    private Connection con = null;
	private CallableStatement callStatement = null;
	private Map<String, Integer> procResults;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public Map<String, Integer> executeProcedure(String procedureName, String mapperObjectName, Object dtoObject, Map<String, Object> additionalParam)
    {
        createDBConnection();
        prepareProcedureCall(procedureName);
		procResults = executeProc(callStatement, mapperObjectName, dtoObject, additionalParam);
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