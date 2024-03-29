package com.dal.mycareer.DAO.Impl;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dal.mycareer.DAO.Interface.IManageApplicationsDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.Application;

@Repository
public class ManageApplicationsDAO implements IManageApplicationsDAO {
	private static final String CALL_FETCH_DOCUMENT = "{call fetchDocument(?)}";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<Application> getApplications(int jobRecordId) {
		List<Application> applicants = null;
		Connection conn = DatabaseConnection.getConnection();
		try {
			CallableStatement statement = conn.prepareCall("{CALL sp_getjobapplications(?)}");
			statement.setInt("jobRecordId", jobRecordId);
			ResultSet result = statement.executeQuery();
			Application applicant;
			applicants = new ArrayList<>();
			while (result.next()) {
				applicant = new Application();
				applicant.setApplicationId(result.getInt("id"));
				applicant.setJobId(result.getInt("jobId"));
				applicant.setApplicantFName(result.getString("firstName"));
				applicant.setApplicantLName(result.getString("lastName"));
				applicant.setApplicationStatus(result.getString("applicationStatus"));
				applicant.setCompletedCourses(result.getString("completedCourses"));
				applicant.setStudentId(result.getString("studentId"));
				applicant.setEmail(result.getString("email"));
				applicants.add(applicant);
			}
		} catch (SQLException e) {
			logger.error("Exception occurred at ManageApplicantsDAO: getApplicants " + e.getMessage());
			e.printStackTrace();
		}
		return applicants;
	}

	@Override
	public boolean updateApplicationStatus(int applicationId, String appStatus) {
		boolean isUpdateSuccess = false;
		CallableStatement callStatement = null;
		Connection con = null;
		try {
			con = DatabaseConnection.getConnection();
			callStatement = con.prepareCall("{call sp_updateApplicationStatus(?, ?)}");
			callStatement.setInt("applicationId", applicationId);
			callStatement.setString("applicationStatus", appStatus);
			int rowsAffected = callStatement.executeUpdate();
			if (rowsAffected > 0) {
				isUpdateSuccess = true;
			} else {
				isUpdateSuccess = false;
			}
		} catch (Exception ex) {
			logger.error("Error Occurred in ManageApplicationsDAO: updateApplicationStatus" + ex.getMessage());
		}

		return isUpdateSuccess;
	}
	
	@Override
	public InputStream fetchDocument(int applicationId) throws SQLException {
		logger.debug("ManageApplicationsDAO: fetchDocument method: Entered");
		Connection con = DatabaseConnection.getConnection();
		try {
	
			CallableStatement callableStatement = con.prepareCall(CALL_FETCH_DOCUMENT);
			callableStatement.setInt(1, applicationId);
			callableStatement.execute();
			ResultSet rs = callableStatement.getResultSet();
			rs.next();
			InputStream is =rs.getBinaryStream(1);
			rs.close();
			logger.debug("Document fetched for job application with ID: "+applicationId);
			return is;

		} catch (SQLException e) {
			logger.error( "SQLException Occurred in ManageApplicationsDAO: fetchDocument method:" + e.getMessage());
			throw new SQLException("Error while downloading the document.");
		}
		finally {
			DatabaseConnection.closeConnection(con);
		}
	}
}
