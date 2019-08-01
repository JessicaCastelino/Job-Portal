package com.dal.mycareer.model;

import static org.mockito.Mockito.mock;

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

import com.dal.mycareer.DAO.Impl.RecruiterRegistrationRequestDAO;
import com.dal.mycareer.DAO.Interface.IRecruiterRegistrationRequestDAO;
import com.dal.mycareer.DTO.RecruiterRequest;
import com.dal.mycareer.emailengine.EmployerApprovalEmail;
import com.dal.mycareer.emailengine.EmployerRejectionEmailImpl;
import com.dal.mycareer.imodel.IRecruiterRegistrationRequestModel;
import com.dal.mycareer.passwordgenerator.PasswordGenerator;

public class RecruiterRegistrationRequestModelTest
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	IRecruiterRegistrationRequestDAO recruiterRequestDao;
	IRecruiterRegistrationRequestModel recruiterRequestModel;
	
	@Test
	public void testFetchRecruiterRequests()
	{
		//Creating mock objects
		HttpSession mockSession = mock(HttpSession.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		Model mockModel =  new ExtendedModelMap();
		recruiterRequestDao = mock(RecruiterRegistrationRequestDAO.class);
				
				//Data Preparation
				List<RecruiterRequest> requests = new ArrayList<RecruiterRequest>();
				RecruiterRequest mockRequest1 = new RecruiterRequest("101", "Firstname1", "Lastname1", "mock1@dal.ca", "CENGN1", "HR-Head");
				RecruiterRequest mockRequest2 = new RecruiterRequest("102", "Firstname2", "Lastname2", "mock2@dal.ca", "CENGN2", "HR");
				RecruiterRequest mockRequest3 = new RecruiterRequest("103", "Firstname3", "Lastname3", "mock3@dal.ca", "CENGN3", "Manager");
				requests.add(mockRequest1);
				requests.add(mockRequest2);
				requests.add(mockRequest3);
				try
				{
					// Output for Mock Methods
					Mockito.when(req.getSession()).thenReturn(mockSession);
					Mockito.when(mockSession.getAttribute("sessionName")).thenReturn("mock@dal.ca");
					Mockito.when(recruiterRequestDao.fetchAllRecruiterRequests()).thenReturn(requests);
					
					// Class object creation
					recruiterRequestModel=new RecruiterRegistrationRequestModel();
					Model returnedModel = recruiterRequestModel.fetchRecruiterRequests(mockModel, req, recruiterRequestDao);
				
					//Fetch returned values
					String isValid= (String)returnedModel.asMap().get("isValid");
					List<RecruiterRequest> returnedRequests = (List<RecruiterRequest>)returnedModel.asMap().get("recruiterRequests");
				
					//Assertion
					Assert.assertEquals(isValid, "NA");
					Assert.assertEquals(returnedRequests.get(0).getId(),"101");
				}
				catch (Exception e)
				{
					logger.debug("Error during execution of RecruiterRegistrationRequestModelTest: testFetchRecruiterRequests()");
				}	
	}

	@Test
	public void testApproveRecruiterRequest()
	{
		//Creating mock objects
		HttpSession mockSession = mock(HttpSession.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		Model mockModel =  new ExtendedModelMap();
		recruiterRequestDao = mock(RecruiterRegistrationRequestDAO.class);
		PasswordGenerator passwordGenerator = mock(PasswordGenerator.class);
		EmployerApprovalEmail approvalEmail = mock(EmployerApprovalEmail.class);
				
		//Data Preparation
		RecruiterRequest mockRequest1 = new RecruiterRequest("101", "Firstname1", "Lastname1", "mock1@dal.ca", "CENGN1", "HR-Head");
		List<RecruiterRequest> requests = new ArrayList<RecruiterRequest>();
		RecruiterRequest mockRequest2 = new RecruiterRequest("102", "Firstname2", "Lastname2", "mock2@dal.ca", "CENGN2", "HR");
		RecruiterRequest mockRequest3 = new RecruiterRequest("103", "Firstname3", "Lastname3", "mock3@dal.ca", "CENGN3", "Manager");
		requests.add(mockRequest2);
		requests.add(mockRequest3);
				
				try
				{		
					// Output for Mock Methods
					Mockito.when(req.getSession()).thenReturn(mockSession);
					Mockito.when(mockSession.getAttribute("sessionName")).thenReturn("mock@dal.ca");
					Mockito.when(passwordGenerator.generatePassword()).thenReturn("TESTPWPD");
					Mockito.when(recruiterRequestDao.fetchRecruiterRequest(101)).thenReturn(mockRequest1);
					Mockito.when(recruiterRequestDao.approveRequest(101, "mock@dal.ca", "TESTPWPD")).thenReturn(1);
					Mockito.when(recruiterRequestDao.fetchAllRecruiterRequests()).thenReturn(requests);

					// Class object creation
					recruiterRequestModel=new RecruiterRegistrationRequestModel();
					Model returnedModel = recruiterRequestModel.approveRecruiterRequest(mockModel, req, 101, recruiterRequestDao, approvalEmail, passwordGenerator);
						
					//Fetch returned values
					String isValid= (String)returnedModel.asMap().get("isValid");
					List<RecruiterRequest> returnedRequests = (List<RecruiterRequest>)returnedModel.asMap().get("recruiterRequests");
						
					//Assertion
					Assert.assertEquals(isValid, "approve");
					Assert.assertEquals(returnedRequests.get(0).getId(),"102");
				}
				catch (Exception e)
				{
					logger.debug("Error during execution of RecruiterRegistrationRequestModelTest: testApproveRecruiterRequest()");
				}	
	}

	@Test
	public void testRejectRecruiterRequest()
	{
		//Creating mock objects
				HttpSession mockSession = mock(HttpSession.class);
				HttpServletRequest req = mock(HttpServletRequest.class);
				Model mockModel =  new ExtendedModelMap();
				recruiterRequestDao = mock(RecruiterRegistrationRequestDAO.class);
				EmployerRejectionEmailImpl rejectEmail = mock(EmployerRejectionEmailImpl.class);
				
				//Data Preparation
				RecruiterRequest mockRequest1 = new RecruiterRequest("101", "Firstname1", "Lastname1", "mock1@dal.ca", "CENGN1", "HR-Head");
				List<RecruiterRequest> requests = new ArrayList<RecruiterRequest>();
				RecruiterRequest mockRequest2 = new RecruiterRequest("102", "Firstname2", "Lastname2", "mock2@dal.ca", "CENGN2", "HR");
				RecruiterRequest mockRequest3 = new RecruiterRequest("103", "Firstname3", "Lastname3", "mock3@dal.ca", "CENGN3", "Manager");
				requests.add(mockRequest2);
				requests.add(mockRequest3);
				
				try
				{
					// Output for Mock Methods
					Mockito.when(req.getSession()).thenReturn(mockSession);
					Mockito.when(mockSession.getAttribute("sessionName")).thenReturn("mock@dal.ca");
				
					Mockito.when(recruiterRequestDao.fetchRecruiterRequest(101)).thenReturn(mockRequest1);
					Mockito.when(recruiterRequestDao.rejectRequest(101)).thenReturn(1);
					Mockito.when(recruiterRequestDao.fetchAllRecruiterRequests()).thenReturn(requests);
				
					// Class object creation
					recruiterRequestModel=new RecruiterRegistrationRequestModel();
					Model returnedModel = recruiterRequestModel.rejectRecruiterRequest(mockModel, req, 101, recruiterRequestDao, rejectEmail);
				
					//Fetch returned values
					String isValid= (String)returnedModel.asMap().get("isValid");
					List<RecruiterRequest> returnedRequests = (List<RecruiterRequest>)returnedModel.asMap().get("recruiterRequests");
								
					//Assertion
					Assert.assertEquals(isValid, "reject");
					Assert.assertEquals(returnedRequests.get(0).getId(),"102");
				}
				catch (Exception e)
				{
					logger.debug("Error during execution of RecruiterRegistrationRequestModelTest: testRejectRecruiterRequest()");
				}		
	}

}
