package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tube.entities.User;
import tube.entities.UserLikes;
import tube.entities.Video;
import tube.persistence.UserDAO;
import tube.persistence.UserLikesDAO;
import tube.persistence.VideoDAO;

@Controller
public class UserLikesController {
	
	@Autowired
	VideoDAO videoDao;

	@Autowired
	UserDAO userDao;
	
	@Autowired
	UserLikesDAO userLikesDao;
	
	@RequestMapping(value = "/like", method = POST)
	public @ResponseBody int getLikes(@RequestParam("username") String username,
			@RequestParam("videoId") int videoId) {
		
		User user = userDao.findByUsername(username);
	
		Video video = videoDao.findOne(videoId);
		UserLikes userlikes = new UserLikes(user, video, true);
		userLikesDao.saveAndFlush(userlikes);
		
		
		
		return userLikesDao.countByVideoIdAndLikeStatus(videoId, true);
	}
	
	@RequestMapping(value = "/dislike", method = POST)
	public @ResponseBody int getDisLikes(@RequestParam("username") String username,
			@RequestParam("videoId") int videoId) {
		
		User user = userDao.findByUsername(username);
		
		Video video = videoDao.findOne(videoId);
		UserLikes userlikes = new UserLikes(user, video, false);
		userLikesDao.saveAndFlush(userlikes);
		
		
		
		return userLikesDao.countByVideoIdAndLikeStatus(videoId, false);
	}
	
	
	

	

}
