package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator {
	private static final String NAME_PATTERN = "[A-Z]{1}[a-z]+";
	private static final String USER_NAME_PATTERN = "[A-Za-z0-9_-]{3,15}";

	public static boolean validateName(String value) {
		if (value!= null && !value.isEmpty()) {
			Pattern namePattern = Pattern.compile(NAME_PATTERN);
			Matcher matcher = namePattern.matcher(value.toString());
			return matcher.matches();
		}		
		return false;
	}
	
	public static boolean validateUserName(String userName) {
		if (userName!= null && !userName.isEmpty()) {
			Pattern namePattern = Pattern.compile(USER_NAME_PATTERN);
			Matcher matcher = namePattern.matcher(userName.toString());
			return matcher.matches();
		}		
		return false;
	}
	
	public static boolean validateVideoName(String videoName) {
		if (videoName!= null && !videoName.isEmpty()) {
			return true;
		}		
		return false;
	}
}