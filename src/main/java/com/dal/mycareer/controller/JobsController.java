package com.dal.mycareer.controller;

import com.dal.mycareer.imodel.IJobsModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JobsController
{
    @Autowired
    IJobsModel jobsManager;

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
}