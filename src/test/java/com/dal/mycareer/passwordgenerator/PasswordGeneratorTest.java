package com.dal.mycareer.passwordgenerator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import org.mockito.Mockito;

public class PasswordGeneratorTest {

	@Test
	public void testGeneratePassword() {

		PasswordGenerator passGen = new PasswordGenerator();

		StringBuilder mockBuffer = mock(StringBuilder.class);

		when(new StringBuilder(8)).thenReturn(mockBuffer);
		Mockito.when(mockBuffer.toString()).thenReturn("mockPassword");

		passGen.generatePassword();

	}

}
