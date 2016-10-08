package tube.persistence;

import java.util.List;



import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import tube.entities.Comment;

public interface CommentDAO extends JpaRepository<Comment, Integer> {
	
	
	@Cacheable(value = "tubeCache",key="#result.id" )
	 List<Comment> findByVideoId (Integer videoId);
	 
	 @CachePut(value = "tubeCache",key="#result.id")
	 @Override
	<S extends Comment> S save(S arg0);
}
