package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tube.entities.User;
import tube.entities.Video;
import tube.model.SubscribeButton;
import tube.model.SubscriptionType;
import tube.persistence.UserDAO;
import tube.persistence.VideoDAO;

@Controller
public class SubsrciptionController {

	private static final int SUBSCR_NEWEST_VIDEOS_SHOW = 9;

	@Autowired
	private UserDAO userDao;

	@Autowired
	private VideoDAO videoDao;

	@RequestMapping(value = "/subscribe", method = POST)
	public @ResponseBody String subscribe(Principal principal, @RequestParam String username, Model model, @Autowired SubscribeButton subsButton) {
		User subject = userDao.findByUsername(username);
		//get current subscription status
		SubscriptionType subType = subsButton.getButtonType(subject, principal, userDao);
		String afterSubResult = null;
		switch(subType) {
		//return unchanged if not logged in
		case UNLOGGED_SUBSCRIBE:
			afterSubResult = subType.getValue();
			break;
		//return opposite after change in subscription
		case LOGGED_SUBSCRIBE:
			subject.getSubscribers().add(userDao.findByUsername(principal.getName()));
			afterSubResult = SubscriptionType.UNSUBSCRIBE.getValue();
			break;
		case UNSUBSCRIBE:
			subject.getSubscribers().remove(userDao.findByUsername(principal.getName()));
			afterSubResult = SubscriptionType.LOGGED_SUBSCRIBE.getValue();
			break;
		}
		userDao.save(subject);
		return afterSubResult;
	}

	@RequestMapping(value = "/subscribtions", method = GET)
	public String getSubscribtions(Principal principal, Model model) {
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

}
