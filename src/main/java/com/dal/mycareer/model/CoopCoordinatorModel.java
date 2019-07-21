package com.dal.mycareer.model;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dal.mycareer.DAO.Impl.CoopCordinatorDAO;
import com.dal.mycareer.DAO.Interface.ICoopCordinatorDAO;
import com.dal.mycareer.DTO.RecruiterRequest;
import com.dal.mycareer.emailengine.EmployerApprovalEmail;
import com.dal.mycareer.emailengine.EmployerRejectionEmailImpl;
import com.dal.mycareer.emailengine.IEmployerApprovalEmail;
import com.dal.mycareer.emailengine.IEmployerRejectionEmail;
import com.dal.mycareer.imodel.ICoopCoordinatorModel;
import com.dal.mycareer.passwordgenerator.IPasswordGenerator;
import com.dal.mycareer.passwordgenerator.PasswordGenerator;

@Service
public class CoopCoordinatorModel implements ICoopCoordinatorModel
{
	@Autowired
	ICoopCordinatorDAO coopCordinatorDAO;

	public CoopCoordinatorModel ()
	{

	}
	public CoopCoordinatorModel (ICoopCordinatorDAO coopCordinatorDAO)
	{
		this.coopCordinatorDAO = coopCordinatorDAO;
	}
	private static List<RecruiterRequest> requests = new ArrayList<RecruiterRequest>();
	private static final String SESSION_NAME = "sessionName";
	static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	

	@Override
	public Model fetchRecruiterRequests(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		System.out.println("USERNAME :"+userSessionName);
		if(userSessionName!="" && userSessionName!=null)
		{
		//dao = new CoopCordinatorDAO();
		model.addAttribute("isValid", "NA");
		requests = coopCordinatorDAO.fetchRecruiterRequests();
		model.addAttribute("recruiterRequests", requests);
		}
		return model;
	}


	 @Override
		public Model approveRecruiterRequest(Model model, HttpServletRequest request, int recruiterRequestId) {
			HttpSession session = request.getSession();
			String userSessionName = (String) session.getAttribute(SESSION_NAME);
			System.out.println("USERNAME :"+userSessionName);
			IEmployerApprovalEmail approvalEmail=new EmployerApprovalEmail();
			if(userSessionName!="" && userSessionName!=null)
			{
			//dao = new CoopCordinatorDAO();
			IPasswordGenerator passwordGenerator = new PasswordGenerator();
			String password= passwordGenerator.generatePassword();
			RecruiterRequest recruiter=coopCordinatorDAO.fetchRecruiter(recruiterRequestId);
			int i=coopCordinatorDAO.approveRequest(recruiterRequestId, recruiter.getEmail(), password);
			approvalEmail.employerApprovalEmail(recruiter.getEmail(), recruiter.getFirstname()+" "+recruiter.getLastname(), recruiter.getCompanyname(), password, recruiter.getEmail());
			requests=coopCordinatorDAO.fetchRecruiterRequests();
			model.addAttribute("isValid", "approve");
			model.addAttribute("recruiterRequests", requests);
			}
			return model;
		}
	 @Override
		public Model rejectRecruiterRequest(Model model, HttpServletRequest request, int recruiterRequestId) {
			HttpSession session = request.getSession();
			String userSessionName = (String) session.getAttribute(SESSION_NAME);
			System.out.println("USERNAME :"+userSessionName);
			IEmployerRejectionEmail rejectEmail=new EmployerRejectionEmailImpl();
			if(userSessionName!="" && userSessionName!=null)
			{
			//dao = new CoopCordinatorDAO();
			RecruiterRequest recruiter=coopCordinatorDAO.fetchRecruiter(recruiterRequestId);
			int i=coopCordinatorDAO.rejectRequest(recruiterRequestId);
			rejectEmail.employerRejectionEmail(recruiter.getEmail(), recruiter.getFirstname()+" "+recruiter.getLastname(), recruiter.getCompanyname());
			requests=coopCordinatorDAO.fetchRecruiterRequests();
			model.addAttribute("isValid", "reject");
			model.addAttribute("recruiterRequests", requests);
			}
			return model;
		}
	 @Override
	 public boolean deleteActiveRecruiter(int employerId)
	 {
		//dao = new CoopCordinatorDAO(); 
		return coopCordinatorDAO.deleteActiveRecruiter(employerId);
	 }


	@Override
	public List<RecruiterRequest> fetchActiveRecruiters() 
	{
		//dao = new CoopCordinatorDAO(); 
		return coopCordinatorDAO.fetchActiveRecruiters();
	}
}
