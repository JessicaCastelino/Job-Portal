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

public class SelectHandler extends JdbcManager {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private Map<String, Integer> procResult;

	public SelectHandler() {
		procResult = new HashMap<>();
	}

	@Override
	public Map<String, Integer> executeProc(CallableStatement callStatement, String mapperObjectName, Object dtoObject,
			Map<String, Object> additionalParam) {
		ResultSet result = null;
		try {
			ProcedureParamLoader paramLoader = new ProcedureParamLoader();
			paramLoader.fillInputParameters(callStatement, additionalParam);
			result = callStatement.executeQuery();
			if (result !=  null)
			{
			fillDTOObjectFromResultSet(mapperObjectName, result, dtoObject);
			procResult.put("isObjectFilled", 1);
			}
		} catch (Exception ex) {
			logger.error("Error Occurred in executeProc :" + ex.getMessage());
		} finally {
			DatabaseConnection.closeDatabaseComponents(result, callStatement, null);
		}
		return procResult;
	}

	private void fillDTOObjectFromResultSet(String mapperObjectName, ResultSet result, Object dtoObject) {
		if (dtoObject != null) {
			IDTOMapper mapper = DTOMapper.dtoMap.get(mapperObjectName);
			mapper.mapStatementtoObject(result, dtoObject);
		} else {
			try {
				if (result.next()) {
					procResult.put("recordExist", 1);
				}
				else
				{
					procResult.put("recordExist", 0);
				}
			} catch (Exception ex) {
				logger.error("Error Occurred in fillDTOObjectFromResultSet :" + ex.getMessage());
			}

		}
	}
}