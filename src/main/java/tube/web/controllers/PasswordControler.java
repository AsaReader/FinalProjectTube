package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.base.Throwables;

import javassist.bytecode.stackmap.BasicBlock.Catch;
import tube.entities.User;
import tube.mail.MailMail;
import tube.mail.PasswordGenerator;
import tube.persistence.UserDAO;

@Controller
public class PasswordControler {

	private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
	MailMail mm = (MailMail) context.getBean("mailMail");

	@Autowired
	UserDAO userDAO;

	@RequestMapping(value = "/forgotenpassword", method = GET)
	public String showForgotenPassword() {
		return "forgotenPassword";
	}

	@RequestMapping(value = "/passwordForgotten", method = RequestMethod.POST)
	public String passwordForgotten(@RequestParam("username") String username, ModelMap model,
			@Autowired BCryptPasswordEncoder passwordEncoder) {
		try {
			User userTemp = userDAO.findByUsername(username);

			if (userTemp == null) {
				String errors = "This username is invalid.";
				model.addAttribute("errors", errors);
				return "forgotenPassword";
			}

			String newPassword = PasswordGenerator.getRandomPassword();
			String send = "Your new password for YouPlay is: " + newPassword;
			System.out.println(send);
			
			mm.sendMail("youplayittalents@gmail.com", userTemp.getEmail(), "Change Password in YouPlay",send);
			String criptPass = passwordEncoder.encode(newPassword);
			userTemp.setPassword(criptPass);
			userDAO.saveAndFlush(userTemp);
			
			StringBuilder sb = new StringBuilder();
			sb.append("Password for ").append(userTemp.getUsername());
			sb.append(" was changed. Please check your e-mail!");
			
			model.addAttribute("success", sb);

			return "forgotenPassword";

		} catch (Exception e) {
			mm.sendMail("youplayittalents@gmail.com", MailMail.EMAIL_RECEPIENT, "Catch an Exception",
					Throwables.getStackTraceAsString(e));
			return "redirect:/";
		}
	}
}
