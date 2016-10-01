package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String email;

	private byte isAdmin;

	private byte isBanned;

	private String password;

	private String username;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="user")
	private List<Comment> comments;

	//bi-directional many-to-one association to Playlist
	@OneToMany(mappedBy="user")
	private List<Playlist> playlists;

	//bi-directional many-to-one association to UserLike
	@OneToMany(mappedBy="user")
	private List<UserLike> userLikes;

	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="subscribers"
		, joinColumns={
			@JoinColumn(name="subscribe_user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="user_id")
			}
		)
	private List<User> users1;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="users1")
	private List<User> users2;

	//bi-directional many-to-one association to Video
	@OneToMany(mappedBy="user")
	private List<Video> videos;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(byte isAdmin) {
		this.isAdmin = isAdmin;
	}

	public byte getIsBanned() {
		return this.isBanned;
	}

	public void setIsBanned(byte isBanned) {
		this.isBanned = isBanned;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setUser(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setUser(null);

		return comment;
	}

	public List<Playlist> getPlaylists() {
		return this.playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public Playlist addPlaylist(Playlist playlist) {
		getPlaylists().add(playlist);
		playlist.setUser(this);

		return playlist;
	}

	public Playlist removePlaylist(Playlist playlist) {
		getPlaylists().remove(playlist);
		playlist.setUser(null);

		return playlist;
	}

	public List<UserLike> getUserLikes() {
		return this.userLikes;
	}

	public void setUserLikes(List<UserLike> userLikes) {
		this.userLikes = userLikes;
	}

	public UserLike addUserLike(UserLike userLike) {
		getUserLikes().add(userLike);
		userLike.setUser(this);

		return userLike;
	}

	public UserLike removeUserLike(UserLike userLike) {
		getUserLikes().remove(userLike);
		userLike.setUser(null);

		return userLike;
	}

	public List<User> getUsers1() {
		return this.users1;
	}

	public void setUsers1(List<User> users1) {
		this.users1 = users1;
	}

	public List<User> getUsers2() {
		return this.users2;
	}

	public void setUsers2(List<User> users2) {
		this.users2 = users2;
	}

	public List<Video> getVideos() {
		return this.videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public Video addVideo(Video video) {
		getVideos().add(video);
		video.setUser(this);

		return video;
	}

	public Video removeVideo(Video video) {
		getVideos().remove(video);
		video.setUser(null);

		return video;
	}

}