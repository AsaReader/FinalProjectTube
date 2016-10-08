package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import net.sf.ehcache.util.FindBugsSuppressWarnings;
import tube.entities.User;
import tube.entities.Video;
import tube.persistence.UserDAO;
import tube.persistence.VideoDAO;
import tube.validations.UserValidation;

@Controller
@RequestMapping("/user")
@SessionAttributes("loggedUser")
public class UserController {

	private static final String ALABALA = new String("alabala");
	private UserDAO userDao;
//	private ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
//	private UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");

	public UserController() {
	}

	@Autowired
	public UserController(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	VideoDAO videoDAO;

	@RequestMapping(value = "/login", method = GET)
	public String showLoginForm() {
		return "loginForm";
	}

	
	
	@RequestMapping(value = "/login", method = POST)
	public String confirmLogin(@ModelAttribute User user, Model model) {
		//TODO add erroe message for wrong username or password
//		User dbUser = userJDBCTemplate.login(user.getUsername());
//
//		if (dbUser != null) {
////			if (dbUser.getPassword().equals(user.getPassword())) {
////				model.addAttribute("loggedUser", dbUser);
////				return "redirect:/user/" + dbUser.getUsername();
//			
//		
//			} else {
//				return "loginForm";
//			}
//		}
		return "loginForm";
	}

	@RequestMapping(value = "/register", method = GET)
	public String showRegistrationForm(Model model) {
		for (int i = 0; i < 5; i++) {
			System.out.println("username from find by username: " + userDao.findByEmail("alabala@abv.bg").getUsername());
			System.out.println("by username: " + userDao.findByUsername("kjhfdgjfbghvdhvbdsjhb2"));
			System.out.println("id 21 exists ; " + userDao.exists(21));
			System.out.println(userDao.findOne(3));
			System.out.println(userDao.countByEmail("hristo_angelov1234@abv.bg"));
			
			System.out.println("*****");
		}
		
		
		model.addAttribute(new User());
		return "registerForm";
	}

	@RequestMapping(value = "/register", method = POST)
	public String processRegistration(@Valid User user, Errors errors, Model model, @Autowired UserValidation userValidator) {
		if (!userValidator.isUsernameAvailable(user.getUsername(), userDao)) {
			errors.rejectValue("username", null, "This username is already taken.");
		}
		if (!userValidator.isEmailAvailable(user.getEmail(), userDao)) {
			errors.rejectValue("email", null, "This email is already taken.");
		}
		if (errors.hasErrors()) {
			return "registerForm";
		}
		
		
		System.out.println(user.getEmail() + "; username: "+ user.getUsername());
		user = userDao.saveAndFlush(user);
		System.out.println("USER:::::::" + user);
//		System.out.println("--------");
//		System.out.println(userDao.findByUsername(user.getUsername()));
//		System.out.println("after" + user.getEmail() + "; username: "+ user.getUsername());
////		System.out.println(userDao.findByEmail(user.getEmail()));
//		System.out.println(userDao.countByEmail(user.getEmail()));
//		System.out.println(userDao.countByUsername(user.getUsername()));
//		System.out.println(userDao.findOne(user.getId()));
//		System.out.println("--------");
		model.addAttribute("loggedUser", user);
		return "redirect:/user/" + user.getUsername();
	}

	

	@RequestMapping(value = "/{username}", method = GET)
	public String showUserProfile(@PathVariable String username, Model model) {
		
		System.out.println(username);
		String kkk = username;
		System.out.println(userDao.findByUsername("mincho"));
		
		System.out.println(userDao.findByUsername(kkk));
		User user = userDao.findByUsername(kkk);
		System.out.println(user);
		model.addAttribute(user);
		return "profile";
	}
}
