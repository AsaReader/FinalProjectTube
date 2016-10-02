package tube.persistence.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import tube.entities.User;
import tube.persistence.UserDAO;

@Repository
public class MockUserDAO implements UserDAO {

	private List<User> users = new ArrayList<>();
	
	public MockUserDAO() {
	}
	
	@Override
	public User insert(User user) {
		users.add(user);
		user.setId(users.indexOf(user));
		return user;
	}

	@Override
	public User selectByUsername(String username) {
		int i = 0;
		User user =  new User();
		for (; i < users.size(); i++) {
			if (users.get(i).getUsername().equals(username)) {
				user = users.get(i);
				break;
			}
		}
		return user;
	}
	
}
