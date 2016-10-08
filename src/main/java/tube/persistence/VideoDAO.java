package tube.persistence;

import java.util.List;
import java.util.Set;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tube.entities.Tag;
import tube.entities.Video;

public interface VideoDAO extends JpaRepository<Video, Integer> {
	@Cacheable(value = "tubeCache")
	List<Video> getByTags(Set<Tag> tags);
	
	@Cacheable(value = "tubeCache")
	@Query(value = "SELECT * FROM videos WHERE title LIKE ?1%",nativeQuery = true)
	List<Video> getByTitle(String title);
	
	@Cacheable(value = "tubeCache")
	@Query(value = "SELECT v.* FROM videos v "
			+ "JOIN video_has_tags vt ON (v.id = vt.video_id) "
			+ "JOIN tags t ON (t.id = vt.tags_id) "
			+ "WHERE v.title LIKE ?1% "
			+ "OR t.name LIKE ?1% ", nativeQuery = true)
	List<Video> getVideosByInput(String description);
	
}
