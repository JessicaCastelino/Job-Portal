package com.dal.mycareer.JDBC;

import java.sql.CallableStatement;
import java.util.Map;

public class ProcedureParamLoader
{
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
        catch (Exception e) 
        {
            
        }
    }
}