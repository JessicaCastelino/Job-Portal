package com.dal.mycareer.controller;

import com.dal.mycareer.DAO.Impl.JobsDAO;
import com.dal.mycareer.DAO.Interface.IJobsDAO;
import com.dal.mycareer.imodel.IJobsModel;
import com.dal.mycareer.imodel.IRoleModel;
import com.dal.mycareer.model.JobsModel;
import com.dal.mycareer.model.RoleModel;
import com.dal.mycareer.propertiesparser.PropertiesParser;

import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JobsController
{
	@Autowired
    IJobsModel jobsManager;
	private static final Properties PROPERTY_MAP = PropertiesParser.getPropertyMap();
	private final Logger logger;
	private IRoleModel roleModel;
	private IJobsModel jobsModel;
	private IJobsDAO jobsDao;
	
    public JobsController()
	{
    	logger = LoggerFactory.getLogger(this.getClass());
    	roleModel = new RoleModel();
    	jobsModel = new JobsModel();
    	jobsDao = new JobsDAO();
	}

	

    @ResponseBody
	@RequestMapping( value="/closeJob", method=RequestMethod.PUT)
	public boolean closeJob(@RequestParam(name = "id") int jobRecordId )
	{
		return jobsManager.updateJobStatus(jobRecordId, false);
    }
    
    @ResponseBody
	@RequestMapping( value="/openJob", method=RequestMethod.PUT)
	public boolean openJob(@RequestParam(name = "id") int jobRecordId )
	{
		return jobsManager.updateJobStatus(jobRecordId, true);
	}
    
    @RequestMapping(value = { "/viewJob" }, method = RequestMethod.GET)
	public String viewJob(@RequestParam("id") int jobId, Model model, HttpServletRequest request) throws SQLException {
		logger.debug("JobsController: viewJob method: Entered");
		model.addAttribute("reqPage", PROPERTY_MAP.get("viewJob").toString());
		model.addAttribute("role", "student");
		roleModel = new RoleModel();
		model = roleModel.getBasePage(model, request);
		jobsModel = new JobsModel();
		model = jobsModel.fetchJob(model, jobId, request, jobsDao);
		logger.debug("JobsController: viewJob method: Exit");
		return model.asMap().get("view").toString();

	}
}