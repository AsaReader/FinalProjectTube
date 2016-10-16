package tube.mail;

import java.util.Random;

public class PasswordGenerator {
	private static final String NUMBERS = "0123456789";
	private static final String UPPER_ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LOWER_ALPHABETS = "abcdefghijklmnopqrstuvwxyz";
	private static final int MIN_LENGTH_OF_PASSWORD = 8;

	public static String getRandomPassword() {
		Random randomNum = new Random();
		StringBuilder password = new StringBuilder();
		int j = 0;
		for (int i = 0; i < MIN_LENGTH_OF_PASSWORD; i++) {
			j = randomNum.nextInt(3);
			password.append(getRandomSaltCharacters(j));
		}
		return password.toString();
	}

	private static String getRandomSaltCharacters(int pos) {
		Random randomNum = new Random();
		StringBuilder randomChar = new StringBuilder();
		switch (pos) {
		case 0:
			randomChar.append(NUMBERS.charAt(randomNum.nextInt(NUMBERS.length())));
			break;
		case 1:
			randomChar.append(UPPER_ALPHABETS.charAt(randomNum.nextInt(UPPER_ALPHABETS.length())));
			break;
		case 2:
			randomChar.append(LOWER_ALPHABETS.charAt(randomNum.nextInt(LOWER_ALPHABETS.length())));
			break;
		}
		return randomChar.toString();

	}

}
