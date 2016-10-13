package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tube.entities.Video;
import tube.persistence.VideoDAO;

@Controller
public class VideoController {
	
	@Autowired
	VideoDAO videoDao;
	
	@RequestMapping(value = "/video/{videoId}", method = GET)
	public String playVideo(Model model , @PathVariable("videoId") int videoId ){
		Video video = videoDao.findOne(videoId);
		Integer currentViews = video.getViews();
		video.setViews(currentViews == null ? 1 : ++currentViews);
		video = videoDao.save(video);
		model.addAttribute("video", video);
		return "video";
		
	}
	
	

}
