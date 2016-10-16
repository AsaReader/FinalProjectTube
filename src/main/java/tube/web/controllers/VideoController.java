package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tube.entities.Playlist;
import tube.entities.User;
import tube.entities.UserLikes;
import tube.entities.UserLikesId;
import tube.entities.Video;
import tube.mail.MailMail;
import tube.persistence.PlaylistDAO;
import tube.persistence.TagDAO;
import tube.persistence.UserDAO;
import tube.persistence.UserLikesDAO;
import tube.persistence.VideoDAO;
import tube.security.SecurityUser;
import tube.web.controllers.UserLikesController.Helper;

@Controller
public class VideoController {

	private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
	private MailMail mailSender = (MailMail) context.getBean("mailMail");

	@Autowired
	VideoDAO videoDao;

	@Autowired
	private UserDAO userDao;

	@Autowired
	PlaylistDAO playlistDao;
	
	@Autowired
	private UserLikesDAO userLikesDao;
	
	@Autowired
	private TagDAO tagDao;

	@RequestMapping(value = "/video/{videoId}", method = GET)
	public String playVideo(Model model, Principal principal, @PathVariable("videoId") int videoId) {
		try {
			Video video = videoDao.findOne(videoId);
			Integer currentViews = video.getViews();
			video.setViews(currentViews == null ? 1 : ++currentViews);
			video = videoDao.save(video);
			model.addAttribute("video", video);

			String likeButton;
			String dislikeButton;
			if (principal != null) {
				String username = principal.getName();

				User user = userDao.findByUsername(username);
				int userId = user.getId();

				int unlikesCount;
				int likesCount;
				int beforeUnlikes;
				int beforeLikes;
				UserLikes userunlikes = new UserLikes(new UserLikesId(userId, videoId), user, video, false);
				UserLikes userlikes = new UserLikes(new UserLikesId(userId, videoId), user, video, true);

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

			} else {
				int totalLikes = userLikesDao.countByVideoIdAndLikeStatus(videoId, true);
				int totalDislikes = userLikesDao.countByVideoIdAndLikeStatus(videoId, false);
				likeButton = "Like " + totalLikes;
				dislikeButton = "Dislike " + totalDislikes;

			}
			if (principal!=null){
			SecurityUser user = (SecurityUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			List<Playlist> playlists = playlistDao.findByUserId(user.getId());
			model.addAttribute("playlists", playlists);
			}
			UserLikesController.Helper helper = new Helper(dislikeButton, likeButton);
			model.addAttribute("likesHelper", helper);
			model.addAttribute("tags", tagDao.findByVideoId(video.getId()));
			return "video";

		} catch (Exception e) {
			model.addAttribute("missing", "video");
			return mailSender.handle(e);
		}
	}
	
	
	

	@RequestMapping(value = "/videoDelete", method = POST)
	protected void doDelete(@RequestParam("videoId") int videoId, Principal principal, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			Video video = videoDao.findOne(videoId);
			video.setTags(null);
			video.setPlaylists(null);
			if (principal !=null && video.getUser().equals(userDao.findByUsername(principal.getName()))) {			
			videoDao.delete(video);
			resp.setStatus(200);
			}
		} catch (Exception e) {
			mailSender.handle(e);
		}
	}

}
