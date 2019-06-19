package com.dal.mycareer.DAO.Impl;

import java.sql.Connection;

import com.dal.mycareer.DAO.Interface.ILoginDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.UserLogin;

public class LoginDAO implements ILoginDAO {

	@Override
	public boolean isValidUser(UserLogin user) {

		Connection dbConn = DatabaseConnection.getConnection();
		
		return false;
	}

}
