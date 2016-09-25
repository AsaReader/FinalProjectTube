package business;

import static org.junit.Assert.*;
import org.junit.Test;
import validators.EmailValidator;

public class JUnitEmailValidation {

	@Test
	public void testEmailValidator() {
		assertTrue("Valid email", EmailValidator.isEmailValid("k.tsv@abv.bg"));
		assertFalse("Invalid email", EmailValidator.isEmailValid("k.tsv@abv"));
		assertTrue("Valid email", EmailValidator.isEmailValid("k_tsv@abv.bg"));
		assertTrue("Valid email", EmailValidator.isEmailValid("k-tsv@abv.bg"));
		assertTrue("Valid email", EmailValidator.isEmailValid("123.tsv@abv.bg"));
		assertTrue("Valid email", EmailValidator.isEmailValid("vb@abv.eu"));
		assertFalse("Invalid email", EmailValidator.isEmailValid("123.tsv@abv.b"));
		assertFalse("Invalid email", EmailValidator.isEmailValid(".tsv@abv.eu"));
		assertFalse("Invalid email", EmailValidator.isEmailValid("v@abv.eu"));
		assertFalse("Invalid email", EmailValidator.isEmailValid("v.@abv.eu"));
		assertTrue("Valid email", EmailValidator.isEmailValid("v.ve@abv.com"));
		assertFalse("Invalid email", EmailValidator.isEmailValid(null));
		assertFalse("Invalid email", EmailValidator.isEmailValid(""));
	}

}
