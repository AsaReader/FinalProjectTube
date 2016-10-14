package tube.persistence;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import tube.entities.Comment;

public interface CommentDAO extends JpaRepository<Comment, Integer> {
	
	
	@Cacheable(value = "commentCache",key="#result.id" )
	 List<Comment> findByVideoId (Integer videoId);
	 
	 @CacheEvict(value = "commentCache",key="#result.id")
	 @Override
	<S extends Comment> S save(S arg0);
	 
	 
}
