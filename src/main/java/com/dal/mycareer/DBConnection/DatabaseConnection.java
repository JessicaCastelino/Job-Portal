package com.dal.mycareer.DBConnection;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dal.mycareer.propertiesparser.PropertiesParser;

public class DatabaseConnection {

	static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private static Connection connection;

	static {

		try {
			Properties dbProps = PropertiesParser.getPropertyMap();

			String dbURL = dbProps.getProperty("devIntDbURL");
			String dbPassword = dbProps.getProperty("devIntDbPassword");
			String dbUsername = dbProps.getProperty("devIntDbPassword");
			String driverClass = dbProps.getProperty("databaseDriverClass");

			Class.forName(driverClass);
			connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

			LOGGER.info("Connected to Database");

		} catch (SQLException | ClassNotFoundException ex) {
			LOGGER.error("Error while connecting to db : " + ex.getMessage());
		}
	}

	public static Connection getConnection() {
		return connection;
	}

}
