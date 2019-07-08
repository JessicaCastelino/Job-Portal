package com.dal.mycareer.imodel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.dal.mycareer.DTO.RecruiterRequest;

public interface ICoopCoordinatorModel 
{
	public Model fetchRecruiterRequests(Model model, HttpServletRequest request);
	public Model approveRecruiterRequest(Model model, HttpServletRequest request, int recruiterRequestId);
	public Model rejectRecruiterRequest(Model model, HttpServletRequest request, int recruiterRequestId);
	public boolean deleteActiveRecruiter(int employerId);
	public List<RecruiterRequest> fetchActiveRecruiters();
	
}
