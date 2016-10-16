package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tube.entities.Video;
import tube.persistence.UserDAO;
import tube.persistence.VideoDAO;

@RestController
public class VideoRestController {

	@Autowired
	VideoDAO videoDao;

	@Autowired
	UserDAO userDao;

	@RequestMapping(value = "/prefix/{text}", method = GET)
	public @ResponseBody List<Video> getVideo(@PathVariable("text") String query) {
		List<Video> videoList = videoDao.getVideosByInput(query);
		return videoList;
	}
	
	

	@RequestMapping(value = "/title/{text}", method = GET)
	public @ResponseBody List<String> getVideoTitles(@PathVariable("text") String query) {
		List<Video> videoList = videoDao.getByTitle(query);
		List<String> titles = new ArrayList<String>();
		for (Video video : videoList) {
			titles.add(video.getTitle());
			System.out.println(query);
			System.out.println(video.getTitle());
		}
		return titles;
	}

}
