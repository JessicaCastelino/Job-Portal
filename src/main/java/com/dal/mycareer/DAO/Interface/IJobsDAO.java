package com.dal.mycareer.DAO.Interface;

public interface IJobsDAO 
{
    public boolean updateJobStatus(int jobRecordId, boolean jobStatus);
}