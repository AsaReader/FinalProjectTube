package tube.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import tube.entities.User;

public interface UserDAO extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
	int countByUsername(String username);
}
