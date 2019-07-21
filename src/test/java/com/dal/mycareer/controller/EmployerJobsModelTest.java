package com.dal.mycareer.controller;

import static org.junit.Assert.assertTrue;

import com.dal.mycareer.DAOMocks.EmployerJobsDAOMock;
import com.dal.mycareer.DAO.Interface.IEmployerJobsDAO;
import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.model.EmployerJobsModel;

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
        EmployerJobsModel employerJobsModel = new EmployerJobsModel(employerJobsDAO);
        Job fetchedJobDetails = employerJobsModel.viewPostedJobDetails(1);
        Assert.assertTrue(new ReflectionEquals(expectedJobDetails).matches(fetchedJobDetails));
    }
    @Test
    public void updatejobDetailsTest ()
    {
        JobDetails expectedJobDetails = new JobDetails();
        expectedJobDetails.setId(1);
        expectedJobDetails.setJobTitle("Manager");
        expectedJobDetails.setHourPerWeek(40);
        expectedJobDetails.setRateOfPay(15);
        expectedJobDetails.setNoOfPosition(10);
        expectedJobDetails.setEmployeeId(1);
        JobDetails updatedJobDet = new JobDetails();
        updatedJobDet.setId(1);
        updatedJobDet.setJobTitle("Manager");
        updatedJobDet.setHourPerWeek(40);
        updatedJobDet.setRateOfPay(15);
        updatedJobDet.setNoOfPosition(10);
        updatedJobDet.setEmployeeId(1);
        EmployerJobsModel employerJobsModel = new EmployerJobsModel(employerJobsDAO);
        employerJobsModel.updateJobDetails(updatedJobDet);
        Job fetchedJobDetails = employerJobsModel.viewPostedJobDetails(1);
        Assert.assertTrue(new ReflectionEquals(expectedJobDetails).matches(fetchedJobDetails));
    }
}