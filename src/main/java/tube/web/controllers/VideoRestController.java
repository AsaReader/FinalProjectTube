package tube.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tube.entities.Video;
import tube.persistence.VideoDAO;

@RestController
public class VideoRestController {
	
	
	@Autowired
	VideoDAO videoDao;
	
	
	
	@RequestMapping(value = "/prefix/{text}",  method = RequestMethod.GET)
    public @ResponseBody List<Video> getVideo(@PathVariable("text") String query) {
        List<Video> videoList = videoDao.getVideosByInput(query);
//        for (Video video : videoList) {
//        	System.out.println(video.getTitle() + " filename : " + video.getFileName() + " tags: " + video.getTags() + " playlists : " + video.getPlaylists());
//		}
        return videoList;
    }
	
	@RequestMapping(value = "/title/{text}",  method = RequestMethod.GET)
    public @ResponseBody List<String> getVideoTitles(@PathVariable("text") String query) {
        List<Video> videoList = videoDao.getVideosByInput(query);
        List<String> titles = new ArrayList<String>();
        for (Video video : videoList) {
        titles.add(video.getTitle());
        }
        return titles;
    }
	
	@RequestMapping("/dislikes/{id}")
	public @ResponseBody Integer getLikes(@PathVariable("id") int videoId){
		return videoDao.getLikes(true, videoId);
	}
	
	@RequestMapping("/likes/{id}")
	public @ResponseBody Integer getDisLikes(@PathVariable("id") int videoId){
		return videoDao.getLikes(false,videoId);
	}

}
