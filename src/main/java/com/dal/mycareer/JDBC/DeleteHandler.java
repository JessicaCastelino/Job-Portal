package com.dal.mycareer.JDBC;

import java.sql.CallableStatement;
import java.util.HashMap;
import java.util.Map;

import com.dal.mycareer.DBConnection.DatabaseConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteHandler extends JdbcManager 
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<String, Integer> procResult;

    @Override
    public Map<String, Integer> executeProc(CallableStatement callStatement, String mapperObjectName, Object dtoObject,
            Map<String, Object> additionalParam) 
    {
        try
        {
            ProcedureParamLoader paramLoader = new ProcedureParamLoader();
			paramLoader.fillInputParameters(callStatement, additionalParam);
            int rowsAffected = callStatement.executeUpdate();
            procResult = new HashMap<>();
            procResult.put("rowsAffected", rowsAffected);
        }
        catch(Exception ex)
        {
            logger.error( "Error Occurred in executeProc :" + ex.getMessage());
        }
        finally
        {
            DatabaseConnection.closeDatabaseComponents(callStatement);
        }
        return procResult;
    }
}