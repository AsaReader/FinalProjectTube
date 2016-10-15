package tube.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Throwables;

import tube.entities.Video;
import tube.mail.MailMail;
import tube.persistence.VideoDAO;

@Controller
public class SearchController {
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
	MailMail mm = (MailMail) context.getBean("mailMail");

	@Autowired
	VideoDAO videoDao;

	@RequestMapping("/search")
	public String getVideosByInput(Model model, String input) {
		try {
			List<Video> videos = videoDao.getVideosByInput(input);
			model.addAttribute("videos", videos);
			System.err.println("text to search : " + input);

			return "results";
			
		} catch (Exception e) {
			
			mm.sendMail("youplayittalents@gmail.com", MailMail.EMAIL_RECEPIENT, "Catch an Exception",
					Throwables.getStackTraceAsString(e));

			return "redirect:/";
		}
	}

}
