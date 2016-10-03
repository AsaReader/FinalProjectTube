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
import org.springframework.web.bind.annotation.SessionAttributes;

import tube.entities.User;
import tube.jdbc.UserJDBCTemplate;
import tube.persistence.UserDAO;

@Controller
@RequestMapping("/user")
@SessionAttributes("loggedUser")
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
	public String confirmLogin(@ModelAttribute User user, Model model) {
		//TODO add erroe message for wrong username or password
		User dbUser = userJDBCTemplate.login(user.getUsername());

		if (dbUser != null) {
			if (dbUser.getPassword().equals(user.getPassword())) {
				model.addAttribute("loggedUser", dbUser);
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
	public String processRegistration(@Valid User user, Errors errors, Model model) {
		if (errors.hasErrors()) {
			return "registerForm";
		}
		user = userDao.save(user);
		// TODO password hashing algoritm
//		int id = userJDBCTemplate.registerNewUser(user);
//		user.setId(id);
		model.addAttribute("loggedUser", user);
		return "redirect:/user/" + user.getUsername();
	}

	@RequestMapping(value = "/{username}", method = GET)
	public String showUserProfile(@PathVariable String username, Model model) {
		User user = userDao.findByUsername(username);
		model.addAttribute(user);
		return "profile";
	}
}
