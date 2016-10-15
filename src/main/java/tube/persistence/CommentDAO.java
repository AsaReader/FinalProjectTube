package tube.persistence;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tube.entities.Comment;

public interface CommentDAO extends JpaRepository<Comment, Integer> {
	
	@Query(value = "SELECT * FROM youtubeDB.comments where video_id = ?1 ", nativeQuery = true)
	 List<Comment> findByVideoId (Integer videoId);
	 
	 @CacheEvict(value = "commentCache",key="#result.id")
	 @Override
	<S extends Comment> S save(S arg0);
	 
	 
}
