package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import tube.entities.User;
import tube.persistence.UserDAO;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserDAO userDao;

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
		return "redirect:/user/" + user.getUsername();
	}

	@RequestMapping(value = "/{username}", method = GET)
	public String showSpitterProfile(@PathVariable String username, Model model) {
		User user = userDao.selectByUsername(username);
		model.addAttribute(user);
		return "profile";
	}
}
