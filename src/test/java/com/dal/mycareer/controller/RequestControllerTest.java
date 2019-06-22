package com.dal.mycareer.controller;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

public class RequestControllerTest {
	
	public HttpSession session = Mockito.mock(HttpSession.class);

	@Test
	public void test() {
		session.setAttribute("hithere", "hi here");
	}

}
