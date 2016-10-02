package tube.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class User {

	private int id;
	
	@NotNull
	@Size(min=5, max=16, message="{username.size}")
	@Pattern(regexp="[a-zA-Z0-9_-]+", message="{username.pattern}")
	private String username;
	
	@NotNull
	@Pattern(regexp="([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*){2,}@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})", message="{email.valid}")
	private String email;
	
	@NotNull
	@Size(min=8, message="{password.size}")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", message="{password.pattern}")
	private String password;
	
	private boolean isBanned;
	private boolean isAdmin;

	public User() {
	}

	public User(String username, String email, String password) {
		this(0, username, email, password);
	}

	public User(int id, String username, String email, String password) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.isBanned = false;
		this.isAdmin = false;
	}

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "username", "password", "email");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "username", "password", "email");
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", isBanned=" + isBanned + ", isAdmin=" + isAdmin + "]";
	}

	
}
