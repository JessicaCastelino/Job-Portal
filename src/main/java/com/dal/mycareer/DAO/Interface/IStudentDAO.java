package com.dal.mycareer.DAO.Interface;

import java.io.InputStream;
import java.util.List;

import com.dal.mycareer.DTO.AppliedJob;
import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.DTO.Student;

public interface IStudentDAO {
	public Student getStudentDetails(String userSessionName);

	public List<JobDetails> getAllJobList();

	public List<AppliedJob> getAppliedJobList(int studentId);

	public int applyForJob(InputStream inputStream);

	public int withdrawApplication(int studentId, int jobId);
}
