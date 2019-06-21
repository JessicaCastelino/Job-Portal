package com.dal.mycareer.DAO.Interface;

import java.io.InputStream;
import java.util.List;

import com.dal.mycareer.DTO.AppliedJob;
import com.dal.mycareer.DTO.Job;

public interface IStudentDAO {
public List<Job> getAllJobList();
public List<AppliedJob> getAppliedJobList();
public int applyForJob(InputStream inputStream);
}
