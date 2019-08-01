package com.dal.mycareer.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

import com.dal.mycareer.DAO.Impl.StudentApplicationDAO;
import com.dal.mycareer.DAO.Impl.StudentDetailsDAO;
import com.dal.mycareer.DAO.Impl.StudentJobsDAO;
import com.dal.mycareer.DAO.Interface.IStudentApplicationDAO;
import com.dal.mycareer.DAO.Interface.IStudentDetailsDAO;
import com.dal.mycareer.DAO.Interface.IStudentJobsDAO;
import com.dal.mycareer.DTO.AppliedJob;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.DTO.Student;

public class StudentApplicationModelTest
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void testSubmitJobApplication()
	{
		// Creating Mock Objects.
		HttpSession mockSession = mock(HttpSession.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		Model mockModel =  new ExtendedModelMap();
		IStudentDetailsDAO mockStudentDao = mock(StudentDetailsDAO.class);
		IStudentApplicationDAO mockStudentApplicationDao =mock(StudentApplicationDAO.class); 
		IStudentJobsDAO mockStudentJobDao= mock(StudentJobsDAO.class);
		MultipartFile mockFile = mock(MultipartFile.class);
		Student mockStudent = mock(Student.class);
		InputStream mockDocument = mock(InputStream.class);
		
		//Mock Data Preparation
		AppliedJob mockAppliedJob1 = new AppliedJob("1", mockDocument, "submitted", "1", "1", "Mock Job Title", "Mock Location 1", "2", "Co-op", "25", "38", "26-08-2019", "Developer", "None", "Mock Job Status", "2", "CENGN","4 Months");
		AppliedJob mockAppliedJob2 = new AppliedJob("2", mockDocument, "submitted", "1", "2", "Mock Job Title", "Mock Location 2", "2", "Co-op", "25", "38", "26-08-2019", "Developer", "None", "Mock Job Status", "2", "RBC","4 Months");
		List<AppliedJob> mockAppliedJobList = new ArrayList<AppliedJob>();
		mockAppliedJobList.add(mockAppliedJob1);
		mockAppliedJobList.add(mockAppliedJob2);		
				
		try
		{
			// Output for Mock Methods
			Mockito.when(req.getSession()).thenReturn(mockSession);
			Mockito.when(mockSession.getAttribute("sessionName")).thenReturn("mock@dal.ca");
			Mockito.when(mockStudentDao.getStudentDetails("mock@dal.ca")).thenReturn(mockStudent);
			Mockito.when(mockStudentApplicationDao.submitJobApplication(mockFile, 1, 1)).thenReturn(1);
			Mockito.when(mockStudent.getId()).thenReturn(1);
			Mockito.when(mockStudentJobDao.getAppliedJobList(1)).thenReturn(mockAppliedJobList);
				
			// Class object creation
			StudentApplicationModel studentApplicationModel =new StudentApplicationModel();
			Model returnedModel = studentApplicationModel.submitJobApplication(mockModel, mockFile, req, 1, mockStudentDao, mockStudentApplicationDao, mockStudentJobDao);
		
			//Fetch returned values
			List<AppliedJob> returnedAppliedJobs =(List<AppliedJob>) returnedModel.asMap().get("appliedJobs");
		
			//Assertion
			Assert.assertEquals(returnedAppliedJobs.get(0).getOrganization(),"CENGN");
		}
		catch (Exception e)
		{
			logger.debug("Error during execution of StudentModelTest: testApplyJob()");
		}
	}

	@Test
	public void testWithdrawJobApplication()
	{
		// Creating Mock Objects.
				HttpSession mockSession = mock(HttpSession.class);
				HttpServletRequest req = mock(HttpServletRequest.class);
				Model mockModel =  new ExtendedModelMap();
				Student mockStudent = mock(Student.class);
				InputStream mockDocument = mock(InputStream.class);
				IStudentDetailsDAO mockStudentDao = mock(StudentDetailsDAO.class);
				IStudentApplicationDAO mockStudentApplicationDao =mock(StudentApplicationDAO.class); 
				IStudentJobsDAO mockStudentJobDao= mock(StudentJobsDAO.class);
				
				//Mock Data Preparation
				AppliedJob mockAppliedJob2 = new AppliedJob("2", mockDocument, "submitted", "1", "2", "Mock Job Title", "Mock Location 2", "2", "Co-op", "25", "38", "26-08-2019", "Developer", "None", "Mock Job Status", "2", "RBC","4 Months");
				List<AppliedJob> mockAppliedJobList = new ArrayList<AppliedJob>();
				mockAppliedJobList.add(mockAppliedJob2);
				
				try
				{
					// Output for Mock Methods
					Mockito.when(req.getSession()).thenReturn(mockSession);
					Mockito.when(mockSession.getAttribute("sessionName")).thenReturn("mock@dal.ca");
					Mockito.when(mockStudentDao.getStudentDetails("mock@dal.ca")).thenReturn(mockStudent);
					Mockito.when(mockStudentApplicationDao.withdrawJobApplication(1)).thenReturn(1);
					Mockito.when(mockStudentJobDao.getAppliedJobList(1)).thenReturn(mockAppliedJobList);
					Mockito.when(mockStudent.getId()).thenReturn(1);
					
					// Class object creation
					StudentApplicationModel studentApplicationModel =new StudentApplicationModel();
					Model returnedModel = studentApplicationModel.withdrawJobApplication(mockModel, 1, req, mockStudentDao, mockStudentApplicationDao, mockStudentJobDao);
							
					//Fetch returned values
					List<AppliedJob> returnedAppliedJobs =(List<AppliedJob>) returnedModel.asMap().get("appliedJobs");

					//Assertion
					Assert.assertEquals(returnedAppliedJobs.get(0).getOrganization(),"RBC");
				}
				catch (Exception e)
				{
					logger.debug("Error during execution of StudentModelTest: testWithdrawApplication()");
				}	
	}

	@Test
	public void testCheckApplicationExists()
	{
		// Creating Mock Objects.
				HttpSession mockSession = mock(HttpSession.class);
				HttpServletRequest req = mock(HttpServletRequest.class);
				Model mockModel =  new ExtendedModelMap();
				StudentJobsDAO mockStudentDAO = mock(StudentJobsDAO.class);
				Student mockStudent = mock(Student.class);
				InputStream mockDocument = mock(InputStream.class);
				IStudentDetailsDAO mockStudentDao = mock(StudentDetailsDAO.class);
				IStudentApplicationDAO mockStudentApplicationDao =mock(StudentApplicationDAO.class); 
				
				try
				{
					// Output for Mock Methods
					Mockito.when(req.getSession()).thenReturn(mockSession);
					Mockito.when(mockSession.getAttribute("sessionName")).thenReturn("mock@dal.ca");
					Mockito.when(mockStudentDao.getStudentDetails("mock@dal.ca")).thenReturn(mockStudent);
					Mockito.when(mockStudentApplicationDao.checkApplicationExists(1,2)).thenReturn(1);
					Mockito.when(mockStudent.getId()).thenReturn(1);
				
					// Class object creation
					StudentApplicationModel studentApplicationModel =new StudentApplicationModel();
					Model returnedModel = studentApplicationModel.checkApplicationExists(mockModel, req, 2, mockStudentDao, mockStudentApplicationDao);
							
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
