package com.dal.mycareer.model;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.dal.mycareer.DAO.Interface.ILoginDAO;
import com.dal.mycareer.DTO.UserLogin;

public class LoginModelTest {

	@Test
	public void testVerifyLoginInvalid() {

		LoginModel loginModel = new LoginModel();

		// Mocked Objects
		ILoginDAO mockedLoginDAO = mock(ILoginDAO.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		Model mockModel = new ExtendedModelMap();
		UserLogin mockUserLogin = mock(UserLogin.class);

		// Output
		Model output = loginModel.verifyLogin(mockModel, mockUserLogin, mockedLoginDAO, req);

		// Assertion
		Assert.assertEquals(true, output.asMap().get("isInvalid"));
	}

	@Test
	public void testVerifyLoginValidEmployer() {

		// Mocked Objects
		LoginModel loginModel = new LoginModel();
		ILoginDAO mockedLoginDAO = mock(ILoginDAO.class);
		Model mockModel = new ExtendedModelMap();
		UserLogin mockUserLogin = mock(UserLogin.class);
		HttpServletRequest req = mock(HttpServletRequest.class);

		// Data Preparation
		Mockito.when(mockedLoginDAO.isValidUser(mockUserLogin)).thenReturn(true);
		Mockito.when(mockUserLogin.getRole()).thenReturn("employer");

		// Output
		Model output = loginModel.verifyLogin(mockModel, mockUserLogin, mockedLoginDAO, req);

		// Assertion
		Assert.assertEquals(true, output.asMap().get("isValidUser"));
	}

	@Test
	public void testVerifyLoginValidStudent() {

		// Mocked Objects
		LoginModel loginModel = new LoginModel();
		ILoginDAO mockedLoginDAO = mock(ILoginDAO.class);
		Model mockModel = new ExtendedModelMap();
		UserLogin mockUserLogin = mock(UserLogin.class);
		HttpServletRequest req = mock(HttpServletRequest.class);

		// Data Preparation
		Mockito.when(mockedLoginDAO.isValidUser(mockUserLogin)).thenReturn(true);
		Mockito.when(mockUserLogin.getRole()).thenReturn("student");

		// Output
		Model output = loginModel.verifyLogin(mockModel, mockUserLogin, mockedLoginDAO, req);

		// Assertion
		Assert.assertEquals(true, output.asMap().get("isValidUser"));
	}

	@Test
	public void testVerifyLoginValidAdmin() {

		// Mocked Objects
		LoginModel loginModel = new LoginModel();
		ILoginDAO mockedLoginDAO = mock(ILoginDAO.class);
		Model mockModel = new ExtendedModelMap();
		UserLogin mockUserLogin = mock(UserLogin.class);
		HttpServletRequest req = mock(HttpServletRequest.class);

		// Data Preparation
		Mockito.when(mockedLoginDAO.isValidUser(mockUserLogin)).thenReturn(true);
		Mockito.when(mockUserLogin.getRole()).thenReturn("co-op Admin");

		// Output
		Model output = loginModel.verifyLogin(mockModel, mockUserLogin, mockedLoginDAO, req);

		// Assertion
		Assert.assertEquals(true, output.asMap().get("isValidUser"));
	}

}
