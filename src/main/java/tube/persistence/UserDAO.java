package tube.persistence;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import tube.entities.User;

public interface UserDAO extends JpaRepository<User, Integer> {
	
	@Cacheable(value = "userCache")
	User findByUsername(String username);
	
	@Cacheable(value = "userCache")
	User findByEmail(String email);
	
	@Cacheable(value = "userCache")
	@Override
	User findOne(Integer userId);
	
	@CachePut(value = "userCache", key="#result.username")
	@Override
	<S extends User> S saveAndFlush(S user);
	
	int countByUsername(String username);

	int countByEmail(String email);
	
}
