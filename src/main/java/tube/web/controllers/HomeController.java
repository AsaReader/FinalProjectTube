package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tube.entities.Video;
import tube.persistence.VideoDAO;

@Controller
@RequestMapping({"/", "/home"})
public class HomeController {
	
	private VideoDAO videoDao;
	
	@Autowired
	public HomeController(VideoDAO videoDao) {
		this.videoDao = videoDao;
	}

	@RequestMapping(method = GET)
	public String home(Model model) {
		List<Video> videos = videoDao.getLastVideos();
		model.addAttribute("videos", videos);
		return "home";
	}
}
