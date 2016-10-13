package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tube.entities.User;
import tube.persistence.UserDAO;

@Controller
public class SubsrciptionController {
	
	@Autowired
	private UserDAO userDao;

	@RequestMapping(value = "/subscribe", method = POST)
	public String subscribe(Principal principal, @RequestParam String username) {
		System.out.println(username);
		User subject = userDao.findByUsername(username);
		User subscriber = userDao.findByUsername(principal.getName());
		subscriber.getUserSubscriptions().add(subject);
//		subject.getSubscribers().add(subscriber);
		userDao.save(subscriber);
//		userDao.save(subject);
		return "redirect:/user/" + subject.getUsername();
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
