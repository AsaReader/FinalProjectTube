package tube.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tube.entities.Comment;

public interface CommentDAO extends JpaRepository<Comment, Integer> {
	
	 List<Comment> findByVideoId (Integer videoId);
	 

}
