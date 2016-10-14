package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tube.entities.User;
import tube.entities.UserLikes;
import tube.entities.UserLikesId;
import tube.entities.Video;
import tube.persistence.UserDAO;
import tube.persistence.UserLikesDAO;
import tube.persistence.VideoDAO;
import tube.security.SecurityUser;

@Controller
public class UserLikesController {

	static class Helper {

		private String dislikeButton;
		private String likeButton;

		public Helper(String dislikeButton, String likeButton) {
			this.likeButton = likeButton;
			this.dislikeButton = dislikeButton;
		}

		public String getDislikeButton() {
			return dislikeButton;
		}

		public void setDislikeButton(String dislikeButton) {
			this.dislikeButton = dislikeButton;
		}

		public String getLikeButton() {
			return likeButton;
		}

		public void setLikeButton(String likeButton) {
			this.likeButton = likeButton;
		}

	}

	@Autowired
	VideoDAO videoDao;

	@Autowired
	UserDAO userDao;

	@Autowired
	UserLikesDAO userLikesDao;

	@RequestMapping(value = "/video/like", method = POST)
	public @ResponseBody Helper getLikes(Principal principal, @RequestParam("videoId") int videoId,
			@RequestParam("likeId") int likeId) {

		String likeButton = null;
		String dislikeButton = null;

		Video video = videoDao.findOne(videoId);
		String username = principal.getName();
		

		User user = userDao.findByUsername(username);
		int userId = user.getId();
		
		int likesCount;

		if (likeId == 1) {
			int before = 0;
			UserLikes userlikes = new UserLikes(new UserLikesId(userId, videoId), user, video, true);
			likesCount = userLikesDao.countByVideoIdAndLikeStatus(videoId, true);
			
			before = likesCount;
			System.out.println(likesCount);
			userLikesDao.save(userlikes);
			likesCount = userLikesDao.countByVideoIdAndLikeStatus(videoId, true);
			System.out.println(likesCount);

			if (before == likesCount) {
			
				userLikesDao.delete(userlikes);
				likesCount = userLikesDao.countByVideoIdAndLikeStatus(videoId, true);
				likeButton = "Like " + likesCount;

			} else {
				likesCount = userLikesDao.countByVideoIdAndLikeStatus(videoId, true);
				likeButton = "Unlike " + likesCount;
			}

		

		} else {
			UserLikes userlikes = new UserLikes(new UserLikesId(userId, videoId), user, video, false);

			int isLiked = (int) video.getUserLikes().stream().filter((l) -> l.getUser().getId() == userId).count();
			int dislikesCount;

			if (isLiked == 1) {
				userLikesDao.delete(userlikes);
				dislikesCount = userLikesDao.countByVideoIdAndLikeStatus(videoId, false);
				dislikeButton = "Unlike " + dislikesCount;
			} else {
				userLikesDao.saveAndFlush(userlikes);
				dislikesCount = userLikesDao.countByVideoIdAndLikeStatus(videoId, false);
				dislikeButton = "Disunlike " + dislikesCount;

			}

		}

		UserLikesController.Helper helper = new Helper(dislikeButton, likeButton);
		return helper;
	}

	@RequestMapping(value = "/video/dislike", method = POST)
	public @ResponseBody List<UserLikes> getDisLikes(@RequestParam("username") String username,
			@RequestParam("videoId") int videoId) {

		User user = userDao.findByUsername(username);

		Video video = videoDao.findOne(videoId);
		UserLikes userlikes = new UserLikes(new UserLikesId(user.getId(), videoId), user, video, false);
		userLikesDao.saveAndFlush(userlikes);

		System.out.println(video.getUserLikes().size());

		return video.getUserLikes();
	}

}
