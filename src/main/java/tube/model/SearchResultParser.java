package tube.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import tube.entities.Tag;
import tube.entities.User;
import tube.entities.Video;

@Service
public class SearchResultParser {

	public List<String> getTitles(List<Video> videos) {
		List<String> titles = new ArrayList<>();
		for (Video video : videos) {
			titles.add(video.getTitle());
		}
		return titles;
	}

	public List<String> getTagNames(List<Tag> tags) {
		List<String> tagNames = new ArrayList<>();
		for (Tag tag : tags) {
			tagNames.add(tag.getName());
		}
		return tagNames;
	}

	public List<String> getUsernames(List<User> users) {
		List<String> usernames = new ArrayList<>();
		for (User user : users) {
			usernames.add(user.getUsername());
		}
		return usernames;
	}

}
