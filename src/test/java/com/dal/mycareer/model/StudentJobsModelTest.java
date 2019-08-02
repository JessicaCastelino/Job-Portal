package com.dal.mycareer.model;

import static org.mockito.Mockito.mock;

import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.dal.mycareer.DAO.Impl.StudentDetailsDAO;
import com.dal.mycareer.DAO.Impl.StudentJobsDAO;
import com.dal.mycareer.DAO.Interface.IStudentDetailsDAO;
import com.dal.mycareer.DAO.Interface.IStudentJobsDAO;
import com.dal.mycareer.DTO.AppliedJob;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.DTO.Student;

public class StudentJobsModelTest
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void testFetchStudentSpecificJobs()
	{
		// Creating Mock Objects.
				HttpSession mockSession = mock(HttpSession.class);
				HttpServletRequest req = mock(HttpServletRequest.class);
				Model mockModel = new ExtendedModelMap();
				
				// Mock objects
				IStudentJobsDAO mockStudentJobDao = mock(StudentJobsDAO.class); 
				IStudentDetailsDAO mockStudentDao = mock(StudentDetailsDAO.class);
				Student mockStudent = mock(Student.class);
				Map<String, Object> mockMap = Mockito.mock(Map.class);
				InputStream mockDocument = mock(InputStream.class);
				StudentJobsDAO mockStudDAO = mock(StudentJobsDAO.class);
			    List<Integer> mockList = mock(List.class);
			    Date mockDate = mock(Date.class);
				//Mock Data Preparation
				AppliedJob mockAppliedJob1 = new AppliedJob("1", mockDocument, "submitted", "1", "1", "Mock Job Title", "Mock Location 1", "2", "Co-op", "25", "38", "26-08-2019", "Developer", "None", "Mock Job Status", "2", "CENGN","4 Months");
				AppliedJob mockAppliedJob2 = new AppliedJob("2", mockDocument, "submitted", "1", "2", "Mock Job Title", "Mock Location 2", "2", "Co-op", "25", "38", "26-08-2019", "Developer", "None", "Mock Job Status", "2", "RBC","4 Months");
				
				JobDetails mockJobDetails1=new JobDetails(2, 25, 38, "Developer", 1, "Mock Job Title", "Mock Location 1", "Co-op", mockDate, "CENGN", "Dummy Required Courses", mockList, 101, true);
				JobDetails mockJobDetails2=new JobDetails(2, 25, 38, "Developer", 2, "Mock Job Title", "Mock Location 2","Co-op", mockDate, "RBC", "Dummy Required Courses", mockList, 201, true);
				JobDetails mockJobDetails3=new JobDetails(3, 30, 37, "Developer", 3, "Mock Job Title", "Mock Location 3", "Co-op", mockDate, "IBM", "Dummy Required Courses", mockList, 301, true);
				
				List<JobDetails> mockJobList = new ArrayList<JobDetails>();
				mockJobList.add(mockJobDetails1);
				mockJobList.add(mockJobDetails2);
				mockJobList.add(mockJobDetails3);
				
				List<AppliedJob> mockAppliedJobList = new ArrayList<AppliedJob>();
				mockAppliedJobList.add(mockAppliedJob1);
				mockAppliedJobList.add(mockAppliedJob2);
				
				try
				{
					// Output for Mock Methods
					Mockito.when(req.getSession()).thenReturn(mockSession);
					Mockito.when(mockSession.getAttribute("sessionName")).thenReturn("mock@dal.ca");
					Mockito.when(mockStudent.getId()).thenReturn(1);
					Mockito.when(mockStudentJobDao.getAllJobList(1,"prerequisite")).thenReturn(mockJobList);
					Mockito.when(mockStudentDao.getStudentDetails("mock@dal.ca")).thenReturn(mockStudent);
					Mockito.when(mockStudentJobDao.getAppliedJobList(1)).thenReturn(mockAppliedJobList);
				
					// Class object creation
					StudentJobsModel studentJobsModel =new StudentJobsModel();
					Model returnedModel = studentJobsModel.fetchStudentSpecificJobs(mockModel, req, mockStudentJobDao, mockStudentDao);
				
					//Fetch returned values
					List<JobDetails> returnedJobs= (List<JobDetails>) returnedModel.asMap().get("jobs");
					List<AppliedJob> returnedAppliedJobs =(List<AppliedJob>) returnedModel.asMap().get("appliedJobs");
				
					//Assertion
					Assert.assertEquals(returnedJobs.get(0).getHourPerWeek().intValue(), 38);
					Assert.assertEquals(returnedAppliedJobs.get(0).getOrganization(),"CENGN");
				}
				catch (Exception e)
				{
					logger.debug("Error during execution of StudentModelTest: testFetchJobs()");
				}
	}

	@Test
	public void testFilterStudentSpecificJobList()
	{
		// Creating Mock Objects.
				HttpSession mockSession = mock(HttpSession.class);
				HttpServletRequest req = mock(HttpServletRequest.class);
				Model mockModel =  new ExtendedModelMap();
				StudentJobsDAO mockStudentDAO = mock(StudentJobsDAO.class);
				Student mockStudent = mock(Student.class);
				InputStream mockDocument = mock(InputStream.class);
				Date mockDate = mock(Date.class);
				List<Integer> mockList = mock(List.class);
				
				
				// Output for Mock Methods
				Mockito.when(req.getSession()).thenReturn(mockSession);
				Mockito.when(mockSession.getAttribute("sessionName")).thenReturn("mock@dal.ca");
							
				// Class object creation
				StudentJobsModel studentJobsModel =new StudentJobsModel();
				Model returnedModel = studentJobsModel.filterStudentSpecificJobList(mockModel, req, "Non-existent Mock Location", "");
						
				//Fetch returned values
				List<JobDetails> returnedJobs= (List<JobDetails>) returnedModel.asMap().get("jobs");
				List<AppliedJob> returnedAppliedJobs =(List<AppliedJob>) returnedModel.asMap().get("appliedJobs");
								
				//Assertion
				logger.debug("********************"+returnedJobs.size());
				Assert.assertEquals(returnedJobs.size(), 0);
				Assert.assertEquals(returnedAppliedJobs.size(),2);
	}

}
