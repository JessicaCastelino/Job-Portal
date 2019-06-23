package com.dal.mycareer.DAO.Impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dal.mycareer.DAO.Interface.IStudentProfileDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.StudentProfileDTO;

public class StudentProfileDAO implements IStudentProfileDAO {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public StudentProfileDTO getStudentProfile(String email) {

		Connection dbConn = DatabaseConnection.getConnection();
		StudentProfileDTO spDTO = new StudentProfileDTO();

		try {
			CallableStatement myStmt = dbConn.prepareCall("{call studprofile(?)}");

			// Set the parameters
			myStmt.setString(1, email);

			boolean result = myStmt.execute();

			if (result) {
				ResultSet rs = myStmt.getResultSet();
				ArrayList<String> courses = new ArrayList<String>();

				while (rs.next()) {
					spDTO.setFirstname(rs.getString(1));
					spDTO.setLastname(rs.getString(2));
					spDTO.setEmail(rs.getString(3));
					spDTO.setBannerid(rs.getString(4));
					spDTO.setPhonenumber(rs.getString(5));
					spDTO.setDegree(rs.getString(6));
					spDTO.setDepartment(rs.getString(7));
					spDTO.setProgram(rs.getString(8));
					courses.add(rs.getString(11));
				}

				spDTO.setCourseName(courses);
			}

		} catch (SQLException e) {
			LOGGER.error("Error while fetching student profile:" + e.getMessage());
		}
		return spDTO;

	}
}
