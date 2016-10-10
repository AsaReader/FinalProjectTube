package tube.persistence;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tube.entities.Tag;

public interface TagDAO extends JpaRepository<Tag, Integer>  {

	@Cacheable(value = "tubeCache")
	@Query(value = "SELECT * FROM tags WHERE name LIKE ?1%",nativeQuery = true)
	List<Tag> getByName(String name);
	
	@Cacheable(value = "tubeCache")
	Tag findByName(String name);
}
