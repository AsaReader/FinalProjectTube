package business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import validators.PasswordValidator;

public class JUnitPaswordValidation {

	@Test
	public void testEmailValidator() {
		assertTrue("Valid password", PasswordValidator.checkPassword("K0ki123"));//start with Capital
		assertTrue("Valid password", PasswordValidator.checkPassword("123K0ki"));//start with Digit
		assertTrue("Valid password", PasswordValidator.checkPassword("k0K123"));//start with small
		
		assertFalse("Invalid password", PasswordValidator.checkPassword("K0ki"));//lenght
		assertFalse("Invalid password", PasswordValidator.checkPassword("Kokiii"));//no digits
		assertFalse("Invalid password", PasswordValidator.checkPassword("k0ki123"));//no Capital
		assertFalse("Invalid password", PasswordValidator.checkPassword("K0KI123"));//no small
		assertFalse("Invalid password", PasswordValidator.checkPassword("kokiiii"));//only small
		assertFalse("Invalid password", PasswordValidator.checkPassword("1234567"));//only digits
		assertFalse("Invalid password", PasswordValidator.checkPassword("kokiiii"));//only small
		assertFalse("Invalid password", PasswordValidator.checkPassword("KOKIKOKI"));//only Big
		assertFalse("Invalid password", PasswordValidator.checkPassword(null));//null
		assertFalse("Invalid password", PasswordValidator.checkPassword(""));//empty String

	}
	
}
