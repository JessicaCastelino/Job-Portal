package com.dal.mycareer.DAO.Impl;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dal.mycareer.DAO.Interface.IStudentDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.AppliedJob;
import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.Student;

public class StudentDAO implements IStudentDAO {

	Connection con = null;
	CallableStatement callableStatement = null;

	@Override
	public List<Job> getAllJobList() {
		con = DatabaseConnection.getConnection();
		Job job = null;
		List<Job> jobs = new ArrayList<Job>();
		try {
			callableStatement = con.prepareCall("{call getAllJobList()}");
			boolean results = callableStatement.execute();

			while (results) {
				ResultSet rs = callableStatement.getResultSet();
				while (rs.next()) {
					job = new Job();
					job.setId(rs.getInt(1));
					job.setJobTitle(rs.getString(2));
					job.setLocation(rs.getString(3));
					job.setOpenPosition(Integer.toString(rs.getInt(4)));
					job.setJobType(rs.getString(5));
					job.setRateOfPay(Integer.toString(rs.getInt(6)));
					job.setHoursPerWeek(Integer.toString(rs.getInt(7)));
					job.setApplicationDeadline(rs.getDate(8));
					job.setJobDescription(rs.getString(9));
					job.setAdditionalInformation(rs.getString(10));
					job.setJobStatus(rs.getString(11));
					job.setEmployeeId(Integer.toString(rs.getInt(12)));
					job.setTerm(rs.getString(13));
					job.setOrganization(rs.getString(13));
					jobs.add(job);
					System.out.println(job.getId());
				}

				rs.close();
				results = callableStatement.getMoreResults();
			}
			return jobs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return jobs;
		} finally {
			try {
				callableStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<AppliedJob> getAppliedJobList(int studentId) {
		con = DatabaseConnection.getConnection();
		AppliedJob job = null;
		List<AppliedJob> appliedJobs = new ArrayList<AppliedJob>();
		try {
			callableStatement = con.prepareCall("{call getAppliedJobList('" + studentId + "')}");
			boolean results = callableStatement.execute();

			while (results) {
				ResultSet rs = callableStatement.getResultSet();
				while (rs.next()) {
					job = new AppliedJob();
					job.setId(Integer.toString(rs.getInt(1)));
					job.setDocument(rs.getBinaryStream(2));
					job.setApplicationStatus(rs.getString(3));
					job.setStudentId(Integer.toString(rs.getInt(4)));
					job.setJobId(Integer.toString(rs.getInt(5)));
					job.setJobTitle(rs.getString(7));
					job.setLocation(rs.getString(8));
					job.setOpenPosition(Integer.toString(rs.getInt(9)));
					job.setJobType(rs.getString(10));
					job.setRateOfPay(Integer.toString(rs.getInt(11)));
					job.setHoursPerWeek(Integer.toString(rs.getInt(12)));
					job.setApplicationDeadline(rs.getString(13));
					job.setJobDescription(rs.getString(14));
					job.setAdditionalInformation(rs.getString(15));
					job.setJobStatus(rs.getString(16));
					job.setEmployeeId(Integer.toString(rs.getInt(17)));
					job.setTerm(rs.getString(18));
					job.setOrganization(rs.getString(19));
					appliedJobs.add(job);
					System.out.println(job.getId());
				}

				rs.close();
				results = callableStatement.getMoreResults();
			}
			return appliedJobs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return appliedJobs;
		} finally {
			try {
				callableStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public int applyForJob(InputStream inputStream) {
		Connection c = DatabaseConnection.getConnection();
		System.out.println("connection applyForJob " + c);
		String sql = "INSERT INTO appliedJobs(id, document, applicationStatus, studentId, jobId) values (?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = c.prepareStatement(sql);
			statement.setString(1, "4");
			if (inputStream != null) {
				statement.setBlob(2, inputStream);
			}
			statement.setString(3, "Submited");
			statement.setString(4, "1");
			statement.setString(5, "2");
			int row = statement.executeUpdate();
			if (row > 0) {
				System.out.println("Inserted");
				return 1;
			} else {
				System.out.println("Not Inserted");
				return 0;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Student getStudentDetails(String userSessionName) {
		con = DatabaseConnection.getConnection();
		Student student = null;
		List<Job> jobs = new ArrayList<Job>();
		try {
			callableStatement = con.prepareCall("{call fetchStudentDetails('" + userSessionName + "')}");
			boolean results = callableStatement.execute();
			ResultSet rs = callableStatement.getResultSet();
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
			}
			rs.close();

			return student;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return student;
		} finally {
			try {
				callableStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
