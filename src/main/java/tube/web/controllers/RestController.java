package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tube.entities.Video;

@Controller
public class RestController {
	
	@RequestMapping(value = "/restService", method = GET)
	public String openRest(){
		
		
		return "rest";
	}
	
	@RequestMapping(value = "/returnLink", method= POST)
	public @ResponseBody String getLink(@RequestParam("link") String link) {
		
		return link;
	}
	

}
