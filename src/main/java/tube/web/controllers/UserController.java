package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tube.entities.User;
import tube.jdbc.UserJDBCTemplate;
import tube.persistence.UserDAO;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserDAO userDao;
	private ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	private UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");

	public UserController() {
	}

	@Autowired
	public UserController(UserDAO userDao) {
		this.userDao = userDao;
	}

	@RequestMapping(value = "/login", method = GET)
	public String showLoginForm() {
		return "loginForm";
	}

	@RequestMapping(value = "/login", method = POST)
	public String confirmLogin(@ModelAttribute User user) {
		//TODO add erroe message for wrong username or password
		User dbUser = userJDBCTemplate.login(user.getUsername());

		if (dbUser != null) {
			if (dbUser.getPassword().equals(user.getPassword())) {
				return "redirect:/user/" + dbUser.getUsername();
			} else {
				return "loginForm";
			}
		}
		return "loginForm";
	}

	@RequestMapping(value = "/register", method = GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute(new User());
		return "registerForm";
	}

	@RequestMapping(value = "/register", method = POST)
	public String processRegistration(@Valid User user, Errors errors) {
		if (errors.hasErrors()) {
			return "registerForm";
		}
		userDao.insert(user);
		// TODO password hashing algoritm
		int id = userJDBCTemplate.registerNewUser(user);
		user.setId(id);
		return "redirect:/user/" + user.getUsername();
	}

	@RequestMapping(value = "/{username}", method = GET)
	public String showSpitterProfile(@PathVariable String username, Model model) {
		User user = userJDBCTemplate.login(username);
		model.addAttribute(user);
		return "profile";
	}
}
