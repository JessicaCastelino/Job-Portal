package com.dal.mycareer.DTOMapper;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.Map;

public interface IDTOMapper
{
    public Object mapStatementtoObject(ResultSet resultSet, Object jobDetails);
    public int[] mapObjectToStatement(Object jobDetails, CallableStatement callStatement, Map<String, Object> additionalParam);
}