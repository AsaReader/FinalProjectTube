package hashing;

import java.util.Calendar;
import java.util.Random;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class HashingAlgorithm {
	private static final int RANDOM_NUMBER_FOR_SESSION_TOKEN = 1000000;

	public static String hashingPassword(String sault, String loginPassword) {
		HashFunction hash = Hashing.sha256();
		String salt = hash.newHasher().putString(sault, Charsets.UTF_8).hash().toString();
		String pass = hash.newHasher().putString(loginPassword, Charsets.UTF_8).hash().toString();
		HashCode hs = hash.newHasher().putString(pass, Charsets.UTF_8).putString(salt, Charsets.UTF_8).hash();
		return hs.toString();
	}

	public static String hashingSault(String sault) {
		HashFunction hash = Hashing.sha256();
		String salt = hash.newHasher().putString(sault, Charsets.UTF_8).hash().toString();
		return salt;
	}

	public static String getUniqueToken() {
		String token = "";
		Calendar c = Calendar.getInstance();
		Random rand = new Random();
		long z = c.getTimeInMillis();
		int y = rand.nextInt(RANDOM_NUMBER_FOR_SESSION_TOKEN);
		token = Long.toString(z) + Integer.toString(y);
		HashFunction hf = Hashing.sha1();
		HashCode hcToken = hf.newHasher().putString(token, Charsets.UTF_8).hash();
		return hcToken.toString();
	}

}
