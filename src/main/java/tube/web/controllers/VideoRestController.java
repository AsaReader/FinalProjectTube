package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tube.entities.Video;
import tube.model.SearchResultParser;
import tube.persistence.TagDAO;
import tube.persistence.UserDAO;
import tube.persistence.VideoDAO;

@RestController
public class VideoRestController {

	@Autowired
	VideoDAO videoDao;

	@Autowired
	UserDAO userDao;
	
	@Autowired
	TagDAO tagDao;
	
	@Autowired
	SearchResultParser resultParser;

	@RequestMapping(value = "/prefix/{text}", method = GET)
	public @ResponseBody List<Video> getVideo(@PathVariable("text") String query) {
		List<Video> videoList = videoDao.getVideosByInput(query);
		return videoList;
	}
	
	@RequestMapping(value = "/title/{text}", method = GET)
	public @ResponseBody List<String> getVideoTitles(@PathVariable("text") String query) {
		List<String> results = resultParser.getTitles(videoDao.getByInput(query));
		System.out.println(results);
		results.addAll(resultParser.getTagNames(tagDao.getTagsByInput(query)));
		System.out.println(results);
		results.addAll(resultParser.getUsernames(userDao.getUsersByInput(query)));
		System.out.println(results);
		return results;
	}
}
