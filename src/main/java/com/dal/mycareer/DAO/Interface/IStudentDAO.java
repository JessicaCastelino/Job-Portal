package com.dal.mycareer.DAO.Interface;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import com.dal.mycareer.DTO.AppliedJob;
import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.DTO.Student;

public interface IStudentDAO {
	public Student getStudentDetails(String userSessionName) throws SQLException;

	public List<JobDetails> getAllJobList(int studID, String type) throws SQLException;

	public List<AppliedJob> getAppliedJobList(int studentId) throws SQLException;

	public int applyForJob(InputStream inputStream, int studentId, int jobId) throws SQLException;

	public int withdrawApplication(int jobId) throws SQLException;
	
	public int alreadyApplied(int studentId, int jobId) throws SQLException;
}
