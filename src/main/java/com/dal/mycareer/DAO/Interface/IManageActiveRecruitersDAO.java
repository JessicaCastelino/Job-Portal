package com.dal.mycareer.DAO.Interface;
import java.sql.SQLException;
import java.util.List;

import com.dal.mycareer.DTO.RecruiterRequest;

public interface IManageActiveRecruitersDAO
{
    public List<RecruiterRequest> fetchActiveRecruiters() throws SQLException;
	public boolean deleteActiveRecruiter(int employerId) throws SQLException;
}