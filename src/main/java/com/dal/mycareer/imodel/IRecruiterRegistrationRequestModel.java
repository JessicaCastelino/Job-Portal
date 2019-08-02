package com.dal.mycareer.imodel;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;


import com.dal.mycareer.DAO.Interface.IRecruiterRegistrationRequestDAO;
import com.dal.mycareer.emailengine.IEmployerApprovalEmail;
import com.dal.mycareer.emailengine.IEmployerRejectionEmail;
import com.dal.mycareer.passwordgenerator.IPasswordGenerator;

public interface IRecruiterRegistrationRequestModel
{
	public Model fetchRecruiterRequests(Model model, HttpServletRequest request, IRecruiterRegistrationRequestDAO recruiterRequestDao) throws SQLException;
	public Model approveRecruiterRequest(Model model, HttpServletRequest request, int recruiterRequestId, IRecruiterRegistrationRequestDAO recruiterRequestDao, IEmployerApprovalEmail approvalEmail, IPasswordGenerator passwordGenerator) throws SQLException;
	public Model rejectRecruiterRequest(Model model, HttpServletRequest request, int recruiterRequestId, IRecruiterRegistrationRequestDAO recruiterRequestDao, IEmployerRejectionEmail rejectEmail) throws SQLException;
}
