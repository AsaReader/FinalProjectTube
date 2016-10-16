package tube.persistence;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tube.entities.Tag;

public interface TagDAO extends JpaRepository<Tag, Integer>  {

	@Cacheable(value = "tagCache")
	@Query(value = "SELECT * FROM tags WHERE name LIKE ?1%", nativeQuery = true)
	List<Tag> getByName(String name);
	
	@Cacheable(value = "tagCache")
	Tag findByName(String name);

	@Query(value = "SELECT t.* FROM tags t "
			+ "JOIN video_has_tags vt ON (vt.tags_id = t.id)"
			+ "JOIN videos v ON (vt.video_id = v.id)"
			+ "WHERE v.id = ?1", nativeQuery = true)
	@Cacheable(value = "tagCache")
	List<Tag> findByVideoId(Integer id);
}
