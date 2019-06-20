package com.dal.mycareer.DAO.Interface;

import com.dal.mycareer.DTO.UserLogin;

public interface ILoginDAO {

	public boolean isValidUser(UserLogin user);

}
