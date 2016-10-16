package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
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
@RequestMapping({ "/", "/home" })
public class HomeController {

	private static final int LIMIT_OF_NEW_VIDEOS_SHOW = 12;
	private VideoDAO videoDao;
	private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
	private MailMail mm = (MailMail) context.getBean("mailMail");

	@Autowired
	public HomeController(VideoDAO videoDao) {
		this.videoDao = videoDao;
	}

	@RequestMapping(method = GET)
	public String home(Model model) {
		try {
			List<Video> videosMostWatched = new ArrayList<Video>();
			// videosMostWatched.sort((v3, v4) -> v4.getViews() -
			// v3.getViews());
			videosMostWatched = videoDao.getMostWatchedVideos();
			model.addAttribute("videosMostWatched", videosMostWatched);

			List<Video> videosAll = videoDao.findAll();
			videosAll.sort((v1, v2) -> v2.getId() - v1.getId());
			int count = 0;
			List<Video> videosLast = new ArrayList<Video>();
			for (Video video : videosAll) {
				if (count >= LIMIT_OF_NEW_VIDEOS_SHOW) {
					break;
				}
				videosLast.add(video);
				count++;
			}
			model.addAttribute("videosLast", videosLast);
			return "home";
		} catch (Exception e) {

			mm.sendMail("youplayittalents@gmail.com", MailMail.EMAIL_RECEPIENT, "Catch an Exception",
					Throwables.getStackTraceAsString(e));

			return "redirect:/";
		}
	}
}
