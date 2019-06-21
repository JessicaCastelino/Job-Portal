package com.dal.mycareer.DAO.Impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dal.mycareer.DAO.Interface.ILoginDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.UserLogin;

public class LoginDAO implements ILoginDAO {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean isValidUser(UserLogin user) {

		boolean validUserFlag = false;
		Connection dbConn = DatabaseConnection.getConnection();

		try {
			CallableStatement myStmt = dbConn.prepareCall("{call isValidLogin(?, ?, ?, ?)}");

			//Set the parameters
			myStmt.setString(1, user.getUsername());
			myStmt.setString(2, user.getPassword());
			myStmt.setString(3, user.getRole());
			myStmt.registerOutParameter(4, Types.INTEGER);
			myStmt.execute();

			if (myStmt.getInt(4) > 0) {
				validUserFlag = true;
			}

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}

		return validUserFlag;
	}

}
