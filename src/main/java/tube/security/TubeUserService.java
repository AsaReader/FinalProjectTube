package tube.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import tube.entities.User;
import tube.persistence.UserDAO;

@Service
public class TubeUserService implements UserDetailsService {

	private final UserDAO userDao;

	@Autowired
	public TubeUserService(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username "+username+" not found.");
		}
		return new SecurityUser(user);
	}

}
