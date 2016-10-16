package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tube.entities.Comment;
import tube.entities.User;
import tube.entities.Video;
import tube.mail.MailMail;
import tube.persistence.CommentDAO;
import tube.persistence.UserDAO;
import tube.persistence.VideoDAO;

@Controller
@RestController
public class CommentsController {

	@Autowired
	CommentDAO commentDao;

	@Autowired
	UserDAO userDao;

	@Autowired
	VideoDAO videoDao;
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
	private MailMail mailSender = (MailMail) context.getBean("mailMail");

	public static class CommentHolder {
		private String username;
		private String text;
		private int commentUserId;
		private int videoUserId;
		private int currentUserId;
		private int id;

		public CommentHolder(String username, String text, int commentUserId, int id, int videoUserId, int currentUserId) {
			super();
			this.username = username;
			this.text = text;
			this.commentUserId = commentUserId;
			this.id = id;
			this.currentUserId = currentUserId;
			this.videoUserId = videoUserId;
		}

		public int getVideoUserId() {
			return videoUserId;
		}

		public void setVideoUserId(int videoUserId) {
			this.videoUserId = videoUserId;
		}

		public int getCurrentUserId() {
			return currentUserId;
		}

		public void setCurrentUserId(int currentUserId) {
			this.currentUserId = currentUserId;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getText() {
			return text;
		}

		public int getCommentUserId() {
			return commentUserId;
		}

		public void setCommentUserId(int commentUserId) {
			this.commentUserId = commentUserId;
		}

		public void setText(String text) {
			this.text = text;
		}

	}

	@RequestMapping(value = "/video/comment", method = POST)
	public @ResponseBody List<CommentsController.CommentHolder> getComments(Principal principal,
			@RequestParam("videoId") int videoId, @RequestParam("comment") String commentText) {
		try{
		String username;
		String text;
	

		String loggedUsername = principal.getName();
		User user = userDao.findByUsername(loggedUsername);
		int currentUserId = user.getId();
		Video video = videoDao.getOne(videoId);

		
		User videoUser = userDao.getUserByVideoId(videoId);
		int videoUserId = videoUser.getId();

		Comment comment = new Comment(user, video, commentText);
		if (commentText.length() != 0) {
			commentDao.save(comment);
		}

		List<CommentsController.CommentHolder> commentsAndUsername = new ArrayList<CommentsController.CommentHolder>();
	
		List<Comment> comments = commentDao.findByVideoId(videoId);
		comments.sort((c1, c2) -> c2.getId() - c1.getId());
	
		for (Comment com : comments) {
			if (com.getText().length() == 0) {
				continue;
			}
		
			User comentator = com.getUsers();

		
			username = comentator.getUsername();
			text = com.getText();
			int commentUserId = comentator.getId();
			int commentId = com.getId();
			commentsAndUsername.add(new CommentHolder(username, text, commentUserId, commentId, videoUserId, currentUserId));
		}

		return commentsAndUsername;
		
		} catch (Exception e) {
			mailSender.handle(e);
			return null;
		}
	}

	@RequestMapping(value = "/video/delete", method = POST)
	public void deleteComment(@RequestParam("id") int id) {
		System.out.println(id);	
		commentDao.delete(id);		
	}

}