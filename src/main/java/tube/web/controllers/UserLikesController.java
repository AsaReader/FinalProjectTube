package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import tube.web.controllers.UserLikesController.Helper;

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
		if(principal != null){

		Video video = videoDao.findOne(videoId);
		String username = principal.getName();

		User user = userDao.findByUsername(username);
		int userId = user.getId();

		int unlikesCount;
		int likesCount;
		int beforeUnlikes;
		UserLikes userunlikes = new UserLikes(new UserLikesId(userId, videoId), user, video, false);
		UserLikes userlikes = new UserLikes(new UserLikesId(userId, videoId), user, video, true);
	
		if (likeId == 1) {
			

			int beforeLikes;
			likesCount = userLikesDao.countByVideoIdAndLikeStatus(videoId, true);
			userLikesDao.save(userlikes);

			beforeLikes = likesCount;
			System.out.println("B: " + likesCount + " - likesCount ");
			System.out.println("B: " + beforeLikes + " - beforeLikes ");
			userLikesDao.save(userlikes);

			likesCount = userLikesDao.countByVideoIdAndLikeStatus(videoId, true);
			System.out.println("A: " + likesCount + " - likesCount ");
			System.out.println("A: " + beforeLikes + " - beforeLikes ");

			if (beforeLikes == likesCount) {

				unlikesCount = userLikesDao.countByVideoIdAndLikeStatus(videoId, false);

				beforeUnlikes = unlikesCount;
				System.out.println("Bunlikes: " + unlikesCount + " -count");
				System.out.println("B: " + beforeUnlikes + " -before");

				userLikesDao.save(userunlikes);

				userLikesDao.delete(userunlikes);
				System.out.println("Aunlikes: " + unlikesCount + " -count");
				System.out.println("A: " + beforeUnlikes + " -before");
				unlikesCount = userLikesDao.countByVideoIdAndLikeStatus(videoId, false);

				dislikeButton = "Dislike " + unlikesCount;

			}
			unlikesCount = userLikesDao.countByVideoIdAndLikeStatus(videoId, false);
			dislikeButton = "Dislike " + unlikesCount;
			if (beforeLikes == likesCount) {
				System.out.println("equals");
				userLikesDao.delete(userlikes);
				likesCount = userLikesDao.countByVideoIdAndLikeStatus(videoId, true);
				likeButton = "Like " + likesCount;

			} else {
				System.out.println("not equals");
				likesCount = userLikesDao.countByVideoIdAndLikeStatus(videoId, true);
				likeButton = "Liked " + likesCount;
			}

		} else {
			if (likeId == 2) {
				int beforeDislikes = 0;

				unlikesCount = userLikesDao.countByVideoIdAndLikeStatus(videoId, false);

				beforeDislikes = unlikesCount;
				System.out.println("Before unlike: " + unlikesCount);
				userLikesDao.save(userunlikes);
				unlikesCount = userLikesDao.countByVideoIdAndLikeStatus(videoId, false);
				System.out.println("After unlike:   " + unlikesCount);
				if (beforeDislikes == unlikesCount) {

					likesCount = userLikesDao.countByVideoIdAndLikeStatus(videoId, true);

					userLikesDao.save(userlikes);

					userLikesDao.delete(userlikes);
					likesCount = userLikesDao.countByVideoIdAndLikeStatus(videoId, true);
					likeButton = "Like " + likesCount;
				}

				likesCount = userLikesDao.countByVideoIdAndLikeStatus(videoId, true);
				likeButton = "Like " + likesCount;

				if (beforeDislikes == unlikesCount) {

					userLikesDao.delete(userunlikes);
					unlikesCount = userLikesDao.countByVideoIdAndLikeStatus(videoId, false);
					dislikeButton = "Dislike " + unlikesCount;

				} else {
					unlikesCount = userLikesDao.countByVideoIdAndLikeStatus(videoId, false);
					dislikeButton = "Disliked " + unlikesCount;
				}

			} else {

				likesCount = userLikesDao.getUserLikes(userId, videoId, true);
				unlikesCount = userLikesDao.getUserLikes(userId, videoId, false);

				int totalLikes = userLikesDao.countByVideoIdAndLikeStatus(videoId, true);
				int totalDislikes = userLikesDao.countByVideoIdAndLikeStatus(videoId, false);
				if (likesCount == 1) {
					likeButton = "Liked " + totalLikes;
					dislikeButton = "Dislike " + totalDislikes;

				} else {
					if (unlikesCount == 1) {
						likeButton = "Like " + totalLikes;
						dislikeButton = "Disliked " + totalDislikes;
					} else {
						likeButton = "Like " + totalLikes;
						dislikeButton = "Dislike " + totalDislikes;
					}
				}

			}
		}
	}else{
		int totalLikes = userLikesDao.countByVideoIdAndLikeStatus(videoId, true);
		int totalDislikes = userLikesDao.countByVideoIdAndLikeStatus(videoId, false);
		likeButton = "Like " + totalLikes;
		dislikeButton = "Dislike " + totalDislikes;
		
	}
		UserLikesController.Helper helper = new Helper(dislikeButton, likeButton);
		return helper;

	}

}
