package tube.persistence;

import java.util.List;
import java.util.Set;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tube.entities.Tag;
import tube.entities.Video;

public interface VideoDAO extends JpaRepository<Video, Integer> {
	@Cacheable(value = "videoCache")
	List<Video> getByTags(Set<Tag> tags);
	
	@Cacheable(value = "videoCache")
	@Query(value = "SELECT * FROM videos WHERE title LIKE ?1%",nativeQuery = true)
	List<Video> getByTitle(String title);
	
	@Cacheable(value = "videoCache")
	@Query(value = "SELECT DISTINCT v.* FROM videos v "
			+ "JOIN video_has_tags vt ON (v.id = vt.video_id) "
			+ "JOIN tags t ON (t.id = vt.tags_id) "
			+ "WHERE v.title LIKE ?1% "
			+ "OR t.name LIKE ?1% ", nativeQuery = true)
	List<Video> getVideosByInput(String description);
	
	@Cacheable(value = "videoCache")
	@Query(value = "SELECT v.* FROM videos v ORDER BY v.views DESC LIMIT 10", nativeQuery = true)
	List<Video> getMostWatchedVideos();
	
	@Query(value = "Select Count(likeStatus) from user_likes where likeStatus = ?1 AND video_id = ?2 AND user_id - ?3", nativeQuery = true)
	Integer getLikes(Boolean bool, int videoId, int userUd);

	@Cacheable(value = "videoCache")
	@Query(value = "SELECT v.* FROM videos v ORDER BY v.id DESC LIMIT 8", nativeQuery = true)
	List<Video> getLastVideos();
	

	
	@Override
	@Cacheable(value = "videoCache")
	Video findOne(Integer videoId);

	
	
	
	@Override
	@CacheEvict(value = "videoCache", key = "#result.id")
	<S extends Video> S save(S video);
	
	@Override
	@CacheEvict(value = "videoCache", key = "#result.id")
	<S extends Video> S saveAndFlush(S video);

	@Query(value = "UPDATE videos v SET v.views = ?1 WHERE v.id = ?2", nativeQuery = true)
	void updateViewCount(Integer views, int videoId);
}
