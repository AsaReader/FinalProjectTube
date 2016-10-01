package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_likes database table.
 * 
 */
@Entity
@Table(name="user_likes")
@NamedQuery(name="UserLike.findAll", query="SELECT u FROM UserLike u")
public class UserLike implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserLikePK id;

	private byte likeStatus;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to Video
	@ManyToOne
	private Video video;

	public UserLike() {
	}

	public UserLikePK getId() {
		return this.id;
	}

	public void setId(UserLikePK id) {
		this.id = id;
	}

	public byte getLikeStatus() {
		return this.likeStatus;
	}

	public void setLikeStatus(byte likeStatus) {
		this.likeStatus = likeStatus;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Video getVideo() {
		return this.video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

}