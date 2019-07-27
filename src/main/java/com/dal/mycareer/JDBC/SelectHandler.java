package com.dal.mycareer.JDBC;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTOMapper.DTOMapper;
import com.dal.mycareer.DTOMapper.IDTOMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectHandler extends JdbcManager 
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<String, Integer> procResult;
    @Override
    public Map<String, Integer> executeProc(CallableStatement callStatement, String mapperObjectName, Object dtoObject, 
                            Map<String, Object> additionalParam) 
    {
        ResultSet result = null;
        try
        {
            fillInputParameters(callStatement, additionalParam);
            result = callStatement.executeQuery();
            fillDTOObjectFromResultSet(mapperObjectName, result, dtoObject);
            procResult = new HashMap<>();
            procResult.put("isObjectFilled", 1);
        }
        catch(Exception ex)
        {
            logger.error( "Error Occurred in executeProc :" + ex.getMessage());
        }
        finally
        {
            DatabaseConnection.closeDatabaseComponents(result, callStatement);
        }
        return procResult;
    }

    private void fillDTOObjectFromResultSet(String mapperObjectName, ResultSet result, Object dtoObject)
    {
        IDTOMapper mapper = DTOMapper.dtoMap.get(mapperObjectName);
        mapper.mapStatementtoObject(result, dtoObject);
    }

    private void fillInputParameters(CallableStatement callStatement, Map<String, Object> inputParams)
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
            }
        } 
        catch (Exception e) 
        {
            
        }
    }
}