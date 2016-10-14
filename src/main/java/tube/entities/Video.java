package tube.entities;
// Generated Oct 6, 2016 4:59:27 PM by Hibernate Tools 5.2.0.Beta1

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Videos generated by hbm2java
 */
@Entity
@Table(name = "videos", catalog = "youtubeDB", uniqueConstraints = @UniqueConstraint(columnNames = "file_name"))
public class Video implements java.io.Serializable {

	private static final long serialVersionUID = 8438340336511057600L;
	
	private Integer id;
	private User user;
	private LocalDate date;
	private String description;
	private String fileName;
	private String title;
	private Integer views;
	private Set<Playlist> playlists = new HashSet<Playlist>(0);
	private Set<Comment> comments = new HashSet<Comment>(0);
	private Set<Tag> tags = new HashSet<Tag>(0);
	private List<UserLikes> userLikes = new ArrayList<UserLikes>(0);

	public Video() {
	}

	public Video(User user, LocalDate date, String fileName, String title) {
		this.user = user;
		this.date = date;
		this.fileName = fileName;
		this.title = title;
	}

	public Video(User user, LocalDate date, String description, String fileName, String title, Set<Playlist> playlists,
			Set<Comment> comments, Set<Tag> tags, List<UserLikes> userLikes) {
		this.user = user;
		this.date = date;
		this.description = description;
		this.fileName = fileName;
		this.title = title;
		this.playlists = playlists;
		this.comments = comments;
		this.tags = tags;
		this.userLikes = userLikes;
	}

	public Video(String description, String fileName, String title, User user) {
		this.description = description;
		this.fileName = fileName;
		this.title = title;
		this.user = user;
	}

	public Video(String description, String fileName, String title, User user, Set<Tag> tags) {
		this.description = description;
		this.fileName = fileName;
		this.title = title;
		this.user = user;
		this.tags = tags;
	}
	
	public Video(User user, LocalDate date, String description, String fileName, String title) {
		this.user = user;
		this.date = date;
		this.description = description;
		this.fileName = fileName;
		this.title = title;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "date", nullable = false)
	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Column(name = "description", length = 150)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "file_name", unique = true, nullable = false, length = 100)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "title", nullable = false, length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "views")
	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinTable(name = "playlist_videos", catalog = "youtubeDB", joinColumns = {
			@JoinColumn(name = "video_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "playlist_id", nullable = false, updatable = false) })
	public Set<Playlist> getPlaylists() {
		return this.playlists;
	}

	public void setPlaylists(Set<Playlist> playlists) {
		this.playlists = playlists;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "video")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "video_has_tags", catalog = "youtubeDB", joinColumns = {
			@JoinColumn(name = "video_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "tags_id", nullable = false, updatable = false) })
	public Set<Tag> getTags() {
		return this.tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "video")
	public List<UserLikes> getUserLikes() {
		return this.userLikes;
	}

	public void setUserLikes(List<UserLikes> userLikes) {
		this.userLikes = userLikes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Video other = (Video) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
