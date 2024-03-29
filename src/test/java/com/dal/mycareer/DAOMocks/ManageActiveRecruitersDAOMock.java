package com.dal.mycareer.DAOMocks;

import java.util.ArrayList;
import java.util.List;

import com.dal.mycareer.DAO.Interface.IManageActiveRecruitersDAO;
import com.dal.mycareer.DTO.RecruiterRequest;

public class ManageActiveRecruitersDAOMock implements IManageActiveRecruitersDAO
{
    ArrayList<RecruiterRequest> lstRecruiters;
    public ManageActiveRecruitersDAOMock ()
    {
        lstRecruiters = new ArrayList<RecruiterRequest>();
        RecruiterRequest recruiter1 = new RecruiterRequest();
        recruiter1.setId("1");
        recruiter1.setFirstname("Henry");
        recruiter1.setLastname("Ford");
        recruiter1.setEmail("henry@ford.com");
        recruiter1.setCompanyname("Ford Corp");
        recruiter1.setDesignation("CEO");
        lstRecruiters.add(recruiter1);
        RecruiterRequest recruiter2 = new RecruiterRequest();
        recruiter2.setId("2");
        recruiter2.setFirstname("Steve");
        recruiter2.setLastname("Jobs");
        recruiter2.setEmail("steve@apple.com");
        recruiter2.setCompanyname("Apple");
        recruiter2.setDesignation("CEO");
        lstRecruiters.add(recruiter2);
        RecruiterRequest recruiter3 = new RecruiterRequest();
        recruiter3.setId("3");
        recruiter3.setFirstname("Bill");
        recruiter3.setLastname("Gates");
        recruiter3.setEmail("BillGates@microsoft.com");
        recruiter3.setCompanyname("Microsoft");
        recruiter3.setDesignation("CEO");
        lstRecruiters.add(recruiter3);
    }
 

    @Override
    public boolean deleteActiveRecruiter(int employerId) 
    {
        return lstRecruiters.removeIf(recruiter -> recruiter.getId().equals(Integer.toString(employerId)));
    }

    @Override
    public List<RecruiterRequest> fetchActiveRecruiters() 
    {
        return lstRecruiters;
    }
}