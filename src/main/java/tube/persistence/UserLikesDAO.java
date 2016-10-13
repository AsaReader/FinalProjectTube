package tube.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import tube.entities.UserLikes;

public interface UserLikesDAO extends JpaRepository<UserLikes, Integer>{
	
	int countByVideoIdAndLikeStatus(int videoId, boolean likeStatus);
	

}
