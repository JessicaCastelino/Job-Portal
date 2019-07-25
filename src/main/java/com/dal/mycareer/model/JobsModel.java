package com.dal.mycareer.model;

import com.dal.mycareer.DAO.Interface.IJobsDAO;
import com.dal.mycareer.imodel.IJobsModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobsModel implements IJobsModel 
{
    @Autowired
	private IJobsDAO jobsManagerDao;

    public JobsModel()
    {

    }

    public JobsModel(IJobsDAO jobsDAO)
    {
        this.jobsManagerDao = jobsDAO;
    }

    @Override
    public boolean updateJobStatus(int jobRecordId, boolean jobStatus) 
    {
        return this.jobsManagerDao.updateJobStatus(jobRecordId, jobStatus);
    }
}