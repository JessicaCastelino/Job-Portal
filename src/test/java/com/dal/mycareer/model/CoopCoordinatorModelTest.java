package com.dal.mycareer.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dal.mycareer.DAO.Impl.CoopCordinatorDAO;
import com.dal.mycareer.DAO.Interface.ICoopCordinatorDAO;
import com.dal.mycareer.DAOMocks.CoopCordinatorDAOMock;
import com.dal.mycareer.DTO.RecruiterRequest;
import com.dal.mycareer.emailengine.EmployerApprovalEmail;
import com.dal.mycareer.emailengine.EmployerRejectionEmailImpl;
import com.dal.mycareer.passwordgenerator.PasswordGenerator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.dal.mycareer.DAO.Impl.CoopCordinatorDAO;
import com.dal.mycareer.DTO.RecruiterRequest;
import com.dal.mycareer.emailengine.EmployerApprovalEmail;
import com.dal.mycareer.emailengine.EmployerRejectionEmailImpl;
import com.dal.mycareer.passwordgenerator.PasswordGenerator;

public class CoopCoordinatorModelTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	ICoopCordinatorDAO coopCordinatorDAO;
    @Before
    public void setUp()
    {
        coopCordinatorDAO = new CoopCordinatorDAOMock();
    }

	@Test
	public void testFetchRecruiterRequests() {
		
		//Creating mock objects
		HttpSession mockSession = mock(HttpSession.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		Model mockModel =  new ExtendedModelMap();
		CoopCordinatorDAO coopCordinatorDAO = mock(CoopCordinatorDAO.class);
		
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
			Mockito.when(coopCordinatorDAO.fetchRecruiterRequests()).thenReturn(requests);
			
			// Class object creation
			CoopCoordinatorModel cm=new CoopCoordinatorModel();
			Model returnedModel = cm.fetchRecruiterRequests(mockModel, req, coopCordinatorDAO);
		
			//Fetch returned values
			String isValid= (String)returnedModel.asMap().get("isValid");
			List<RecruiterRequest> returnedRequests = (List<RecruiterRequest>)returnedModel.asMap().get("recruiterRequests");
		
			//Assertion
			Assert.assertEquals(isValid, "NA");
			Assert.assertEquals(returnedRequests.get(0).getId(),"101");
		}
		catch (Exception e)
		{
			logger.debug("Error during execution of CoopCoordinatorModelTest: testFetchRecruiterRequests()");
		}	
	}

	@Test
	public void testApproveRecruiterRequest() {
		//Creating mock objects
		HttpSession mockSession = mock(HttpSession.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		Model mockModel =  new ExtendedModelMap();
		CoopCordinatorDAO coopCordinatorDAO = mock(CoopCordinatorDAO.class);
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
			Mockito.when(coopCordinatorDAO.fetchRecruiter(101)).thenReturn(mockRequest1);
			Mockito.when(coopCordinatorDAO.approveRequest(101, "mock@dal.ca", "TESTPWPD")).thenReturn(1);
			Mockito.when(coopCordinatorDAO.fetchRecruiterRequests()).thenReturn(requests);

			// Class object creation
			CoopCoordinatorModel cm=new CoopCoordinatorModel();
			Model returnedModel = cm.approveRecruiterRequest(mockModel, req, 101, coopCordinatorDAO, approvalEmail, passwordGenerator);
				
			//Fetch returned values
			String isValid= (String)returnedModel.asMap().get("isValid");
			List<RecruiterRequest> returnedRequests = (List<RecruiterRequest>)returnedModel.asMap().get("recruiterRequests");
				
			//Assertion
			Assert.assertEquals(isValid, "approve");
			Assert.assertEquals(returnedRequests.get(0).getId(),"102");
		}
		catch (Exception e)
		{
			logger.debug("Error during execution of CoopCoordinatorModelTest: testApproveRecruiterRequest()");
		}		
	}

	@Test
	public void testRejectRecruiterRequest() {
		//Creating mock objects
		HttpSession mockSession = mock(HttpSession.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		Model mockModel =  new ExtendedModelMap();
		CoopCordinatorDAO coopCordinatorDAO = mock(CoopCordinatorDAO.class);
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
		
			Mockito.when(coopCordinatorDAO.fetchRecruiter(101)).thenReturn(mockRequest1);
			Mockito.when(coopCordinatorDAO.rejectRequest(101)).thenReturn(1);
			Mockito.when(coopCordinatorDAO.fetchRecruiterRequests()).thenReturn(requests);
		
			// Class object creation
			CoopCoordinatorModel cm=new CoopCoordinatorModel();
			Model returnedModel = cm.rejectRecruiterRequest(mockModel, req, 101, coopCordinatorDAO, rejectEmail);
		
			//Fetch returned values
			String isValid= (String)returnedModel.asMap().get("isValid");
			List<RecruiterRequest> returnedRequests = (List<RecruiterRequest>)returnedModel.asMap().get("recruiterRequests");
						
			//Assertion
			Assert.assertEquals(isValid, "reject");
			Assert.assertEquals(returnedRequests.get(0).getId(),"102");
		}
		catch (Exception e)
		{
			logger.debug("Error during execution of CoopCoordinatorModelTest: testRejectRecruiterRequest()");
		}		
	}

	@Test 
    public void testFetchActiveRecruiters ()
    {
        int expectedActiveRecruiter = 3;
        CoopCoordinatorModel coopCoordinatorModel = new CoopCoordinatorModel(coopCordinatorDAO);
        List <RecruiterRequest> fetchedActiveRecruiter = coopCoordinatorModel.fetchActiveRecruiters();
        assertEquals(expectedActiveRecruiter,fetchedActiveRecruiter.size());

	}
	@Test
	public void testDeleteActiveRecruiter()
	{
		CoopCoordinatorModel coopCoordinatorModel = new CoopCoordinatorModel(coopCordinatorDAO);
		Boolean isRecruiterDeleted = coopCoordinatorModel.deleteActiveRecruiter(3);
		Assert.assertEquals("Test for checking whether the recruiter got deleted", isRecruiterDeleted, true);

	}
	@Test
	public void testDeleteEmployerCount()
	{
		int expectedRecruiterAfterDelete = 2;
		CoopCoordinatorModel coopCoordinatorModel = new CoopCoordinatorModel(coopCordinatorDAO);
		coopCoordinatorModel.deleteActiveRecruiter(3);
		List <RecruiterRequest> fetchedActiveRecruiter = coopCoordinatorModel.fetchActiveRecruiters();
		int updatedRecruiterCount = fetchedActiveRecruiter.size();
		assertEquals(expectedRecruiterAfterDelete, updatedRecruiterCount);
	}

}
