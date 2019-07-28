package com.dal.mycareer.DBConnection;

import java.lang.invoke.MethodHandles;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.dal.mycareer.propertiesparser.PropertiesParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConnection {

	static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private static Connection connection;

	public static Connection getConnection() {
		if (connection == null) {
			try {
				Properties dbProps = PropertiesParser.getPropertyMap();
				String dbURL = dbProps.getProperty("devIntDbURL");
				String dbPassword = dbProps.getProperty("devIntDbPassword");
				String dbUsername = dbProps.getProperty("devIntDbUsername");
				String driverClass = dbProps.getProperty("databaseDriverClass");
				Class.forName(driverClass);
				connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

				logger.info("Connected to Database");

			} catch (SQLException | ClassNotFoundException ex) {
				logger.info("Error while connecting to db : " + ex.getMessage());
			}

		}
		return connection;
	}

	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			logger.info("Error while closing the connection : " + e.getMessage());
		}
	}

	public static void closeDatabaseComponents(ResultSet resultSet, CallableStatement callableStatement) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException sqlEx) {
				logger.error("Error Occurred in closing resultSet :" + sqlEx.getMessage());
			}
		}
		if (callableStatement != null) {
			try {
				callableStatement.close();
			} catch (SQLException sqlEx) {
				logger.error("Error Occurred in closing callable Statement :" + sqlEx.getMessage());
			}
		}
	}

	public static void closeDatabaseComponents(CallableStatement callableStatement) {

		if (callableStatement != null) {
			try {
				callableStatement.close();
			} catch (SQLException sqlEx) {
				logger.error("Error Occurred in closing callable Statement :" + sqlEx.getMessage());
			}
		}

	}
}
