package com.dal.mycareer.DAO.Impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dal.mycareer.DAO.Interface.IStudentDetailsDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.Student;

public class StudentDetailsDAO implements IStudentDetailsDAO
{
	private static final String CALL_FETCH_STUDENT_DETAILS = "{call fetchStudentDetails(?)}";
	private Connection con = null;
	private CallableStatement callableStatement = null;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Student getStudentDetails(String userSessionName) throws SQLException
	{
		logger.debug("StudentDetailsDAO: getStudentDetails method: Entered");
		con = DatabaseConnection.getConnection();
		Student student = null;
		ResultSet rs = null;
		try {
			callableStatement = con.prepareCall(CALL_FETCH_STUDENT_DETAILS);
			callableStatement.setString(1,userSessionName);
			callableStatement.execute();
			rs = callableStatement.getResultSet();
			while (rs.next()) {
				student = new Student();
				student.setId(rs.getInt(1));
				student.setFirstname(rs.getString(2));
				student.setLastname(rs.getString(3));
				student.setBannerid(rs.getString(4));
				student.setEmail(rs.getString(5));
				student.setPhonenumber(rs.getString(6));
				student.setDegree(rs.getString(7));
				student.setDepartment(rs.getString(8));
				student.setProgram(rs.getString(9));
				student.setIsActive(rs.getInt(10));
				logger.debug("Details for student with session name: "+userSessionName+" and ID: "+student.getId()+" successfully fetched");
			}
			return student;
		} catch (SQLException e) {
			logger.error( "SQLException Occurred in StudentDetailsDAO: getStudentDetails method:" + e.getMessage());
			throw new SQLException("Error while fetching student details.");
		} finally {
			DatabaseConnection.closeDatabaseComponents(rs, callableStatement, con);
			logger.debug("StudentDetailsDAO: getStudentDetails method: Exit");
		}
	}

}
