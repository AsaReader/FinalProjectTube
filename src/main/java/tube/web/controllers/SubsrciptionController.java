package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SubsrciptionController {

	@RequestMapping(value = "/subscribe", method = POST)
	public String subscribe(String subjectName) {
		return "redirect:/user/" + subjectName;
	}
	
	@RequestMapping(value = "/unsubscribe", method = POST)
	public String unsubscribe(String subjectName) {
		return "redirect:/user/" + subjectName;
	}
}
