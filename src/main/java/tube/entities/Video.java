package tube.entities;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Video {

	private int id;
	private Timestamp date;
	
	@NotNull
	@Size(min=5, max=150, message="{username.size}")
	private String description;
	
	@NotNull
	@Size(min=5, max=100, message="{username.size}")
	private String fileName;
	
	@NotNull
	@Size(min=5, max=100, message="{username.size}")
	private String title;
	
	private Long userID;
	// private List<Comment> comments;
	// private List<Playlist> playlists;
	// private List<UserLike> userLikes;
	// private List<Tag> tags;
	// private User user;

	public Video(){
		
	}
	
	public Video(String description, String fileName, String title, Long userID) {
		this(0, description, fileName, title, userID);
	}
	
	public Video(int id, String description, String fileName, String title, Long userID) {
		this.id = id;
		this.description = description;
		this.fileName = fileName;
		this.title = title;
		this.userID = userID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

}
