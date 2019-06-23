package com.dal.mycareer.DAO.Impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dal.mycareer.DAO.Interface.IEmployerSignupDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.EmployerSignupDTO;

public class EmployerSignupDAO implements IEmployerSignupDAO {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public boolean submitEmployerRequest(EmployerSignupDTO empSignupDTO) {

		Connection dbConn = DatabaseConnection.getConnection();

		boolean submitFlag = false;

		try {
			CallableStatement myStmt = dbConn.prepareCall("{call employerRequest(?, ?, ?, ?, ?, ?, ?)}");

			// Set the parameters
			myStmt.setString(1, empSignupDTO.getTitle());
			myStmt.setString(2, empSignupDTO.getFirstname());
			myStmt.setString(3, empSignupDTO.getLastname());
			myStmt.setString(4, empSignupDTO.getEmail());
			myStmt.setString(5, empSignupDTO.getDesignation());
			myStmt.setString(6, empSignupDTO.getCompanyName());
			myStmt.setInt(7, 0);
			myStmt.execute();

			submitFlag = true;

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());

			submitFlag = false;
		}

		return submitFlag;

	}
}
