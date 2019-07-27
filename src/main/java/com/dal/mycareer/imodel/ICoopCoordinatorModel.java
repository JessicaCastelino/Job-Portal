package com.dal.mycareer.imodel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.dal.mycareer.DAO.Interface.ICoopCordinatorDAO;
import com.dal.mycareer.DTO.RecruiterRequest;
import com.dal.mycareer.emailengine.IEmployerApprovalEmail;
import com.dal.mycareer.emailengine.IEmployerRejectionEmail;
import com.dal.mycareer.passwordgenerator.IPasswordGenerator;

public interface ICoopCoordinatorModel 
{
	public Model fetchRecruiterRequests(Model model, HttpServletRequest request, ICoopCordinatorDAO coopCordinatorDAO);
	public Model approveRecruiterRequest(Model model, HttpServletRequest request, int recruiterRequestId, ICoopCordinatorDAO coopCordinatorDAO, IEmployerApprovalEmail approvalEmail, IPasswordGenerator passwordGenerator);
	public Model rejectRecruiterRequest(Model model, HttpServletRequest request, int recruiterRequestId, ICoopCordinatorDAO coopCordinatorDAO, IEmployerRejectionEmail rejectEmail);
	public boolean deleteActiveRecruiter(int employerId);
	public List<RecruiterRequest> fetchActiveRecruiters();
	
}
