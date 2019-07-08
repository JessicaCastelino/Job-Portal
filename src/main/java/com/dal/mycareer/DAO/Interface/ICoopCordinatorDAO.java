package com.dal.mycareer.DAO.Interface;

import java.util.List;

import com.dal.mycareer.DTO.RecruiterRequest;

public interface ICoopCordinatorDAO 
{
	public List<RecruiterRequest> fetchRecruiterRequests();
	public List<RecruiterRequest> fetchActiveRecruiters();
	public int approveRequest(int requestId, String username, String password);
	public int rejectRequest(int requestId);
	public RecruiterRequest fetchRecruiter(int reqID);
	public boolean deleteActiveRecruiter(int employerId);
}
