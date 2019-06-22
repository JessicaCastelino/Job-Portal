package com.dal.mycareer.DAO.Impl;

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
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	// @Override
	public boolean updateApplicationStatus(int applicationId) {
		boolean isUpdateSuccess = false;
		CallableStatement callStatement = null;
		Connection con = null;
		try {
			con = DatabaseConnection.getConnection();
			callStatement = con.prepareCall("{call sp_closeactivejob(?)}");
			callStatement.setInt("applicationId", applicationId);
			int rowsAffected = callStatement.executeUpdate();
			if (rowsAffected > 0) {
				isUpdateSuccess = true;
			} else {
				isUpdateSuccess = false;
			}
		} catch (Exception ex) {
			LOGGER.error("Error Occurred in ManageApplicantsDAO: updateApplicationStatus" + ex.getMessage());
		}

		return isUpdateSuccess;
	}

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
			LOGGER.error("Exception occurred at ManageApplicantsDAO: getApplicants " + e.getMessage());
			e.printStackTrace();
		}
		return applicants;
	}
}
