package com.dal.mycareer.passwordgenerator;

import org.junit.Test;

public class PasswordGeneratorTest {

	@Test
	public void testGeneratePassword() {
		// Main class object
		PasswordGenerator psg = new PasswordGenerator();
		String pswd = psg.generatePassword();

	}

}
