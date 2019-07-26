package com.dal.mycareer.passwordgenerator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({StringBuilder.class})
public class PasswordGeneratorTest {

	@Test
	public void testGeneratePassword() {
		PasswordGenerator pg = new PasswordGenerator();
		/*
		 StringBuilder mockBuilder =
		 * PowerMockito.mock(StringBuilder.class); try {
		 * PowerMockito.whenNew(StringBuilder.class).withArguments(8).thenReturn(
		 * mockBuilder); } catch (Exception e) {
		 * Assert.fail("Cannot create mock String builder"); }
		 * PowerMockito.when(mockBuilder.toString()).thenReturn("dummypassword");
		 */
		
		Assert.assertEquals("Test case pass:", pg.generatePassword(), "ty");
	}

}
