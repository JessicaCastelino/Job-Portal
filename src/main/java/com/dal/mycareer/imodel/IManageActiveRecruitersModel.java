package com.dal.mycareer.imodel;

import java.sql.SQLException;
import java.util.List;

import com.dal.mycareer.DTO.RecruiterRequest;

public interface IManageActiveRecruitersModel
{
    public boolean deleteActiveRecruiter(int employerId) throws SQLException;
	public List<RecruiterRequest> fetchActiveRecruiters() throws SQLException;
}