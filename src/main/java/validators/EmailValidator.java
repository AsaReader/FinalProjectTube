package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
	private final static String REGEX_FOR_VALID_EMAIL = "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*){2,}@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})";

	public static boolean isEmailValid(String email) {
		if (email != null && !email.isEmpty()) {
			Pattern pattern = Pattern.compile(REGEX_FOR_VALID_EMAIL);
			Matcher macher = pattern.matcher(email);
			return macher.matches();
		}
		return false;
	}
}
