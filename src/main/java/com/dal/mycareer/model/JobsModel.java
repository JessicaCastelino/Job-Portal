package com.dal.mycareer.model;

import com.dal.mycareer.DAO.Interface.IJobsDAO;
import com.dal.mycareer.imodel.IJobsModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobsModel implements IJobsModel 
{
    @Autowired
    private IJobsDAO jobsManagerDao;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
        logger.info("DL: updateJobStatus method started");
        return this.jobsManagerDao.updateJobStatus(jobRecordId, jobStatus);
    }
}