package tube.persistence;

import org.springframework.cache.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tube.entities.User;

public interface UserDAO extends JpaRepository<User, Integer> {
	
	@Cacheable(value = "userCache")
	User findByUsername(String username);
	
	@Cacheable(value = "userCache")
	User findByEmail(String email);
	
	@Cacheable(value = "userCache")
	@Override
	User findOne(Integer userId);
	
	@Override
	@CacheEvict(value = "userCache", key = "#result.username")
	<S extends User> S saveAndFlush(S user);
	
	@Override
	@CacheEvict(value = "userCache", key = "#result.username", allEntries = true)
	<S extends User> S save(S arg0);
	
	int countByUsername(String username);

	int countByEmail(String email);

}
