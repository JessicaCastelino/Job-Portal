package com.dal.mycareer.model;

import java.lang.invoke.MethodHandles;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.dal.mycareer.DAO.Interface.ICoopCordinatorDAO;
import com.dal.mycareer.DTO.RecruiterRequest;
import com.dal.mycareer.emailengine.IEmployerApprovalEmail;
import com.dal.mycareer.emailengine.IEmployerRejectionEmail;
import com.dal.mycareer.imodel.ICoopCoordinatorModel;
import com.dal.mycareer.passwordgenerator.IPasswordGenerator;

@Service
public class CoopCoordinatorModel implements ICoopCoordinatorModel 
{
	@Autowired
	private ICoopCordinatorDAO coopCordinatorDAO;
	@Autowired
	private static List<RecruiterRequest> requests;
	private static final String SESSION_NAME = "sessionName";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public CoopCoordinatorModel() 
	{
	}

	public CoopCoordinatorModel(ICoopCordinatorDAO coopCordinatorDAO) 
	{
		this.coopCordinatorDAO = coopCordinatorDAO;
	}

	@Override
	public Model fetchRecruiterRequests(Model model, HttpServletRequest request, ICoopCordinatorDAO coopCordinatorDAO) throws SQLException 
	{
		logger.debug("CoopCoordinatorModel: fetchRecruiterRequests method: Entered");
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		if (userSessionName != "" && userSessionName != null) 
		{
			model.addAttribute("isValid", "NA");
			requests = coopCordinatorDAO.fetchRecruiterRequests();	
			model.addAttribute("recruiterRequests", requests);
		}
		logger.debug("CoopCoordinatorModel: fetchRecruiterRequests method: Exit");
		return model;
	}

	@Override
	public Model approveRecruiterRequest(Model model, HttpServletRequest request, int recruiterRequestId, ICoopCordinatorDAO coopCordinatorDAO, IEmployerApprovalEmail approvalEmail, IPasswordGenerator passwordGenerator) throws SQLException 
	{
		logger.debug("CoopCoordinatorModel: approveRecruiterRequest method: Entered");
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		
		if (userSessionName != "" && userSessionName != null) 
		{
			String password = passwordGenerator.generatePassword();
			RecruiterRequest recruiter = coopCordinatorDAO.fetchRecruiter(recruiterRequestId);
			int i=coopCordinatorDAO.approveRequest(recruiterRequestId, recruiter.getEmail(), password);
			approvalEmail.employerApprovalEmail(recruiter.getEmail(),
					recruiter.getFirstname() + " " + recruiter.getLastname(), recruiter.getCompanyname(), password,
					recruiter.getEmail());
			requests = coopCordinatorDAO.fetchRecruiterRequests();
			model.addAttribute("isValid", "approve");
			model.addAttribute("recruiterRequests", requests);
		}
		logger.debug("CoopCoordinatorModel: approveRecruiterRequest method: Exit");
		return model;
	}

	@Override
	public Model rejectRecruiterRequest(Model model, HttpServletRequest request, int recruiterRequestId, ICoopCordinatorDAO coopCordinatorDAO,IEmployerRejectionEmail rejectEmail) throws SQLException 
	{
		logger.debug("CoopCoordinatorModel: rejectRecruiterRequest method: Entered");
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		if (userSessionName != "" && userSessionName != null) 
		{
			RecruiterRequest recruiter = coopCordinatorDAO.fetchRecruiter(recruiterRequestId);
			int i=coopCordinatorDAO.rejectRequest(recruiterRequestId);
			rejectEmail.employerRejectionEmail(recruiter.getEmail(),
					recruiter.getFirstname() + " " + recruiter.getLastname(), recruiter.getCompanyname());
			requests = coopCordinatorDAO.fetchRecruiterRequests();
			model.addAttribute("isValid", "reject");
			model.addAttribute("recruiterRequests", requests);
		}
		logger.debug("CoopCoordinatorModel: rejectRecruiterRequest method: Exit");
		return model;
	}

	@Override
	public boolean deleteActiveRecruiter(int employerId) 
	{
		logger.debug("BL: deleteActiveRecruiter method started for-" + employerId);
		return coopCordinatorDAO.deleteActiveRecruiter(employerId);
	}

	@Override
	public List<RecruiterRequest> fetchActiveRecruiters() 
	{
		logger.debug("BL: fetchActiveRecruiters method started");
		return coopCordinatorDAO.fetchActiveRecruiters();
	}
}
