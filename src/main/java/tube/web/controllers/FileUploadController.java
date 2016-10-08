package tube.web.controllers;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import tube.entities.User;
import tube.entities.Video;

import tube.model.FileBucket;
import tube.model.FileValidator;
import tube.model.MultiFileBucket;
import tube.model.MultiFileValidator;
import tube.persistence.UserDAO;

@Controller
@SessionAttributes("loggedUser")
public class FileUploadController {
	private static final int MAX_SIZE_FOR_UPLOAD = 524288000;
	private static final String VIDEO_MP4 = "video/mp4";
	private static String UPLOAD_LOCATION = "C:/mytemp/";
//	private ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	

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

	@RequestMapping(value = "/singleUpload", method = RequestMethod.GET)
	public String getSingleUploadPage(ModelMap model) {
		FileBucket fileModel = new FileBucket();
		model.addAttribute("fileBucket", fileModel);
		return "singleFileUploader";
	}

	@RequestMapping(value = "/singleUpload", method = RequestMethod.POST)
	public String singleFileUpload(@Valid FileBucket fileBucket, BindingResult result, ModelMap model,
			@ModelAttribute("loggedUser") User loggedUser, HttpServletRequest request) throws IOException {
		
		//TODO error mesage on jsp to validate Parameters!!!!!!!!!!!!!!!!!!!!
		String title = request.getParameter("title");
		String descr = request.getParameter("descr");

		if (result.hasErrors()) {
			System.out.println("validation errors");
			return "singleFileUploader";
		}
		
		System.out.println("Fetching file");
		MultipartFile multipartFile = fileBucket.getFile();
		String fileName = loggedUser.getUsername() + LocalDateTime.now().toString().replace(":", "-") + ".mp4";

		//TODO error message for wrong type and over size file
		String ext = fileBucket.getFile().getContentType();
		if (!VIDEO_MP4.equals(ext) || fileBucket.getFile().getSize()>MAX_SIZE_FOR_UPLOAD) {
			return "singleFileUploader";
		}
		
		//Please dont use it for now!!!!!!!!!!
//		String url = null;
//		try {
//			url = S3JavaSDK.uploadFileToS3AWS(fileName, multipartFile);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		//copy file to computer
		FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + fileName));
		// fileBucket.getFile().getOriginalFilename())
		
		//Copy file to AWS - S3
		// String fileName = multipartFile.getOriginalFilename();


		title = title.trim();
		descr = descr.trim();
		if(title.isEmpty() || title==null){
			title = loggedUser.getUsername() + " " + LocalDateTime.now().toString();
		}
		if(descr.isEmpty() || descr==null){
			descr = loggedUser.getUsername() + " " + LocalDateTime.now().toString();
		}
		
		int userID = loggedUser.getId();
		//using copy to PC
		Video video = new Video(descr, fileName, title, userDAO.findOne(userID));
	
		//using copy to AWS - S3
//		Video video = new Video(descr, url, title, userID);
		
		//TODO change it with hibernate
//		int id = videoJDBCTemplate.addVideo(video);
//		video.setId(id);

		model.addAttribute("fileName", fileName);
		return "success";
	}

	@RequestMapping(value = "/multiUpload", method = RequestMethod.GET)
	public String getMultiUploadPage(ModelMap model) {
		MultiFileBucket filesModel = new MultiFileBucket();
		model.addAttribute("multiFileBucket", filesModel);
		return "multiFileUploader";
	}

	@RequestMapping(value = "/multiUpload", method = RequestMethod.POST)
	public String multiFileUpload(@Valid MultiFileBucket multiFileBucket, BindingResult result, ModelMap model)
			throws IOException {

		if (result.hasErrors()) {
			System.out.println("validation errors in multi upload");
			return "multiFileUploader";
		} else {
			System.out.println("Fetching files");
			List<String> fileNames = new ArrayList<String>();

			// Now do something with file...
			for (FileBucket bucket : multiFileBucket.getFiles()) {
				FileCopyUtils.copy(bucket.getFile().getBytes(),
						new File(UPLOAD_LOCATION + bucket.getFile().getOriginalFilename()));
				fileNames.add(bucket.getFile().getOriginalFilename());
			}

			model.addAttribute("fileNames", fileNames);
			return "multiSuccess";
		}
	}
}
