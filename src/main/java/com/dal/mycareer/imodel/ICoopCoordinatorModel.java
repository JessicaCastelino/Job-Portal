package com.dal.mycareer.imodel;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface ICoopCoordinatorModel 
{
	public Model fetchRecruiterRequests(Model model, HttpServletRequest request);
	public Model approveRecruiterRequest(Model model, HttpServletRequest request, int recruiterRequestId);
	public Model rejectRecruiterRequest(Model model, HttpServletRequest request, int recruiterRequestId);
}
