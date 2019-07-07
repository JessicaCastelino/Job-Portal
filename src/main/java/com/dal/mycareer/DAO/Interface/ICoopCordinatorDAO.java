package com.dal.mycareer.DAO.Interface;

import java.util.List;

import com.dal.mycareer.DTO.RecruiterRequest;

public interface ICoopCordinatorDAO 
{
	public List<RecruiterRequest> fetchRecruiterRequests();
	public List<RecruiterRequest> fetchActiveRecruiters();
	int approveRequest(int requestId, String username, String password);
	int rejectRequest(int requestId);
}
