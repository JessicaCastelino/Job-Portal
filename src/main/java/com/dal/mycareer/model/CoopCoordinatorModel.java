package com.dal.mycareer.model;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dal.mycareer.DAO.Interface.ICoopCordinatorDAO;
import com.dal.mycareer.DTO.RecruiterRequest;
import com.dal.mycareer.emailengine.EmployerApprovalEmail;
import com.dal.mycareer.emailengine.EmployerRejectionEmailImpl;
import com.dal.mycareer.emailengine.IEmployerApprovalEmail;
import com.dal.mycareer.emailengine.IEmployerRejectionEmail;
import com.dal.mycareer.imodel.ICoopCoordinatorModel;
import com.dal.mycareer.passwordgenerator.IPasswordGenerator;
import com.dal.mycareer.passwordgenerator.PasswordGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class CoopCoordinatorModel implements ICoopCoordinatorModel 
{
	@Autowired
	private ICoopCordinatorDAO coopCordinatorDAO;

	private static List<RecruiterRequest> requests = new ArrayList<RecruiterRequest>();
	private static final String SESSION_NAME = "sessionName";
	static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public CoopCoordinatorModel() 
	{

	}

	public CoopCoordinatorModel(ICoopCordinatorDAO coopCordinatorDAO) 
	{
		this.coopCordinatorDAO = coopCordinatorDAO;
	}

	@Override
	public Model fetchRecruiterRequests(Model model, HttpServletRequest request) 
	{
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		System.out.println("USERNAME :" + userSessionName);
		if (userSessionName != "" && userSessionName != null) 
		{
			model.addAttribute("isValid", "NA");
			requests = coopCordinatorDAO.fetchRecruiterRequests();
			model.addAttribute("recruiterRequests", requests);
		}
		return model;
	}

	@Override
	public Model approveRecruiterRequest(Model model, HttpServletRequest request, int recruiterRequestId) 
	{
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		System.out.println("USERNAME :" + userSessionName);
		IEmployerApprovalEmail approvalEmail = new EmployerApprovalEmail();
		if (userSessionName != "" && userSessionName != null) 
		{
			IPasswordGenerator passwordGenerator = new PasswordGenerator();
			String password = passwordGenerator.generatePassword();
			RecruiterRequest recruiter = coopCordinatorDAO.fetchRecruiter(recruiterRequestId);
			coopCordinatorDAO.approveRequest(recruiterRequestId, recruiter.getEmail(), password);
			approvalEmail.employerApprovalEmail(recruiter.getEmail(),
					recruiter.getFirstname() + " " + recruiter.getLastname(), recruiter.getCompanyname(), password,
					recruiter.getEmail());
			requests = coopCordinatorDAO.fetchRecruiterRequests();
			model.addAttribute("isValid", "approve");
			model.addAttribute("recruiterRequests", requests);
		}
		return model;
	}

	@Override
	public Model rejectRecruiterRequest(Model model, HttpServletRequest request, int recruiterRequestId) 
	{
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		System.out.println("USERNAME :" + userSessionName);
		IEmployerRejectionEmail rejectEmail = new EmployerRejectionEmailImpl();
		if (userSessionName != "" && userSessionName != null) 
		{
			RecruiterRequest recruiter = coopCordinatorDAO.fetchRecruiter(recruiterRequestId);
			coopCordinatorDAO.rejectRequest(recruiterRequestId);
			rejectEmail.employerRejectionEmail(recruiter.getEmail(),
					recruiter.getFirstname() + " " + recruiter.getLastname(), recruiter.getCompanyname());
			requests = coopCordinatorDAO.fetchRecruiterRequests();
			model.addAttribute("isValid", "reject");
			model.addAttribute("recruiterRequests", requests);
		}
		return model;
	}

	@Override
	public boolean deleteActiveRecruiter(int employerId) 
	{
		logger.info("BL: deleteActiveRecruiter method started for-" + employerId);
		return coopCordinatorDAO.deleteActiveRecruiter(employerId);
	}

	@Override
	public List<RecruiterRequest> fetchActiveRecruiters() 
	{
		logger.info("BL: fetchActiveRecruiters method started");
		return coopCordinatorDAO.fetchActiveRecruiters();
	}
}
