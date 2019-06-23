package com.dal.mycareer.model;

import com.dal.mycareer.DAO.Interface.IJobsDAO;
import com.dal.mycareer.imodel.IJobsModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobsModel implements IJobsModel 
{
    @Autowired
	IJobsDAO jobsManagerDao;

    @Override
    public boolean updateJobStatus(int jobRecordId, boolean jobStatus) {
        return jobsManagerDao.updateJobStatus(jobRecordId, jobStatus);
    }
}