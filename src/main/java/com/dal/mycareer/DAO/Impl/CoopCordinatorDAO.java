package com.dal.mycareer.DAO.Impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dal.mycareer.DAO.Interface.ICoopCordinatorDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.RecruiterRequest;


public class CoopCordinatorDAO implements ICoopCordinatorDAO{
	Connection con = null;
	CallableStatement callableStatement = null;
	@Override
	public List<RecruiterRequest> fetchRecruiterRequests() {
		con = DatabaseConnection.getConnection();
		RecruiterRequest recruiterRequest = null;
		List<RecruiterRequest> requests = new ArrayList<RecruiterRequest>();
		try {
			callableStatement = con.prepareCall("{call fetchRecruiterRequests()}");
			boolean results = callableStatement.execute();
			while (results) {
				ResultSet rs = callableStatement.getResultSet();
				while (rs.next()) {
					recruiterRequest = new RecruiterRequest();
					recruiterRequest.setId(Integer.toString(rs.getInt(1)));
					recruiterRequest.setFirstname(rs.getString(2));
					recruiterRequest.setLastname(rs.getString(3));
					recruiterRequest.setEmail(rs.getString(4));
					recruiterRequest.setCompanyname(rs.getString(5));
					requests.add(recruiterRequest);
				}

				rs.close();
				results = callableStatement.getMoreResults();
			}
			return requests;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return requests;
		} finally {
			try {
				callableStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		

}
