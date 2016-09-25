package validators;

public class PasswordValidator {
	private static final int MIN_LENGHT_OF_PASSWORD = 6;

	public static boolean checkPassword(String password) {
		boolean containsSmall = false;
		boolean contasinsLarge = false;
		boolean containsDigit = false;

		if (password != null && !password.isEmpty()) {
			for (int index = 0; index < password.length(); index++) {
				if (password.charAt(index) >= 'a' && password.charAt(index) <= 'z') {
					containsSmall = true;
				}
				if (password.charAt(index) >= 'A' && password.charAt(index) <= 'Z') {
					contasinsLarge = true;
				}
				if (password.charAt(index) >= '0' && password.charAt(index) <= '9') {
					containsDigit = true;
				}
			}

			if (password.length() >= MIN_LENGHT_OF_PASSWORD && containsSmall && contasinsLarge && containsDigit) {
				return true;
			}
		}
		return false;
	}

}
