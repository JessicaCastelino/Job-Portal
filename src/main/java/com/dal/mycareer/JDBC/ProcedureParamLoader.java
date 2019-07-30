package com.dal.mycareer.JDBC;

import java.sql.CallableStatement;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcedureParamLoader
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public void fillInputParameters(CallableStatement callStatement, Map<String, Object> inputParams)
    {
        try 
        {
            for (String param : inputParams.keySet() ) 
            {
                Object paramValue = inputParams.get(param);
                if(paramValue instanceof  Integer)
                {
                    callStatement.setInt(param, (Integer) paramValue);
                }
                else if(paramValue instanceof String)
                {
                    callStatement.setString(param, (String) paramValue);
                }
                else if(paramValue instanceof Boolean)
                {
                    callStatement.setBoolean(param, (Boolean) paramValue);
                }
            }
        } 
        catch (Exception ex) 
        {
            logger.error( "Error Occurred in fillInputParameters :" + ex.getMessage());
        }
    }
}