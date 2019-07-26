package com.dal.mycareer.JDBC;

import java.sql.CallableStatement;
import java.util.HashMap;
import java.util.Map;

import com.dal.mycareer.DTOMapper.DTOMapper;
import com.dal.mycareer.DTOMapper.IDTOMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class insertHandler extends jdbcManager {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<String, Integer> procResult;
    @Override
    public Map executeProc(CallableStatement callStatement, String mapperObjectName, Object dtoObject, Map<String, Object> additionalParam) {
        IDTOMapper mapper = DTOMapper.dtoMap.get(mapperObjectName);
        int[] outParams = mapper.mapObjectToStatement(dtoObject, callStatement, additionalParam);
        try
        {
           int rowsAffected = callStatement.executeUpdate();
           extractOutputParam(callStatement, outParams, rowsAffected);
        }
        catch(Exception ex)
        {
            logger.error( "Error Occurred in executeProc :" + ex.getMessage());
        }
        return procResult;
    }
    private void extractOutputParam (CallableStatement callStatement, int[] outparamIndex, int rowsAffected)
    {
        procResult = new HashMap<>();
        int outParamValue = 0;
        try
        {
        for(int i = 0; i < outparamIndex.length; i++)
        {
            outParamValue = callStatement.getInt(outparamIndex[i]);
            procResult.put(Integer.toString(outparamIndex[i]), outParamValue);
        }
        procResult.put("rowsAffected",rowsAffected);
        }
        catch(Exception ex)
        {
            logger.error( "Error Occurred in executeProc :" + ex.getMessage());
        }
    }

}