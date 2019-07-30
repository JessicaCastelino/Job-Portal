package com.dal.mycareer.passwordgenerator;

import java.util.Properties;
import com.dal.mycareer.propertiesparser.PropertiesParser;

public class PasswordGenerator implements IPasswordGenerator {
	
	private static final String PSWD_SYMBOL = "pswdSymbol";

	private static final String PSWD_UPPER_CASE = "pswdUpperCase";

	private static final String PSWD_LOWER_CASE = "pswdLowerCase";

	private static final String DIGITS_PROP = "digits";

	private static final Properties PROPERTY_MAP = PropertiesParser.getPropertyMap();
	
	private static final int MAX_LENGTH = Integer.parseInt(PROPERTY_MAP.getProperty(DIGITS_PROP).toString());
	
	private static java.util.Random r = new java.util.Random();
	
    
    private static final String DIGITS = PROPERTY_MAP.getProperty(DIGITS_PROP).toString();
    private static final String LOCASE_CHARACTERS = PROPERTY_MAP.getProperty(PSWD_LOWER_CASE).toString();
    private static final String UPCASE_CHARACTERS = PROPERTY_MAP.getProperty(PSWD_UPPER_CASE).toString();
    private static final String SYMBOLS = PROPERTY_MAP.getProperty(PSWD_SYMBOL).toString();
    private static final String ALL = DIGITS + LOCASE_CHARACTERS + UPCASE_CHARACTERS + SYMBOLS;
    private static final char[] upcaseArray = UPCASE_CHARACTERS.toCharArray();
    private static final char[] locaseArray = LOCASE_CHARACTERS.toCharArray();
    private static final char[] digitsArray = DIGITS.toCharArray();
    private static final char[] symbolsArray = SYMBOLS.toCharArray();
    private static final char[] allArray = ALL.toCharArray();

	@Override
	public String generatePassword() {
		 StringBuilder sb = new StringBuilder();
		 
	        // get at least one lowercase letter
	        sb.append(locaseArray[r.nextInt(locaseArray.length)]);
	 
	        // get at least one uppercase letter
	        sb.append(upcaseArray[r.nextInt(upcaseArray.length)]);
	 
	        // get at least one digit
	        sb.append(digitsArray[r.nextInt(digitsArray.length)]);
	 
	        // get at least one symbol
	        sb.append(symbolsArray[r.nextInt(symbolsArray.length)]);
	 
	        // fill in remaining with random letters
	        for (int i = 0; i < MAX_LENGTH - 4; i++) {
	            sb.append(allArray[r.nextInt(allArray.length)]);
	        }

		return sb.toString();
	}

}
