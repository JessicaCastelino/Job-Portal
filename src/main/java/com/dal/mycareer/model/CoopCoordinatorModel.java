package com.dal.mycareer.model;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.stereotype.Service;
import com.dal.mycareer.DAO.Impl.CoopCordinatorDAO;
import com.dal.mycareer.DAO.Interface.ICoopCordinatorDAO;
import com.dal.mycareer.DTO.RecruiterRequest;
import com.dal.mycareer.emailengine.EmployerApprovalEmail;
import com.dal.mycareer.emailengine.EmployerRejectionEmailImpl;
import com.dal.mycareer.emailengine.IEmployerApprovalEmail;
import com.dal.mycareer.emailengine.IEmployerRejectionEmail;
import com.dal.mycareer.imodel.ICoopCoordinatorModel;

@Service
public class CoopCoordinatorModel implements ICoopCoordinatorModel
{
	
	ICoopCordinatorDAO dao = null;
	
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
		dao = new CoopCordinatorDAO();
		model.addAttribute("isValid", "NA");
		requests = dao.fetchRecruiterRequests();
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
			dao = new CoopCordinatorDAO();
			String password="HAHAHA";
			RecruiterRequest recruiter=dao.fetchRecruiter(recruiterRequestId);
			int i=dao.approveRequest(recruiterRequestId, recruiter.getEmail(), password);
			approvalEmail.employerApprovalEmail(recruiter.getEmail(), recruiter.getFirstname()+" "+recruiter.getLastname(), recruiter.getCompanyname(), password, recruiter.getEmail());
			requests=dao.fetchRecruiterRequests();
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
			dao = new CoopCordinatorDAO();
			RecruiterRequest recruiter=dao.fetchRecruiter(recruiterRequestId);
			int i=dao.rejectRequest(recruiterRequestId);
			rejectEmail.employerRejectionEmail(recruiter.getEmail(), recruiter.getFirstname()+" "+recruiter.getLastname(), recruiter.getCompanyname());
			requests=dao.fetchRecruiterRequests();
			model.addAttribute("isValid", "reject");
			model.addAttribute("recruiterRequests", requests);
			}
			return model;
		}
}
