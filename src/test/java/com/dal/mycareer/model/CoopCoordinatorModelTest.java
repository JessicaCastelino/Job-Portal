package com.dal.mycareer.model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dal.mycareer.DAO.Interface.ICoopCordinatorDAO;
import com.dal.mycareer.DAOMocks.CoopCordinatorDAOMock;
import com.dal.mycareer.DTO.RecruiterRequest;

public class CoopCoordinatorModelTest {
	ICoopCordinatorDAO coopCordinatorDAO;
    @Before
    public void setUp()
    {
        coopCordinatorDAO = new CoopCordinatorDAOMock();
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
