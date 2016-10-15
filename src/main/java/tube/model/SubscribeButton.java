package tube.model;

import java.security.Principal;

import org.springframework.stereotype.Component;

import tube.entities.User;
import tube.persistence.UserDAO;

@Component
public class SubscribeButton {
	
	public SubscriptionType getButtonType(User subject, Principal principal, UserDAO userDao) {
		SubscriptionType subButton;
		if (principal == null) {
			subButton = SubscriptionType.UNLOGGED_SUBSCRIBE;
		} else {
			System.out.println(userDao);
			System.out.println(principal);
			User subscriber = userDao.findByUsername(principal.getName());
			if (subscriber.getUserSubscriptions().contains(subject)) {
				subButton = SubscriptionType.UNSUBSCRIBE;
			} else {
				subButton = SubscriptionType.LOGGED_SUBSCRIBE;
			}
		}
		return subButton;
	}

}
