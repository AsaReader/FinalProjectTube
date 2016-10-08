package tube.persistence;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import tube.entities.User;

public interface UserDAO extends JpaRepository<User, Integer> {
	
	@Cacheable(value = "tubeCache")
	User findByUsername(String username);
	
	@Cacheable(value = "tubeCache")
	User findByEmail(String email);
	
	@Cacheable(value = "tubeCache")
	@Override
	User findOne(Integer arg0);
	
	
	@CachePut(value = "tubeCache", key="#result.id")
	@Override
	User saveAndFlush(User user);
	
	@Cacheable(value = "tubeCache")
	int countByUsername(String username);

	@Cacheable(value = "tubeCache")
	int countByEmail(String email);
	
	
}
