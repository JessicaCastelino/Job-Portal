package com.dal.mycareer.JDBC;

import java.sql.CallableStatement;
import java.util.HashMap;
import java.util.Map;

import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTOMapper.DTOMapper;
import com.dal.mycareer.DTOMapper.IDTOMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateHandler extends JdbcManager 
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<String, Integer> procResult;

    @Override
    public Map<String, Integer> executeProc(CallableStatement callStatement, String mapperObjectName, Object dtoObject,
            Map<String, Object> additionalParam) 
    {
        try
        {
            if(mapperObjectName != null && !mapperObjectName.isEmpty())
            {
                IDTOMapper mapper = DTOMapper.dtoMap.get(mapperObjectName);
                mapper.mapObjectToStatement(dtoObject, callStatement, additionalParam);
            }

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