package com.dal.mycareer.DAO.Impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dal.mycareer.DBConnection.DatabaseConnection;

public class FileDAO {
	public int uploadFile(InputStream inputStream)
	{
		DatabaseConnection db=new DatabaseConnection();
		Connection c=db.getConnection();
		System.out.println("connection "+c);
		String sql = "INSERT INTO appliedJobs(id, document, applicationStatus, studentId, jobId) values (?, ?, ?, ?, ?)";
        try {
        	PreparedStatement statement = c.prepareStatement(sql);
			statement.setString(1, "1");
			if (inputStream != null) {
				System.out.println("Not null");
	            statement.setBlob(2, inputStream);
	        }
			statement.setString(3, "Submited");
			statement.setString(4, "1");
			statement.setString(5, "1");
			 int row = statement.executeUpdate();
	            if (row > 0) {
	            	 System.out.println("Inserted");
	                return 1;
	            }
	            else
	            {
	            	System.out.println("Not Inserted");
	            	return 0;
	            }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

}
