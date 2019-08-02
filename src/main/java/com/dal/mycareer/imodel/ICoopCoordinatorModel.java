package com.dal.mycareer.imodel;

import java.util.List;

import com.dal.mycareer.DTO.RecruiterRequest;

public interface ICoopCoordinatorModel 
{
	public boolean deleteActiveRecruiter(int employerId);
	public List<RecruiterRequest> fetchActiveRecruiters();
	
}
