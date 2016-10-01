package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the videos database table.
 * 
 */
@Entity
@Table(name="videos")
@NamedQuery(name="Video.findAll", query="SELECT v FROM Video v")
public class Video implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private Timestamp date;

	private String description;

	@Column(name="file_name")
	private String fileName;

	private String title;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="video")
	private List<Comment> comments;

	//bi-directional many-to-many association to Playlist
	@ManyToMany(mappedBy="videos")
	private List<Playlist> playlists;

	//bi-directional many-to-one association to UserLike
	@OneToMany(mappedBy="video")
	private List<UserLike> userLikes;

	//bi-directional many-to-many association to Tag
	@ManyToMany
	@JoinTable(
		name="video_has_tags"
		, joinColumns={
			@JoinColumn(name="video_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="tags_id")
			}
		)
	private List<Tag> tags;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public Video() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setVideo(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setVideo(null);

		return comment;
	}

	public List<Playlist> getPlaylists() {
		return this.playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public List<UserLike> getUserLikes() {
		return this.userLikes;
	}

	public void setUserLikes(List<UserLike> userLikes) {
		this.userLikes = userLikes;
	}

	public UserLike addUserLike(UserLike userLike) {
		getUserLikes().add(userLike);
		userLike.setVideo(this);

		return userLike;
	}

	public UserLike removeUserLike(UserLike userLike) {
		getUserLikes().remove(userLike);
		userLike.setVideo(null);

		return userLike;
	}

	public List<Tag> getTags() {
		return this.tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}