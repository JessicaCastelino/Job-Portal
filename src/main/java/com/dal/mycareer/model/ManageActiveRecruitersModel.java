package com.dal.mycareer.model;

import java.sql.SQLException;
import java.util.List;

import com.dal.mycareer.DAO.Interface.IManageActiveRecruitersDAO;
import com.dal.mycareer.DTO.RecruiterRequest;
import com.dal.mycareer.imodel.IManageActiveRecruitersModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ManageActiveRecruitersModel implements IManageActiveRecruitersModel 
{

    @Autowired
    private IManageActiveRecruitersDAO manageActiveRecruitersDAO;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public ManageActiveRecruitersModel() 
	{
	}

	public ManageActiveRecruitersModel(IManageActiveRecruitersDAO manageActiveRecruitersDAO) 
	{
		this.manageActiveRecruitersDAO = manageActiveRecruitersDAO;
	}
    @Override
    public boolean deleteActiveRecruiter(int employerId) throws SQLException 
    {
        logger.info("BL: ManageActiveRecruitersModel- deleteActiveRecruiter method started for-" + employerId);
		return manageActiveRecruitersDAO.deleteActiveRecruiter(employerId);
    }

    @Override
    public List<RecruiterRequest> fetchActiveRecruiters() throws SQLException 
    {
        logger.info("BL: ManageActiveRecruitersModel- fetchActiveRecruiters method started");
		return manageActiveRecruitersDAO.fetchActiveRecruiters();
    }

}