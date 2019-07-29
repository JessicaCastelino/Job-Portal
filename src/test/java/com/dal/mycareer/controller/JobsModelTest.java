package com.dal.mycareer.controller;

import com.dal.mycareer.DAO.Impl.JobsDAO;
import com.dal.mycareer.DAO.Interface.IJobsDAO;
import com.dal.mycareer.imodel.IJobsModel;
import com.dal.mycareer.model.JobsModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JobsModelTest
{
    IJobsDAO jobsMockDAO;
    IJobsModel jobsModel;

    @Before
    public void setUp ()
    {
        jobsMockDAO = new JobsDAO();
        jobsModel = new JobsModel(jobsMockDAO);
    }

    @Test
    public void updateJobStatusToOpenTest()
    {
        boolean isUpdated = jobsModel.updateJobStatus(1, true);
        //Assert.assertTrue("Updating job status to 'open' test failed", isUpdated);
    }

    @Test
    public void updateJobStatusToClosedTest()
    {
        boolean isUpdated = jobsModel.updateJobStatus(2, true);
        //Assert.assertTrue("Updating job status to 'closed' test failed", isUpdated);
    }
}
