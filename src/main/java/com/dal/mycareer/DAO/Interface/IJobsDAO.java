package com.dal.mycareer.DAO.Interface;

import java.sql.SQLException;

import com.dal.mycareer.DTO.JobDetails;

public interface IJobsDAO 
{
    public boolean updateJobStatus(int jobRecordId, boolean jobStatus);
    public JobDetails fetchJob(int jobId) throws SQLException;
}