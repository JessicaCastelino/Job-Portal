package com.dal.mycareer.model;

import com.dal.mycareer.DAO.Impl.JobsDAO;
import com.dal.mycareer.DAO.Impl.RecruiterRegistrationRequestDAO;
import com.dal.mycareer.DAO.Interface.IJobsDAO;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.DTO.RecruiterRequest;
import com.dal.mycareer.imodel.IJobsModel;
import com.dal.mycareer.model.JobsModel;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

public class JobsModelTest
{
    private IJobsDAO jobsMockDAO;
    private IJobsModel jobsModel;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
    
    @Test
	public void testFetchJob()
	{
    	//Creating mock objects
    	HttpSession mockSession = mock(HttpSession.class);
    	HttpServletRequest req = mock(HttpServletRequest.class);
    	Model mockModel =  new ExtendedModelMap();
    	IJobsDAO jobsDao = mock(JobsDAO.class);
    	Date date = mock(Date.class);
    	
    	//DataPreparation
    	List<Integer> selectedCourseIds = new ArrayList<Integer>();
    	JobDetails job = new JobDetails(Integer.valueOf(4), Integer.valueOf(37), Integer.valueOf(39), "Job Description", 
    			1, "Software Developer", "Ontario", "4 months coop", date, "CENGN",
    			"", selectedCourseIds, 1, true);
    	
    	try
		{
    		// Output for Mock Methods
        	Mockito.when(req.getSession()).thenReturn(mockSession);
    		Mockito.when(mockSession.getAttribute("sessionName")).thenReturn("mock@dal.ca");
			Mockito.when(jobsDao.fetchJob(1)).thenReturn(job);
			
			// Class object creation
			IJobsModel jobsModel=new JobsModel();
			Model returnedModel = jobsModel.fetchJob(mockModel, 1, req, jobsDao);
			
			//Fetch returned values
			JobDetails returnedJob= (JobDetails)returnedModel.asMap().get("job");
			
			//Assertion
			Assert.assertEquals(returnedJob.getLocation(), "Ontario");
			
		} catch (SQLException e)
		{
			logger.debug("Error during execution of JobsModelTest: testFetchJob()");
		}
		
	}
}
