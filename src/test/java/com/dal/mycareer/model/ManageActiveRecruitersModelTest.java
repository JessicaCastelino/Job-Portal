package com.dal.mycareer.model;

import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.assertEquals;
import com.dal.mycareer.DAO.Interface.IManageActiveRecruitersDAO;

import com.dal.mycareer.DAOMocks.ManageActiveRecruitersDAOMock;
import com.dal.mycareer.DTO.RecruiterRequest;
import com.dal.mycareer.imodel.IManageActiveRecruitersModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ManageActiveRecruitersModelTest
{
    private IManageActiveRecruitersDAO manageActiveRecruitersDAO;
    private IManageActiveRecruitersModel manageActiveRecruitersModel;
    @Before
    public void setUp()
    {
        manageActiveRecruitersDAO = new ManageActiveRecruitersDAOMock();
        manageActiveRecruitersModel = new ManageActiveRecruitersModel(manageActiveRecruitersDAO);
    }
    @Test 
    public void testFetchActiveRecruiters () throws SQLException
    {
        int expectedActiveRecruiter = 3;
        List <RecruiterRequest> fetchedActiveRecruiter = manageActiveRecruitersModel.fetchActiveRecruiters();
        assertEquals(expectedActiveRecruiter,fetchedActiveRecruiter.size());

	}
	@Test
	public void testDeleteActiveRecruiter() throws SQLException
	{
		Boolean isRecruiterDeleted = manageActiveRecruitersModel.deleteActiveRecruiter(3);
		Assert.assertEquals("Test for checking whether the recruiter got deleted", isRecruiterDeleted, true);

	}
	@Test
	public void testDeleteEmployerCount() throws SQLException
	{
		int expectedRecruiterAfterDelete = 2;
		manageActiveRecruitersModel.deleteActiveRecruiter(3);
		List <RecruiterRequest> fetchedActiveRecruiter = manageActiveRecruitersModel.fetchActiveRecruiters();
		int updatedRecruiterCount = fetchedActiveRecruiter.size();
		assertEquals(expectedRecruiterAfterDelete, updatedRecruiterCount);
	}
}