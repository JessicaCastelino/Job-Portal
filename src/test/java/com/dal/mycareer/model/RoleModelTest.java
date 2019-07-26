package com.dal.mycareer.model;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

public class RoleModelTest {

	@Test
	public void testGetBasePage() {
		
		// Creating Mock Objects.
		HttpSession mockSession = mock(HttpSession.class);
		HttpServletRequest req = mock(HttpServletRequest.class);
		Model mockModel = mock(Model.class);
		Map<String, Object> mockMap = Mockito.mock(Map.class);

		// Output for Mock Methods
		Mockito.when(mockSession.getAttribute("sessionName")).thenReturn("mockAttribute");
		Mockito.when(mockSession.getAttribute("role")).thenReturn("employer");
		Mockito.when(mockSession.getAttribute("home")).thenReturn("mockHome");
		Mockito.when(mockModel.asMap()).thenReturn(mockMap);
		Mockito.when(mockMap.get("role")).thenReturn("employer");
		Mockito.when(mockMap.get("reqPage")).thenReturn("mockPage");

		//Mocked Output
		when(req.getSession()).thenReturn(mockSession);

		// Class object creation
		RoleModel rm = new RoleModel();
		Model output = rm.getBasePage(mockModel, req);
		
		//Assertion
		Assert.assertEquals("mockPage", output.asMap().get("reqPage"));
	}

}
