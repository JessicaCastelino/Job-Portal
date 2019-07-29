package com.dal.mycareer.model;
import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.List;

import com.dal.mycareer.DAO.Interface.IEmployerJobsDAO;
import com.dal.mycareer.DAOMocks.EmployerJobsDAOMock;
import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.imodel.IEmployerJobsModel;
import com.dal.mycareer.model.EmployerJobsModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

public class EmployerJobsModelTest
{
    IEmployerJobsDAO employerJobsMockDAO;
    IEmployerJobsModel employerJobsModel; 
   
    @Before
    public void setUp ()
    {
         employerJobsMockDAO = new EmployerJobsDAOMock();
         employerJobsModel = new EmployerJobsModel(employerJobsMockDAO);
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
        employerJobsModel.updateJobDetails(updatedJobDet);
        Job fetchedJobDetails = employerJobsModel.viewPostedJobDetails(2);
        Assert.assertTrue(new ReflectionEquals(expectedJobDetails).matches(fetchedJobDetails));
    }

    @Test
    public void getActiveJobsTest()
    {
        int activeJobsCount = 2;
        List<Job> lstActiveJobs = employerJobsModel.getActiveJobs("Steve");
        assertEquals(activeJobsCount,lstActiveJobs.size());
    }
    @Test
    public void getClosedJobsTest()
    {
        int closedJobsCount = 2;       
        List<Job> lstClosedJobs = employerJobsModel.getClosedJobs("Michael");
        assertEquals(closedJobsCount,lstClosedJobs.size());
    }
    @Test
    public void InsertJobDetailsTest()
    {
        JobDetails postedJobDetails = new JobDetails();
        postedJobDetails.setJobTitle("Technical Architect");
        postedJobDetails.setHourPerWeek(40);
        postedJobDetails.setRateOfPay(30);
        postedJobDetails.setNoOfPosition(1);
        postedJobDetails.setEmployeeId(4);
        postedJobDetails.setJobStatus(true);
        postedJobDetails.setJobDescription("Designing the product from scratch");
        postedJobDetails.setLocation("Halifax");
        postedJobDetails.setJobType("8-month Co-op");
        long milliSeconds=System.currentTimeMillis();  
        Date applicationDeadline=new Date(milliSeconds);
        postedJobDetails.setApplicationDeadline(applicationDeadline);
        //jobDetails.setRequiredCourses(requiredCourses);
        //jobDetails.setSelectedCourseIds(selectedCourseIds);
        String currentUser = "Steve Balmer";
        JobDetails postedJob = employerJobsModel.InsertJobDetails(postedJobDetails, currentUser);
        Assert.assertTrue("Job Registration failed", postedJob.getId() != 0);

    }
}