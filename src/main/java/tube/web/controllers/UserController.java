package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tube.entities.User;
import tube.entities.Video;
import tube.model.SubscribeButton;
import tube.persistence.PlaylistDAO;
import tube.persistence.UserDAO;
import tube.persistence.VideoDAO;
import tube.validations.UserValidation;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private VideoDAO videoDao;
	
	@Autowired
	private PlaylistDAO playlistDao;

	public UserController() {
	}

	@RequestMapping(value = "/login", method = GET)
	public String showLoginForm() {
		return "loginForm";
	}

	@RequestMapping(value = "/logout", method = GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/user/login?logout";
	}

	@RequestMapping(value = "/register", method = GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute(new User());
		return "registerForm";
	}

	@RequestMapping(value = "/register", method = POST)
	public String processRegistration(@Valid User user, Errors errors, Model model,
			@Autowired UserValidation userValidator, HttpServletRequest request, @Autowired BCryptPasswordEncoder passwordEncoder) {
		try {
			if (!userValidator.isUsernameAvailable(user.getUsername(), userDao)) {
				errors.rejectValue("username", null, "This username is already taken.");
			}
			if (!userValidator.isEmailAvailable(user.getEmail(), userDao)) {
				errors.rejectValue("email", null, "This email is already taken.");
			}
			if (errors.hasErrors()) {
				return "registerForm";
			}
			saveAndLogin(user, request, passwordEncoder);
			return "redirect:/user/" + user.getUsername();
		} catch (Exception e) {
			e.printStackTrace();
			return "registerForm";
		}
	}

	private void saveAndLogin(User user, HttpServletRequest request, BCryptPasswordEncoder passwordEncoder)
			throws ServletException {
		String origPass = user.getPassword();
		user.setPassword(passwordEncoder.encode(origPass));
		userDao.saveAndFlush(user);
		request.login(user.getUsername(), origPass);
	}

	@RequestMapping(value = "/me", method = GET)
	public String getMyProfile(Principal principal, Model model) {
		return "forward:/user/" + principal.getName();
	}

	@RequestMapping(value = "/{username}", method = GET)
	public String getProfile(@PathVariable String username, Model model, @Autowired SubscribeButton subsButton, Principal principal) {
		User subject = userDao.findByUsername(username);
			System.out.println("\tusername = " + username);
		List<Video> videos = videoDao.findByUserUsername(username);
		videos.sort((v1, v2) -> v2.getId() - v1.getId());
		model.addAttribute("videos", videos);
		model.addAttribute("user", subject);
			System.out.println("\tuserDao = " + userDao);
			System.out.println("\tsubject = " + subject);
			System.out.println("\tsubject ID = " + subject.getId());
			System.out.println("\tplaylists = " + playlistDao.findByUserId(subject.getId()));
		model.addAttribute("playlists", playlistDao.findByUserId(subject.getId()));
		model.addAttribute("subscribeButton", subsButton.getButtonType(subject, principal, userDao).getValue());
		return "profile";
	}
	
}
