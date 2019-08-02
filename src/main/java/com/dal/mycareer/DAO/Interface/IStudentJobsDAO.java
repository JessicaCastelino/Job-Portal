package com.dal.mycareer.DAO.Interface;

import java.sql.SQLException;
import java.util.List;

import com.dal.mycareer.DTO.AppliedJob;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.DTO.Student;

public interface IStudentJobsDAO {
	
	public List<JobDetails> getAllJobList(int studID, String type) throws SQLException;
	public List<AppliedJob> getAppliedJobList(int studentId) throws SQLException;
}
