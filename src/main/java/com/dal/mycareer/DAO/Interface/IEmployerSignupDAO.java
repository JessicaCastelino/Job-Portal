package com.dal.mycareer.DAO.Interface;

import com.dal.mycareer.DTO.EmployerSignupDTO;

public interface IEmployerSignupDAO {
	public boolean submitEmployerRequest(EmployerSignupDTO empSignupDTO);
}
