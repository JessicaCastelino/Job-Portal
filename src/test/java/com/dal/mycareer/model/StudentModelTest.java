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
import org.springframework.web.multipart.MultipartFile;

import com.dal.mycareer.DAO.Impl.StudentDAO;
import com.dal.mycareer.DTO.AppliedJob;
import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.DTO.Student;



public class StudentModelTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void testFetchJobs() {
			
		// Creating Mock Objects.
		HttpSession mockSession = mock(HttpSession.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		Model mockModel = new ExtendedModelMap();
		
		// Mock objects
		StudentDAO mockStudentDAO = mock(StudentDAO.class);
		Student mockStudent = mock(Student.class);
		Map<String, Object> mockMap = Mockito.mock(Map.class);
		InputStream mockDocument = mock(InputStream.class);
		StudentDAO mockStudDAO = mock(StudentDAO.class);
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
			Mockito.when(mockStudentDAO.getAllJobList(1,"prerequisite")).thenReturn(mockJobList);
			Mockito.when(mockStudentDAO.getStudentDetails("mock@dal.ca")).thenReturn(mockStudent);
			Mockito.when(mockStudentDAO.getAppliedJobList(1)).thenReturn(mockAppliedJobList);
		
			// Class object creation
			StudentModel sm =new StudentModel();
			Model returnedModel = sm.fetchJobs(mockModel, req, mockStudentDAO);
		
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
	public void testViewJobs() {
		// Creating Mock Objects.
		HttpSession mockSession = mock(HttpSession.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		Model mockModel =  new ExtendedModelMap();
		StudentDAO mockStudentDAO = mock(StudentDAO.class);
		List<Integer> mockList = mock(List.class);
		Date mockDate = mock(Date.class);
		
		// Output for Mock Methods
		Mockito.when(req.getSession()).thenReturn(mockSession);
		Mockito.when(mockSession.getAttribute("sessionName")).thenReturn("mock@dal.ca");
		
		// Class object creation
		StudentModel sm =new StudentModel();
		Model returnedModel=sm.viewJobs(mockModel, 1, req, mockStudentDAO);
		
		//Fetch returned values
		Job returnedJob=(Job)returnedModel.asMap().get("job");
		
		//Assertion
		Assert.assertEquals(returnedJob.getEmployeeId(), 101);
					
	}

	@Test
	public void testApplyJob() {
		// Creating Mock Objects.
		HttpSession mockSession = mock(HttpSession.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		Model mockModel =  new ExtendedModelMap();
		StudentDAO mockStudentDAO = mock(StudentDAO.class);
		MultipartFile mockFile = mock(MultipartFile.class);
		Student mockStudent = mock(Student.class);
		InputStream mockStream = mock(InputStream.class);
		
		try
		{
			// Output for Mock Methods
			Mockito.when(req.getSession()).thenReturn(mockSession);
			Mockito.when(mockSession.getAttribute("sessionName")).thenReturn("mock@dal.ca");
			Mockito.when(mockStudentDAO.getStudentDetails("mock@dal.ca")).thenReturn(mockStudent);
			Mockito.when(mockStudentDAO.applyForJob(mockStream, 1, 1)).thenReturn(1);
			Mockito.when(mockFile.getInputStream()).thenReturn(mockStream);
			Mockito.when(mockStudent.getId()).thenReturn(1);
		
			// Class object creation
			StudentModel sm =new StudentModel();
			Model returnedModel = sm.applyJob(mockModel, mockFile, req, 1, mockStudentDAO);
		}
		catch (Exception e)
		{
			logger.debug("Error during execution of StudentModelTest: testApplyJob()");
		}
			
	}

	@Test
	public void testWithdrawApplication() {
		// Creating Mock Objects.
		HttpSession mockSession = mock(HttpSession.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		Model mockModel =  new ExtendedModelMap();
		StudentDAO mockStudentDAO = mock(StudentDAO.class);
		Student mockStudent = mock(Student.class);
		InputStream mockDocument = mock(InputStream.class);
		
		
		//Mock Data Preparation
		AppliedJob mockAppliedJob2 = new AppliedJob("2", mockDocument, "submitted", "1", "2", "Mock Job Title", "Mock Location 2", "2", "Co-op", "25", "38", "26-08-2019", "Developer", "None", "Mock Job Status", "2", "RBC","4 Months");
		List<AppliedJob> mockAppliedJobList = new ArrayList<AppliedJob>();
		mockAppliedJobList.add(mockAppliedJob2);
		
		try
		{
			// Output for Mock Methods
			Mockito.when(req.getSession()).thenReturn(mockSession);
			Mockito.when(mockSession.getAttribute("sessionName")).thenReturn("mock@dal.ca");
			Mockito.when(mockStudentDAO.getStudentDetails("mock@dal.ca")).thenReturn(mockStudent);
			Mockito.when(mockStudentDAO.withdrawApplication(1)).thenReturn(1);
			Mockito.when(mockStudentDAO.getAppliedJobList(1)).thenReturn(mockAppliedJobList);
			Mockito.when(mockStudent.getId()).thenReturn(1);
			
			// Class object creation
			StudentModel sm =new StudentModel();
			Model returnedModel = sm.withdrawApplication(mockModel, 1, req, mockStudentDAO);
			//Fetch returned values
			List<JobDetails> returnedJobs= (List<JobDetails>) returnedModel.asMap().get("jobs");
			List<AppliedJob> returnedAppliedJobs =(List<AppliedJob>) returnedModel.asMap().get("appliedJobs");

				
			//Assertion
			Assert.assertEquals(returnedJobs.get(0).getHourPerWeek().intValue(), 38);
			Assert.assertEquals(returnedAppliedJobs.get(0).getOrganization(),"RBC");
		}
		catch (Exception e)
		{
			logger.debug("Error during execution of StudentModelTest: testWithdrawApplication()");
		}	
			
	}

	@Test
	public void testFilterJobs() {
		// Creating Mock Objects.
		HttpSession mockSession = mock(HttpSession.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		Model mockModel =  new ExtendedModelMap();
		StudentDAO mockStudentDAO = mock(StudentDAO.class);
		Student mockStudent = mock(Student.class);
		InputStream mockDocument = mock(InputStream.class);
				
		// Output for Mock Methods
		Mockito.when(req.getSession()).thenReturn(mockSession);
		Mockito.when(mockSession.getAttribute("sessionName")).thenReturn("mock@dal.ca");
					
		// Class object creation
		StudentModel sm =new StudentModel();
		Model returnedModel = sm.filterJobs(mockModel, req, "Mock Location 1", "", mockStudentDAO);

		//Fetch returned values
		List<JobDetails> returnedJobs= (List<JobDetails>) returnedModel.asMap().get("jobs");
		List<AppliedJob> returnedAppliedJobs =(List<AppliedJob>) returnedModel.asMap().get("appliedJobs");
						
		//Assertion
		// Assert.assertEquals(returnedJobs.get(0).getOrganization(), "CENGN");
		// Assert.assertEquals(returnedAppliedJobs.get(0).getOrganization(),"RBC");

	}

	@Test
	public void testJobApplicationExists() {
		// Creating Mock Objects.
		HttpSession mockSession = mock(HttpSession.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		Model mockModel =  new ExtendedModelMap();
		StudentDAO mockStudentDAO = mock(StudentDAO.class);
		Student mockStudent = mock(Student.class);
		InputStream mockDocument = mock(InputStream.class);
		
		try
		{
			// Output for Mock Methods
			Mockito.when(req.getSession()).thenReturn(mockSession);
			Mockito.when(mockSession.getAttribute("sessionName")).thenReturn("mock@dal.ca");
			Mockito.when(mockStudentDAO.getStudentDetails("mock@dal.ca")).thenReturn(mockStudent);
			Mockito.when(mockStudentDAO.alreadyApplied(1,2)).thenReturn(1);
			Mockito.when(mockStudent.getId()).thenReturn(1);
		
			// Class object creation
			StudentModel sm =new StudentModel();
			Model returnedModel = sm.jobApplicationExists(mockModel, req, 2, mockStudentDAO);

			//Fetch returned values
			String returnedStatus= (String) returnedModel.asMap().get("reqPage");							
			
			//Assertion
			Assert.assertEquals(returnedStatus, "alreadyApplied");
		}
		catch (Exception e)
		{
			logger.debug("Error during execution of StudentModelTest: testJobApplicationExists()");
		}
	}

}
