package com.dal.mycareer.DAO.Interface;

import java.sql.SQLException;
import java.util.List;

import com.dal.mycareer.DTO.RecruiterRequest;

public interface IRecruiterRegistrationRequestDAO
{
	public List<RecruiterRequest> fetchAllRecruiterRequests() throws SQLException;
	public RecruiterRequest fetchRecruiterRequest(int reqID) throws SQLException;
	public int approveRequest(int requestId, String username, String password) throws SQLException;
	public int rejectRequest(int requestId) throws SQLException;
}
