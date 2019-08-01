package com.dal.mycareer.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dal.mycareer.DAO.Interface.ICoopCordinatorDAO;
import com.dal.mycareer.DTO.RecruiterRequest;
import com.dal.mycareer.imodel.ICoopCoordinatorModel;

@Service
public class CoopCoordinatorModel implements ICoopCoordinatorModel 
{
	@Autowired
	private ICoopCordinatorDAO coopCordinatorDAO;
	@Autowired
	private static List<RecruiterRequest> requests;
	private static final String SESSION_NAME = "sessionName";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public CoopCoordinatorModel() 
	{
	}

	public CoopCoordinatorModel(ICoopCordinatorDAO coopCordinatorDAO) 
	{
		this.coopCordinatorDAO = coopCordinatorDAO;
	}


	@Override
	public boolean deleteActiveRecruiter(int employerId) 
	{
		logger.info("BL: CoopCoordinatorModel- deleteActiveRecruiter method started for-" + employerId);
		return coopCordinatorDAO.deleteActiveRecruiter(employerId);
	}

	@Override
	public List<RecruiterRequest> fetchActiveRecruiters() 
	{
		logger.info("BL: CoopCoordinatorModel- fetchActiveRecruiters method started");
		return coopCordinatorDAO.fetchActiveRecruiters();
	}
}
