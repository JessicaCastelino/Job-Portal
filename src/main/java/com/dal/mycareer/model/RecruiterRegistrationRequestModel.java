package com.dal.mycareer.model;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.dal.mycareer.DAO.Interface.IRecruiterRegistrationRequestDAO;
import com.dal.mycareer.DTO.RecruiterRequest;
import com.dal.mycareer.emailengine.IEmployerApprovalEmail;
import com.dal.mycareer.emailengine.IEmployerRejectionEmail;
import com.dal.mycareer.imodel.IRecruiterRegistrationRequestModel;
import com.dal.mycareer.passwordgenerator.IPasswordGenerator;

public class RecruiterRegistrationRequestModel implements IRecruiterRegistrationRequestModel
{
	@Autowired
	private static List<RecruiterRequest> requests;
	private static final String SESSION_NAME = "sessionName";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Model fetchRecruiterRequests(Model model, HttpServletRequest request, IRecruiterRegistrationRequestDAO recruiterRequestDao)
	        throws SQLException
	{
		logger.debug("CoopCoordinatorModel: fetchRecruiterRequests method: Entered");
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		if (userSessionName != "" && userSessionName != null) 
		{
			model.addAttribute("isValid", "NA");
			requests = recruiterRequestDao.fetchAllRecruiterRequests();	
			model.addAttribute("recruiterRequests", requests);
		}
		logger.debug("CoopCoordinatorModel: fetchRecruiterRequests method: Exit");
		return model;
	}

	@Override
	public Model approveRecruiterRequest(Model model, HttpServletRequest request, int recruiterRequestId,
			IRecruiterRegistrationRequestDAO recruiterRequestDao, IEmployerApprovalEmail approvalEmail,
	        IPasswordGenerator passwordGenerator) throws SQLException
	{
		logger.debug("CoopCoordinatorModel: approveRecruiterRequest method: Entered");
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		
		if (userSessionName != "" && userSessionName != null) 
		{
			String password = passwordGenerator.generatePassword();
			RecruiterRequest recruiter = recruiterRequestDao.fetchRecruiterRequest(recruiterRequestId);
			int i=recruiterRequestDao.approveRequest(recruiterRequestId, recruiter.getEmail(), password);
			approvalEmail.employerApprovalEmail(recruiter.getEmail(),
					recruiter.getFirstname() + " " + recruiter.getLastname(), recruiter.getCompanyname(), password,
					recruiter.getEmail());
			requests = recruiterRequestDao.fetchAllRecruiterRequests();
			model.addAttribute("isValid", "approve");
			model.addAttribute("recruiterRequests", requests);
		}
		logger.debug("CoopCoordinatorModel: approveRecruiterRequest method: Exit");
		return model;
	}

	@Override
	public Model rejectRecruiterRequest(Model model, HttpServletRequest request, int recruiterRequestId,
			IRecruiterRegistrationRequestDAO recruiterRequestDao, IEmployerRejectionEmail rejectEmail) throws SQLException
	{
		logger.debug("CoopCoordinatorModel: rejectRecruiterRequest method: Entered");
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		if (userSessionName != "" && userSessionName != null) 
		{
			RecruiterRequest recruiter = recruiterRequestDao.fetchRecruiterRequest(recruiterRequestId);
			int i=recruiterRequestDao.rejectRequest(recruiterRequestId);
			rejectEmail.employerRejectionEmail(recruiter.getEmail(),
					recruiter.getFirstname() + " " + recruiter.getLastname(), recruiter.getCompanyname());
			requests = recruiterRequestDao.fetchAllRecruiterRequests();
			model.addAttribute("isValid", "reject");
			model.addAttribute("recruiterRequests", requests);
		}
		logger.debug("CoopCoordinatorModel: rejectRecruiterRequest method: Exit");
		return model;
	}

}
