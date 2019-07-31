package com.dal.mycareer.DAO.Interface;

import java.util.List;

import com.dal.mycareer.DTO.RecruiterRequest;

public interface ICoopCordinatorDAO 
{
	public List<RecruiterRequest> fetchActiveRecruiters();
	public boolean deleteActiveRecruiter(int employerId);
}
