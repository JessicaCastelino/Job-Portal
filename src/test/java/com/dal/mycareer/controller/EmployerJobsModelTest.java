package com.dal.mycareer.controller;

import java.util.List;

import com.dal.mycareer.DAO.Interface.IEmployerJobsDAO;
import com.dal.mycareer.DAOMocks.EmployerJobsDAOMock;
import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.model.EmployerJobsModel;
import static org.junit.Assert.assertEquals;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

public class EmployerJobsModelTest
{
    IEmployerJobsDAO employerJobsDAO;
   
    @Before
    public void setUp ()
    {
         employerJobsDAO = new EmployerJobsDAOMock();
    } 
    @Test
    public void viewPostedJobDetailsTest()
    {
        JobDetails expectedJobDetails = new JobDetails();
        expectedJobDetails.setId(1);
        expectedJobDetails.setJobTitle("Associate Consultant");
        expectedJobDetails.setHourPerWeek(40);
        expectedJobDetails.setRateOfPay(15);
        expectedJobDetails.setNoOfPosition(10);
        expectedJobDetails.setEmployeeId(1);
        expectedJobDetails.setJobStatus(true);
        EmployerJobsModel employerJobsModel = new EmployerJobsModel(employerJobsDAO);
        Job fetchedJobDetails = employerJobsModel.viewPostedJobDetails(1);
        Assert.assertTrue(new ReflectionEquals(expectedJobDetails).matches(fetchedJobDetails));
    }
    @Test
    public void updatejobDetailsTest ()
    {
        JobDetails expectedJobDetails = new JobDetails();
        expectedJobDetails.setId(2);
        expectedJobDetails.setJobTitle("Manager");
        expectedJobDetails.setHourPerWeek(40);
        expectedJobDetails.setRateOfPay(15);
        expectedJobDetails.setNoOfPosition(10);
        expectedJobDetails.setEmployeeId(2);
        expectedJobDetails.setJobStatus(false);
        JobDetails updatedJobDet = new JobDetails();
        updatedJobDet.setId(2);
        updatedJobDet.setJobTitle("Manager");
        updatedJobDet.setHourPerWeek(40);
        updatedJobDet.setRateOfPay(15);
        updatedJobDet.setNoOfPosition(10);
        updatedJobDet.setEmployeeId(2);
        updatedJobDet.setJobStatus(false);
        EmployerJobsModel employerJobsModel = new EmployerJobsModel(employerJobsDAO);
        employerJobsModel.updateJobDetails(updatedJobDet);
        Job fetchedJobDetails = employerJobsModel.viewPostedJobDetails(2);
        Assert.assertTrue(new ReflectionEquals(expectedJobDetails).matches(fetchedJobDetails));
    }

    @Test
    public void getActiveJobsTest()
    {
        int activeJobsCount = 2;
        EmployerJobsModel employerJobsModel = new EmployerJobsModel(employerJobsDAO);
        List<Job> lstActiveJobs = employerJobsModel.getActiveJobs("Steve");
        assertEquals(activeJobsCount,lstActiveJobs.size());
    }
    @Test
    public void getClosedJobsTest()
    {
        int closedJobsCount = 2;
        EmployerJobsModel employerJobsModel = new EmployerJobsModel(employerJobsDAO);
        List<Job> lstClosedJobs = employerJobsModel.getClosedJobs("Michael");
        assertEquals(closedJobsCount,lstClosedJobs.size());
    } 
}