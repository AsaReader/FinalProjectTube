package tube.persistence;

import tube.entities.User;

public interface UserDAO {

	User insert(User user);

	User selectByUsername(String username);

}
