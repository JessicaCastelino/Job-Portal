package com.dal.mycareer.controller;
import java.util.List;
import static org.junit.Assert.assertEquals;
import com.dal.mycareer.DAOMocks.CoopCordinatorDAOMock;
import com.dal.mycareer.DAO.Interface.ICoopCordinatorDAO;
import com.dal.mycareer.DTO.RecruiterRequest;
import com.dal.mycareer.model.CoopCoordinatorModel;

import org.junit.Before;
import org.junit.Test;

public class CoopCoordinatorModelTest
{
    ICoopCordinatorDAO coopCordinatorDAO;
    @Before
    public void setUp()
    {
        coopCordinatorDAO = new CoopCordinatorDAOMock();
    }
    @Test 
    public void fetchActiveRecruiters ()
    {
        int expectedActiveRecruiter =2;
        CoopCoordinatorModel coopCoordinatorModel = new CoopCoordinatorModel(coopCordinatorDAO);
        List <RecruiterRequest> fetchedActiveRecruiter = coopCoordinatorModel.fetchActiveRecruiters();
        assertEquals(expectedActiveRecruiter,fetchedActiveRecruiter.size());

    }


}