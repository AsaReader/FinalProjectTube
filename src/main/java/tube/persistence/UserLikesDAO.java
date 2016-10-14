package tube.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tube.entities.UserLikes;

public interface UserLikesDAO extends JpaRepository<UserLikes, Integer>{
	
	int countByVideoIdAndLikeStatus(int videoId, boolean likeStatus);
	
	@Query(value = "Delete from user_likes where user_id = ?1 AND video_id = ?2 AND likeStatus = ?3", nativeQuery = true )
	void deleteLike(int userId, int videoId, boolean isLiked); 
	

}
