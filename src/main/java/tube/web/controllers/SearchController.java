package tube.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tube.entities.Video;
import tube.persistence.VideoDAO;

@Controller
public class SearchController {
	
	@Autowired
	VideoDAO videoDao;
	
	
	@RequestMapping("/search")
	public String getVideosByInput(Model model, String input){
		
		List<Video> videos = videoDao.getVideosByInput(input);
		model.addAttribute("videos", videos);
		System.err.println("text to search : " + input);
		
		
		return "results";
		
	}
	
	

}
