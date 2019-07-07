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
import com.dal.mycareer.imodel.ICoopCoordinatorModel;
import org.springframework.beans.factory.annotation.Autowired;
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
		requests = dao.fetchRecruiterRequests();
		model.addAttribute("recruiterRequests", requests);
		}
		return model;
	}

	 @Override
	 public List<RecruiterRequest> fetchActiveRecruiters()
	 {
		dao = new CoopCordinatorDAO(); 
		return dao.fetchActiveRecruiters();
	 }

	 @Override
		public Model approveRecruiterRequest(Model model, HttpServletRequest request, int recruiterRequestId, String email) {
			HttpSession session = request.getSession();
			String userSessionName = (String) session.getAttribute(SESSION_NAME);
			System.out.println("USERNAME :"+userSessionName);
			if(userSessionName!="" && userSessionName!=null)
			{
			dao = new CoopCordinatorDAO();
			int i=dao.approveRequest(recruiterRequestId, email, "HAHAHA");
			requests=dao.fetchRecruiterRequests();
			model.addAttribute("recruiterRequests", requests);
			}
			return model;
		}
	 @Override
		public Model rejectRecruiterRequest(Model model, HttpServletRequest request, int recruiterRequestId) {
			HttpSession session = request.getSession();
			String userSessionName = (String) session.getAttribute(SESSION_NAME);
			System.out.println("USERNAME :"+userSessionName);
			if(userSessionName!="" && userSessionName!=null)
			{
			dao = new CoopCordinatorDAO();
			int i=dao.rejectRequest(recruiterRequestId);
			requests=dao.fetchRecruiterRequests();
			model.addAttribute("recruiterRequests", requests);
			}
			return model;
		}
	 @Override
	 public boolean deleteActiveRecruiter(int employerId)
	 {
		dao = new CoopCordinatorDAO(); 
		return dao.deleteActiveRecruiter(employerId);
	 }
}
