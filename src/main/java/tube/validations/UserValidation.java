package tube.validations;

import org.springframework.stereotype.Service;

import tube.persistence.UserDAO;

@Service
public class UserValidation {
	
	public UserValidation() {}
	
	public boolean isUsernameAvailable(String username, UserDAO userDao) {
		if(username == null) {
			return false;
		}
		boolean available = userDao.countByUsername(username) <= 0;
		return available;
	}
	
	public boolean isEmailAvailable(String email, UserDAO userDao) {
		if(email == null) {
			return false;
		}
		boolean available = userDao.countByEmail(email) <= 0;
		return available;
	}
}
