package tube.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import tube.entities.User;

public class SecurityUser implements UserDetails {

	private static final long serialVersionUID = 2790990792592755950L;

	private String username;
	private String password;
	private Integer id;
	private List<GrantedAuthority> authorities;
	
	public SecurityUser(User user, List<GrantedAuthority> authorities) {
        if(user != null)
        {
        	this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.authorities = authorities;
        }       
    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
