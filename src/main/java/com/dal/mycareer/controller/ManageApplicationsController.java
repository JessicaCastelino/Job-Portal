package com.dal.mycareer.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import com.dal.mycareer.DAO.Impl.ManageApplicationsDAO;
import com.dal.mycareer.imodel.IManageApplicationsModel;
import com.dal.mycareer.propertiesparser.PropertiesParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ManageApplicationsController {
	@Autowired
	IManageApplicationsModel applicationManager;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping("/applications")
	public String viewApplicants(ModelMap model, @RequestParam(name = "jobId")int id) {
        logger.info("Redirect to applications.jsp");
        model.addAttribute("jobId",id);
		model.addAttribute("applicants", applicationManager.getApplications(id));
		model.addAttribute("appStatus", PropertiesParser.getPropertyMap().get("applicationStatus").toString().split(","));
		return "applications";
	}

	@ResponseBody
	@RequestMapping(value = "/updateApplicationStatus", method = RequestMethod.PUT)
	public boolean updateApplicationStatus(ModelMap model, @RequestParam(name = "applicationId")int applicationId, @RequestParam(name = "appStatus") String appStatus) {
		return applicationManager.updateApplicationStatus(applicationId, appStatus);
	}
	
	
	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	public void downloadApplicationDocument(ModelMap model, @RequestParam int id, HttpServletResponse response) throws SQLException, IOException {
		try {
			  logger.debug("ManageApplicationsController: downloadApplicationDocument method: Entered");
		      ManageApplicationsDAO dao=new ManageApplicationsDAO();
		      InputStream is = dao.fetchDocument(id);
		      // copy InputStream to response's OutputStream
		      org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
		      logger.debug("ManageApplicationsController: downloadApplicationDocument method: Exit");
		      response.flushBuffer();
		    } catch (IOException ex) {
		      logger.error("Error in ManageApplicationsController: downloadApplicationDocument method while writing file to output stream for job application with ID: "+id);
		      throw new IOException("Error while downloading document.");
		    }
		
	}
}