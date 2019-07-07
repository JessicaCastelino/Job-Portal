package com.dal.mycareer.passwordgenerator;

import java.util.Random;

public class PasswordGenerator implements IPasswordGenerator {

	@Override
	public String generatePassword() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 8;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedPassword = buffer.toString();

		return generatedPassword;
	}

}
