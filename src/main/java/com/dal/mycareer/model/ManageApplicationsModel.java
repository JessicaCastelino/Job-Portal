package com.dal.mycareer.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dal.mycareer.DAO.Impl.ManageApplicationsDAO;
import com.dal.mycareer.DAO.Interface.IManageApplicationsDAO;
import com.dal.mycareer.DTO.Application;
import com.dal.mycareer.imodel.IManageApplicationsModel;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageApplicationsModel implements IManageApplicationsModel
{
	@Autowired
	IManageApplicationsDAO applicationsManagerDAO;
	private static final String SESSION_NAME = "sessionName";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<Application> getApplications(int jobRecordId)
	{
		return applicationsManagerDAO.getApplications(jobRecordId);
	}

	@Override
	public boolean updateApplicationStatus(int applicationId, String appStatus)
	{
		return applicationsManagerDAO.updateApplicationStatus(applicationId, appStatus);
	}

	@Override
	public void downloadFile(int applicationId, HttpServletRequest request, HttpServletResponse response)
	        throws IOException, SQLException
	{
		logger.debug("ManageApplicationsModel: downloadFile method: Entered");
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		if (userSessionName != "" && userSessionName != null)
		{
			try
			{
				ManageApplicationsDAO dao = new ManageApplicationsDAO();
				InputStream is = dao.fetchDocument(applicationId);
				// copy InputStream to response's OutputStream
				IOUtils.copy(is, response.getOutputStream());
				logger.debug("ManageApplicationsModel: downloadFile method: Exit");
				response.flushBuffer();
			} catch (IOException ex)
			{
				logger.error(
				        "Error in ManageApplicationsModel: downloadFile method while writing file to output stream for job application with ID: "
				                + applicationId);
				throw new IOException("Error while downloading document.");
			} catch (SQLException e)
			{
				logger.error(
				        "Error in ManageApplicationsModel: downloadFile method while writing file to output stream for job application with ID: "
				                + applicationId);
				throw new SQLException("Error while downloading document.");
			}
		}
		else
		{
			logger.debug("Session does not exist in ManageApplicationsModel: downloadFile method: Exit");
		}
	}

}
