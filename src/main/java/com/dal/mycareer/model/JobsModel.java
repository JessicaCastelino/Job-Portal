package com.dal.mycareer.model;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dal.mycareer.DAO.Interface.IJobsDAO;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.imodel.IJobsModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class JobsModel implements IJobsModel 
{
    @Autowired
    private IJobsDAO jobsManagerDao;
	private static final String SESSION_NAME = "sessionName"; 
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public JobsModel()
    {

    }

    public JobsModel(IJobsDAO jobsDAO)
    {
        this.jobsManagerDao = jobsDAO;
    }

    @Override
    public boolean updateJobStatus(int jobRecordId, boolean jobStatus) 
    {
        logger.info("DL: updateJobStatus method started");
        return this.jobsManagerDao.updateJobStatus(jobRecordId, jobStatus);
    }

	@Override
	public Model fetchJob(Model model, int jobId, HttpServletRequest request, IJobsDAO jobsDao) throws SQLException
	{
		logger.debug("JobsModel: fetchJob method: Entered");
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		if(userSessionName!="" && userSessionName!=null)
		{
			JobDetails job=jobsDao.fetchJob(jobId);					
			model.addAttribute("job", job);
		}
		logger.debug("JobsModel: fetchJob method: Exit");
		return model;
	}

}