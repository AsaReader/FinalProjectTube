package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tube.entities.Comment;
import tube.entities.User;
import tube.persistence.CommentDAO;

@Controller
public class CommentsController {

	@Autowired
	CommentDAO commentDao;

	public static class CommentHolder {
		private String username;
		private String text;

		public CommentHolder(String username, String text) {
			super();
			this.username = username;
			this.text = text;
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

		public void setText(String text) {
			this.text = text;
		}

	}

	@RequestMapping(value = "/alabala/comment", method = POST)
	public @ResponseBody List<CommentsController.CommentHolder> getComments(@RequestParam("videoId") int videoId) {
		String username;
		String text;
		List<CommentsController.CommentHolder> commentsAndUsername = new ArrayList<CommentsController.CommentHolder>();

		List<Comment> comments = commentDao.findByVideoId(videoId);
		for (Comment comment : comments) {
			User user = comment.getUsers();
			username = user.getUsername();
			text = comment.getText();
			commentsAndUsername.add(new CommentHolder(username, text));
		}

		return commentsAndUsername;

	}

}
