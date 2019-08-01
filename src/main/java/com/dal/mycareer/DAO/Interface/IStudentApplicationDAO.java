package com.dal.mycareer.DAO.Interface;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.multipart.MultipartFile;

public interface IStudentApplicationDAO
{
	public int submitJobApplication(MultipartFile file, int studentId, int jobId) throws SQLException, IOException;
	public int withdrawJobApplication(int jobId) throws SQLException;
	public int checkApplicationExists(int studentId, int jobId) throws SQLException;
	
}
