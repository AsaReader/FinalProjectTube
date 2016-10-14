package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tube.entities.User;
import tube.entities.UserLikes;
import tube.entities.UserLikesId;
import tube.entities.Video;
import tube.persistence.UserDAO;
import tube.persistence.UserLikesDAO;
import tube.persistence.VideoDAO;
import tube.web.controllers.UserLikesController.Helper;

@Controller
public class VideoController {
	
	@Autowired
	private VideoDAO videoDao;

	@Autowired
	UserDAO userDao;

	@Autowired
	UserLikesDAO userLikesDao;
	
	@RequestMapping(value = "/video/{videoId}", method = GET)
	public String playVideo(Model model ,Principal principal, @PathVariable("videoId") int videoId ){
		Video video = videoDao.findOne(videoId);
		Integer currentViews = video.getViews();
		video.setViews(currentViews == null ? 1 : ++currentViews);
		video = videoDao.save(video);
		model.addAttribute("video", video);
		
		String likeButton;
		String dislikeButton;
		if(principal != null){
		String username = principal.getName();

		User user = userDao.findByUsername(username);
		int userId = user.getId();

		int unlikesCount;
		int likesCount;
		int beforeUnlikes;
		int beforeLikes ;
		UserLikes userunlikes = new UserLikes(new UserLikesId(userId, videoId), user, video, false);
		UserLikes userlikes = new UserLikes(new UserLikesId(userId, videoId), user, video, true);
		
		likesCount = userLikesDao.getUserLikes(userId, videoId, true);
		unlikesCount = userLikesDao.getUserLikes(userId, videoId, false);
		
		int totalLikes = userLikesDao.countByVideoIdAndLikeStatus(videoId, true);
		int totalDislikes = userLikesDao.countByVideoIdAndLikeStatus(videoId, false);
		if(likesCount == 1 ){
			likeButton = "Liked " + totalLikes;
			dislikeButton = "Dislike " + totalDislikes;
			
		}else{
			if(unlikesCount == 1){
				likeButton = "Like " + totalLikes;
				dislikeButton = "Disliked " + totalDislikes;
			}else{
				likeButton = "Like " + totalLikes;
				dislikeButton = "Dislike " + totalDislikes;
			}
		}
		
		
		
		}else{
			int totalLikes = userLikesDao.countByVideoIdAndLikeStatus(videoId, true);
			int totalDislikes = userLikesDao.countByVideoIdAndLikeStatus(videoId, false);
			likeButton = "Like " + totalLikes;
			dislikeButton = "Dislike " + totalDislikes;
			
			
			
			
		}
		UserLikesController.Helper helper = new Helper(dislikeButton, likeButton);	
		model.addAttribute("helper", helper);
		return "video";
		
	}

	
	

}
