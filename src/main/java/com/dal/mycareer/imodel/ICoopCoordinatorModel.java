package com.dal.mycareer.imodel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.dal.mycareer.DTO.RecruiterRequest;

public interface ICoopCoordinatorModel 
{
	public Model fetchRecruiterRequests(Model model, HttpServletRequest request);
	public List<RecruiterRequest> fetchActiveRecruiters();
	public Model approveRecruiterRequest(Model model, HttpServletRequest request, int recruiterRequestId, String email);
	public Model rejectRecruiterRequest(Model model, HttpServletRequest request, int recruiterRequestId);
}
