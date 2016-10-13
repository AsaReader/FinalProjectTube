package tube.entities;
// Generated Oct 6, 2016 4:59:27 PM by Hibernate Tools 5.2.0.Beta1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Playlist generated by hbm2java
 */
@Entity
@Table(name = "playlist", catalog = "youtubeDB")
public class Playlist implements java.io.Serializable {

	private Integer id;
	private User user;
	private Boolean isDeletable;
	private String name;
	private Set<Video> videos = new HashSet<Video>(0);

	public Playlist() {
	}

	public Playlist(User user, String name) {
		this.user = user;
		this.name = name;
	}

	public Playlist(User user, Boolean isDeletable, String name, Set<Video> videos) {
		this.user = user;
		this.isDeletable = isDeletable;
		this.name = name;
		this.videos = videos;
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
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "isDeletable")
	public Boolean getIsDeletable() {
		return this.isDeletable;
	}

	public void setIsDeletable(Boolean isDeletable) {
		this.isDeletable = isDeletable;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name = "playlist_videos", catalog = "youtubeDB", joinColumns = {
			@JoinColumn(name = "playlist_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "video_id", nullable = false, updatable = false) })
	public Set<Video> getVideos() {
		return this.videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}

}
