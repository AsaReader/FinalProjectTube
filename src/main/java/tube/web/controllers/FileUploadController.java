package tube.web.controllers;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import tube.entities.Tag;
import tube.entities.User;
import tube.entities.Video;
import tube.mail.MailMail;
import tube.model.FileBucket;
import tube.model.FileValidator;
import tube.model.MultiFileValidator;
import tube.persistence.TagDAO;
import tube.persistence.UserDAO;
import tube.persistence.VideoDAO;
import tube.s3.S3JavaSDK;

@Controller
public class FileUploadController {
	private static final int MAX_SIZE_FOR_UPLOAD = 524288000;
	private static final String VIDEO_MP4 = "video/mp4";
	private VideoDAO videoDao;
	private TagDAO tagDao;
	private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
	MailMail mailSender = (MailMail) context.getBean("mailMail");

	@Autowired
	public FileUploadController(VideoDAO videoDao, TagDAO tagDao) {
		this.videoDao = videoDao;
		this.tagDao = tagDao;
	}

	@Autowired
	FileValidator fileValidator;

	@Autowired
	UserDAO userDAO;

	@Autowired
	MultiFileValidator multiFileValidator;

	@InitBinder("fileBucket")
	protected void initBinderFileBucket(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

	@InitBinder("multiFileBucket")
	protected void initBinderMultiFileBucket(WebDataBinder binder) {
		binder.setValidator(multiFileValidator);
	}

	@RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
	public String getHomePage(ModelMap model) {
		return "welcome";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String getuploadPage(ModelMap model) {
		FileBucket fileModel = new FileBucket();
		model.addAttribute("fileBucket", fileModel);
		return "singleFileUploader";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String singleFileUpload(@Valid FileBucket fileBucket, BindingResult result, ModelMap model,
			HttpServletRequest request, Principal principal) throws IOException {
		try {
			User loggedUser = userDAO.findByUsername(principal.getName());

			String title = request.getParameter("title");
			String descr = request.getParameter("descr");
			String tags = request.getParameter("tags");

			if (result.hasErrors()) {
				System.out.println("validation errors");
				return "singleFileUploader";
			}

			System.out.println("Fetching file");
			MultipartFile multipartFile = fileBucket.getFile();
			String fileName = loggedUser.getUsername() + LocalDateTime.now().toString().replace(":", "-") + ".mp4";

			// TODO error message for wrong type and over size file
			String ext = fileBucket.getFile().getContentType();
			if (!VIDEO_MP4.equals(ext) || fileBucket.getFile().getSize() > MAX_SIZE_FOR_UPLOAD) {
				return "singleFileUploader";
			}

			String url = null;
			try {
				url = S3JavaSDK.uploadFileToS3AWS(fileName, multipartFile);
			} catch (Exception e) {
				return mailSender.handle(e);
			}

			// Copy file to AWS - S3
			title = title.trim();
			descr = descr.trim();
			tags = tags.trim();
			if (title.isEmpty() || title == null) {
				title = loggedUser.getUsername() + " " + LocalDateTime.now().toString();
			}
			if (descr == null) {
				descr = "";
			}
			if (tags == null) {
				descr = "";
			}

			// add video Tags
			List<String> tagsList = Arrays.asList(tags.split(","));
			List<Tag> tagSet = new ArrayList<Tag>();

			for (String tagStr : tagsList) {
				if (!tagStr.trim().isEmpty()) {
					Tag tag = new Tag(tagStr.trim());
					try {
						tag = tagDao.save(tag);
					} catch (Exception e) {
						tag = tagDao.findByName(tagStr.trim());
					}
					tagSet.add(tag);
				}
			}

			// using copy to AWS - S3
			Video video = new Video(descr, url, title, loggedUser);

			video.setTags(tagSet);
			video = videoDao.saveAndFlush(video);
			// int id = videoJDBCTemplate.addVideo(video);
			// video.setId(id);

			model.addAttribute("fileName", fileName);
			return "redirect:/video/" + video.getId();
		} catch (Exception e) {
			return mailSender.handle(e);
		}
	}
}
