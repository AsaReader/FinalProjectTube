package tube.entities;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Email;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class User {

	private long id;
	
	@NotNull
	@Size(min=5, max=16, message="{username.size}")
	@Pattern(regexp="[a-zA-Z0-9_-]+", message="{username.pattern}")
	private String username;
	
	@NotNull
	@Pattern(regexp="([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*){2,}@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})", message="{email.valid}")
	private String email;
	
	@NotNull
	@Size(min=5, max=25, message="{password.size}")
	private String password;

	public User() {
	}

	public User(String username, String email, String password) {
		this(0, username, email, password);
	}

	public User(long id, String username, String email, String password) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "username", "password", "email");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "username", "password", "email");
	}

}
