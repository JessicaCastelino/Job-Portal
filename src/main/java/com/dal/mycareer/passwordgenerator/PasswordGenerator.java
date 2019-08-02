package com.dal.mycareer.passwordgenerator;

import java.util.Properties;
import com.dal.mycareer.propertiesparser.PropertiesParser;

public class PasswordGenerator implements IPasswordGenerator {
	
	private static final String DEFAULT_SYMBOL = "&*";

	private static final String DEFAULT_UPPER_CASE = "ABC";

	private static final String DEFAULT_LOWER_CASE = "abc";

	private static final String DEFAULT_NUM = "1234";

	private static final String DEFAULT_LEN = "8";

	private static final int DEF_LEN = Integer.parseInt(DEFAULT_LEN);

	private static final String PWD_MAX_LEN = "pwdMaxLen";

	private static final String PSWD_SYMBOL = "pswdSymbol";

	private static final String PSWD_UPPER_CASE = "pswdUpperCase";

	private static final String PSWD_LOWER_CASE = "pswdLowerCase";

	private static final String DIGITS_PROP = "digits";

	private static final Properties PROPERTY_MAP = PropertiesParser.getPropertyMap();
	
	private int MAX_LENGTH = Integer.parseInt(PROPERTY_MAP.getProperty(PWD_MAX_LEN, DEFAULT_LEN).toString());
	
	private static java.util.Random r = new java.util.Random();
	
    
    private static final String DIGITS = PROPERTY_MAP.getProperty(DIGITS_PROP,DEFAULT_NUM).toString();
    private static final String LOCASE_CHARACTERS = PROPERTY_MAP.getProperty(PSWD_LOWER_CASE,DEFAULT_LOWER_CASE).toString();
    private static final String UPCASE_CHARACTERS = PROPERTY_MAP.getProperty(PSWD_UPPER_CASE,DEFAULT_UPPER_CASE).toString();
    private static final String SYMBOLS = PROPERTY_MAP.getProperty(PSWD_SYMBOL,DEFAULT_SYMBOL).toString();
    private static final String ALL = DIGITS + LOCASE_CHARACTERS + UPCASE_CHARACTERS + SYMBOLS;
    private static final char[] upcaseArray = UPCASE_CHARACTERS.toCharArray();
    private static final char[] locaseArray = LOCASE_CHARACTERS.toCharArray();
    private static final char[] digitsArray = DIGITS.toCharArray();
    private static final char[] symbolsArray = SYMBOLS.toCharArray();
    private static final char[] allArray = ALL.toCharArray();

	@Override
	public String generatePassword() {
		 StringBuilder sb = new StringBuilder();
		 
	        sb.append(locaseArray[r.nextInt(locaseArray.length)]);
	 
	        sb.append(upcaseArray[r.nextInt(upcaseArray.length)]);
	 
	        sb.append(digitsArray[r.nextInt(digitsArray.length)]);
	 
	        sb.append(symbolsArray[r.nextInt(symbolsArray.length)]);
	        
	        if(MAX_LENGTH < DEF_LEN) {
	        	MAX_LENGTH = DEF_LEN;
	        } 
	 
	        // fill in remaining with random letters
	        for (int i = 0; i < MAX_LENGTH-4; i++) {
	            sb.append(allArray[r.nextInt(allArray.length)]);
	        }

		return sb.toString();
	}

}
