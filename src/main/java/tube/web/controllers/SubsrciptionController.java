package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tube.entities.User;
import tube.entities.Video;
import tube.persistence.UserDAO;
import tube.persistence.VideoDAO;

@Controller
public class SubsrciptionController {

	private static final int SUBSCR_NEWEST_VIDEOS_SHOW = 10;

	@Autowired
	private UserDAO userDao;

	@Autowired
	private VideoDAO videoDao;

	@RequestMapping(value = "/subscribe", method = POST)
	public String subscribe(Principal principal, @RequestParam String username) {
		System.out.println(username);
		User subject = userDao.findByUsername(username);
		User subscriber = userDao.findByUsername(principal.getName());
		subscriber.getUserSubscriptions().add(subject);
		// subject.getSubscribers().add(subscriber);
		userDao.save(subscriber);
		// userDao.save(subject);
		return "redirect:/user/" + subject.getUsername();
	}

	@RequestMapping(value = "/subscribtions", method = GET)
	public String getSubscribe(Principal principal, Model model) {
		User subscriber = userDao.findByUsername(principal.getName());
		List<User> subscList = subscriber.getUserSubscriptions();
		List<Video> allSubscVideos = new ArrayList<Video>();
		for (User user : subscList) {
			allSubscVideos.addAll(user.getVideos());
		}
		allSubscVideos.sort((v1, v2) -> v2.getId() - v1.getId());
		int count = 0;
		List<Video> newestTenVideoSubscr = new ArrayList<Video>();
		for (Video video : allSubscVideos) {
			if (count >= SUBSCR_NEWEST_VIDEOS_SHOW) {
				break;
			}
			newestTenVideoSubscr.add(video);
			count++;
		}
		model.addAttribute("subscVideos", newestTenVideoSubscr);
		return "subscriptionFeed";
	}

	@RequestMapping(value = "/unsubscribe", method = POST)
	public String unsubscribe(Principal principal, @RequestParam String username) {
		System.out.println(username);
		User subject = userDao.findByUsername(username);
		User subscriber = userDao.findByUsername(principal.getName());
		subscriber.getUserSubscriptions().remove(subject);
		userDao.save(subscriber);
		return "redirect:/user/" + subject.getUsername();
	}

	@RequestMapping(value = "/subscriptions", method = GET)
	public String getSubscriptions() {
		return "subscriptions";
	}
}
