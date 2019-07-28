package com.dal.mycareer.model;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.dal.mycareer.DAO.Impl.StudentDAO;
import com.dal.mycareer.DTO.AppliedJob;
import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.DTO.Student;



public class StudentModelTest {

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
		
		// Output for Mock Methods
		Mockito.when(req.getSession()).thenReturn(mockSession);
		Mockito.when(mockSession.getAttribute("sessionName")).thenReturn("mock@dal.ca");
		Mockito.when(mockStudent.getId()).thenReturn(1);
		try
		{
			Mockito.when(mockStudentDAO.getAllJobList(1,"prerequisite")).thenReturn(mockJobList);
			Mockito.when(mockStudentDAO.getStudentDetails("mock@dal.ca")).thenReturn(mockStudent);
			Mockito.when(mockStudentDAO.getAppliedJobList(1)).thenReturn(mockAppliedJobList);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Class object creation
		StudentModel sm =new StudentModel();
		Model returnedModel = null;
		try
		{
			returnedModel = sm.fetchJobs(mockModel, req, mockStudentDAO);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Fetch returned values
		List<JobDetails> returnedJobs= (List<JobDetails>) returnedModel.asMap().get("jobs");
		List<AppliedJob> returnedAppliedJobs =(List<AppliedJob>) returnedModel.asMap().get("appliedJobs");
		
		//Assertion
		Assert.assertEquals(returnedJobs.get(0).getHourPerWeek().intValue(), 38);
		Assert.assertEquals(returnedAppliedJobs.get(0).getOrganization(),"CENGN");
		
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
						
		// Output for Mock Methods
		Mockito.when(req.getSession()).thenReturn(mockSession);
		Mockito.when(mockSession.getAttribute("sessionName")).thenReturn("mock@dal.ca");
		try
		{
			Mockito.when(mockStudentDAO.getStudentDetails("mock@dal.ca")).thenReturn(mockStudent);
			Mockito.when(mockStudentDAO.applyForJob(mockStream, 1, 1)).thenReturn(1);
			Mockito.when(mockFile.getInputStream()).thenReturn(mockStream);
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Mockito.when(mockStudent.getId()).thenReturn(1);
		
		
		// Class object creation
		StudentModel sm =new StudentModel();
		try
		{
			Model returnedModel = sm.applyJob(mockModel, mockFile, req, 1, mockStudentDAO);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				
		// Output for Mock Methods
		Mockito.when(req.getSession()).thenReturn(mockSession);
		Mockito.when(mockSession.getAttribute("sessionName")).thenReturn("mock@dal.ca");
		try
		{
			Mockito.when(mockStudentDAO.getStudentDetails("mock@dal.ca")).thenReturn(mockStudent);
			Mockito.when(mockStudentDAO.withdrawApplication(1)).thenReturn(1);
			Mockito.when(mockStudentDAO.getAppliedJobList(1)).thenReturn(mockAppliedJobList);
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mockito.when(mockStudent.getId()).thenReturn(1);
			
		// Class object creation
		StudentModel sm =new StudentModel();
		List<JobDetails> returnedJobs = null;
		List<AppliedJob> returnedAppliedJobs = null;
		try
		{
			Model returnedModel = sm.withdrawApplication(mockModel, 1, req, mockStudentDAO);
			//Fetch returned values
			returnedJobs= (List<JobDetails>) returnedModel.asMap().get("jobs");
			returnedAppliedJobs =(List<AppliedJob>) returnedModel.asMap().get("appliedJobs");
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

				
		//Assertion
		Assert.assertEquals(returnedJobs.get(0).getHourPerWeek().intValue(), 38);
		Assert.assertEquals(returnedAppliedJobs.get(0).getOrganization(),"RBC");
			
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
		Assert.assertEquals(returnedJobs.get(0).getOrganization(), "CENGN");
		Assert.assertEquals(returnedAppliedJobs.get(0).getOrganization(),"RBC");

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
						
		// Output for Mock Methods
		Mockito.when(req.getSession()).thenReturn(mockSession);
		Mockito.when(mockSession.getAttribute("sessionName")).thenReturn("mock@dal.ca");
		try
		{
			Mockito.when(mockStudentDAO.getStudentDetails("mock@dal.ca")).thenReturn(mockStudent);
			Mockito.when(mockStudentDAO.alreadyApplied(1,2)).thenReturn(1);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Mockito.when(mockStudent.getId()).thenReturn(1);
		
		// Class object creation
		StudentModel sm =new StudentModel();
		Model returnedModel = null;
		try
		{
			returnedModel = sm.jobApplicationExists(mockModel, req, 2, mockStudentDAO);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Fetch returned values
		String returnedStatus= (String) returnedModel.asMap().get("reqPage");							
		//Assertion
		Assert.assertEquals(returnedStatus, "alreadyApplied");

	}

}
