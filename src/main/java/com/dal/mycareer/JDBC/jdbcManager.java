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
    public Map executeProcedure(String procedureName, String mapperObjectName, Object dtoObject, Map<String, Object> additionalParam)
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
    protected void setParameters(final PreparedStatement statement, final Object... parameters) throws SQLException {
		for (int i = 0, length = parameters.length; i < length; i++) {
			final Object parameter = parameters[i];
			final int parameterIndex = i + 1;
			if (null == parameter) {
				statement.setObject(parameterIndex, null);
			}  else if (parameter instanceof Short) {
				statement.setShort(parameterIndex, (Short) parameter);
			} else if (parameter instanceof String) {
				statement.setString(parameterIndex, (String) parameter);
			} else if (parameter instanceof Date) {
				statement.setDate(parameterIndex, new java.sql.Date(((Date) parameter).getTime()));
			} else if (parameter instanceof Calendar) {
				statement.setDate(parameterIndex, new java.sql.Date(((Calendar) parameter).getTimeInMillis()));
			} else if (parameter instanceof Integer) {
				statement.setInt(parameterIndex, (Integer) parameter);
			} else if (parameter instanceof Long) {
				statement.setLong(parameterIndex, (Long) parameter);
			} else if (parameter instanceof Boolean) {
				statement.setBoolean(parameterIndex, (Boolean) parameter);
			} else if (parameter instanceof Character) {
				statement.setString(parameterIndex, String.valueOf(parameter));
			} else if (parameter instanceof Byte) {
				statement.setByte(parameterIndex, (Byte) parameter);
			}else if (parameter instanceof Float) {
				statement.setFloat(parameterIndex, (Float) parameter);
			} else if (parameter instanceof Double) {
				statement.setDouble(parameterIndex, (Double) parameter);
			} else if (parameter instanceof BigDecimal) {
				statement.setBigDecimal(parameterIndex, (BigDecimal) parameter);
			}else if (parameter instanceof Blob) {
				statement.setBlob(parameterIndex, (Blob) parameter);
			}else if (parameter instanceof InputStream) {
				statement.setBinaryStream(parameterIndex, (InputStream) parameter);
			}  
			else {
				throw new IllegalArgumentException(
						String.format("parameter type is not found. [param: %s, paramIndex: %s]", parameter,
								parameterIndex));
			}
		}
	}
}