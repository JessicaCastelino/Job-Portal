package com.dal.mycareer.model;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dal.mycareer.DAO.Impl.ManageApplicationsDAO;

public class ManageApplicationsModelTest
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void testDownloadFile()
	{
		HttpSession mockSession = mock(HttpSession.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse res = mock(HttpServletResponse.class);
		ManageApplicationsDAO mockDao = mock(ManageApplicationsDAO.class);
		InputStream file = mock(InputStream.class);
		ServletOutputStream mockOutputStream = mock(ServletOutputStream.class);
		try
		{
			Mockito.when(req.getSession()).thenReturn(mockSession);
			Mockito.when(mockSession.getAttribute("sessionName")).thenReturn("mock@dal.ca");
			Mockito.when(mockDao.fetchDocument(1)).thenReturn(file);
			Mockito.when(res.getOutputStream()).thenReturn(mockOutputStream);

		} catch (SQLException e)
		{
			logger.debug("Error during execution of ManageApplicationsModelTest: testDownloadFile()");
		} catch (IOException e)
		{
			logger.debug("Error during execution of ManageApplicationsModelTest: testDownloadFile()");
		}

	}

}
