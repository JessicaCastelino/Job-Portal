package com.dal.mycareer.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.dal.mycareer.DAO.Interface.IStudentApplicationDAO;
import com.dal.mycareer.DAO.Interface.IStudentDetailsDAO;
import com.dal.mycareer.DAO.Interface.IStudentJobsDAO;
import com.dal.mycareer.DTO.AppliedJob;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.DTO.Student;
import com.dal.mycareer.imodel.IStudentApplicationModel;
import com.dal.mycareer.propertiesparser.PropertiesParser;

public class StudentApplicationModel implements IStudentApplicationModel
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private Student student = null;
	private static List<AppliedJob> appliedJobs = new ArrayList<AppliedJob>();
	private static final String SESSION_NAME = "sessionName";
	
	@Override
	public Model submitJobApplication(Model model, MultipartFile file, HttpServletRequest request, int jobId,
			IStudentDetailsDAO studentDao, IStudentApplicationDAO studentApplicationDao, IStudentJobsDAO studentJobDao) throws SQLException, IOException
	{
		logger.debug("StudentApplicationModel: submitJobApplication method: Entered");
		int submitted=0;
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		if(userSessionName!="" && userSessionName!=null)
		{
		student = studentDao.getStudentDetails(userSessionName);
		if (file != null) {
				submitted = studentApplicationDao.submitJobApplication(file,student.getId(),jobId);
				appliedJobs = studentJobDao.getAppliedJobList(student.getId());
				model.addAttribute("appliedJobs", appliedJobs);
				if (submitted == 1) {
					logger.debug("Job application successful.");
				}
			}
		}
		logger.debug("StudentApplicationModel: submitJobApplication method: Exit");
		return model;
	}

	@Override
	public Model withdrawJobApplication(Model model, int jobId, HttpServletRequest request, IStudentDetailsDAO studentDao, IStudentApplicationDAO studentApplicationDao, IStudentJobsDAO studentJobDao)
	        throws SQLException
	{
		logger.debug("StudentApplicationModel: withdrawJobApplication method: Entered");
		int withdrawn=0;
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		if(userSessionName!="" && userSessionName!=null)
		{
		student = studentDao.getStudentDetails(userSessionName);
		withdrawn=studentApplicationDao.withdrawJobApplication(jobId);
		appliedJobs = studentJobDao.getAppliedJobList(student.getId());
		model.addAttribute("appliedJobs", appliedJobs);
		if (withdrawn == 1) {
			logger.debug("Job application successfully withdrawn.");
		}
		}
		logger.debug("StudentApplicationModel: withdrawJobApplication method: Exit");
		return model;
	}

	@Override
	public Model checkApplicationExists(Model model, HttpServletRequest request, int jobId, IStudentDetailsDAO studentDao, IStudentApplicationDAO studentApplicationDao)
	        throws SQLException
	{
		logger.debug("StudentApplicationModel: checkApplicationExists method: Entered");
		int exists=0;
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		if(userSessionName!="" && userSessionName!=null)
		{
			student = studentDao.getStudentDetails(userSessionName);
			exists=studentApplicationDao.checkApplicationExists(student.getId(),jobId);
			if(exists==1)
			{
				model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("alreadyApplied").toString());
			}
		}
		logger.debug("StudentApplicationModel: checkApplicationExists method: Exit");
		return model;
	}

}
