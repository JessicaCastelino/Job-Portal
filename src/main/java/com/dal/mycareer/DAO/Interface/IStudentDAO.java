package com.dal.mycareer.DAO.Interface;

import java.util.List;

import com.dal.mycareer.model.Job;

public interface IStudentDAO {
public List<Job> getAllJobList();
public List<Job> getAppliedJobList();
}
