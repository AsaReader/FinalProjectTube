package tube.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tube.entities.Video;
import tube.mail.MailMail;
import tube.persistence.VideoDAO;

@Controller
public class SearchController {
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
	private MailMail mailSender = (MailMail) context.getBean("mailMail");

	@Autowired
	private VideoDAO videoDao;
	
	@RequestMapping("/search")
	public String getVideosByInput(Model model, String input) {
//		try {
			List<Video> videos = videoDao.getVideosByInput(input);
			model.addAttribute("videos", videos);
			List<String> sorts = new ArrayList<String>();
			sorts.add("Views");
			sorts.add("Date");
			model.addAttribute("sorts", sorts);
			return "results";
//		} catch (Exception e) {
//			return mailSender.handle(e);
//		}
	}
	@RequestMapping("/sort")
	public String sortVideos(Model model, String input, String sortBy) {
//		try {
			List<Video> videos = videoDao.getVideosByInput(input);
			List<String> sorts = new ArrayList<String>();
			sorts.add("Views");
			sorts.add("Date");
			if(sortBy.equals("Views")){
				videos.sort((v1,v2)->v2.getViews() - v1.getViews());
				sorts.sort((s1,s2)->s2.compareTo(s1));
				
			}else{
				videos.sort((v1,v2)->v2.getDate().compareTo(v1.getDate()));
				sorts.sort((s1,s2)->s1.compareTo(s2));
			}
			model.addAttribute("sorts", sorts);
			model.addAttribute("videos", videos);
			System.err.println("text to search : " + input);
			return "results";
			
//		} catch (Exception e) {
//			return mailSender.handle(e);
//		}
	}


}
