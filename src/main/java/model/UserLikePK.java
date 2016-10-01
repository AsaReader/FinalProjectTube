package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the user_likes database table.
 * 
 */
@Embeddable
public class UserLikePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id", insertable=false, updatable=false)
	private int userId;

	@Column(name="video_id", insertable=false, updatable=false)
	private int videoId;

	public UserLikePK() {
	}
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getVideoId() {
		return this.videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserLikePK)) {
			return false;
		}
		UserLikePK castOther = (UserLikePK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.videoId == castOther.videoId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.videoId;
		
		return hash;
	}
}