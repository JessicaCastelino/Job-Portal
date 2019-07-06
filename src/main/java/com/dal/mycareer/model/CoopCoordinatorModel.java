package com.dal.mycareer.model;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.dal.mycareer.DAO.Impl.CoopCordinatorDAO;
import com.dal.mycareer.DAO.Interface.ICoopCordinatorDAO;
import com.dal.mycareer.DTO.RecruiterRequest;
import com.dal.mycareer.imodel.ICoopCoordinatorModel;

public class CoopCoordinatorModel implements ICoopCoordinatorModel{
	private static List<RecruiterRequest> requests = new ArrayList<RecruiterRequest>();
	private static final String SESSION_NAME = "sessionName";
	static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	ICoopCordinatorDAO dao = null;

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

}
