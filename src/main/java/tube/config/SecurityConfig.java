package tube.config;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tube.security.TubeUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final int FOUR_WEEKS = 2419200;
	private static final int ENCODER_STRENGTH = 12;

	@Autowired
	TubeUserService userDetailsService;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.formLogin()
				.loginPage("/user/login")
				.defaultSuccessUrl("/", true)
			.and()
			.logout()
				.logoutSuccessUrl("/")
				.logoutUrl("/user/logout")
			.and()
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/subscribe").authenticated()
				.antMatchers("/video/like").authenticated()
				.antMatchers("/upload").authenticated()
				.antMatchers("/user/login", "/user/register").anonymous()
				.antMatchers("/user/me").authenticated()
				.anyRequest().permitAll()
			.and()
			.rememberMe()
				.tokenValiditySeconds(FOUR_WEEKS);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		try {
			return new BCryptPasswordEncoder(ENCODER_STRENGTH, SecureRandom.getInstance("SHA1PRNG"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println(
					"Failed to instantiate specified BCryptPasswordEncoder, reverting to deafult BCryptPasswordEncoder.");
			return new BCryptPasswordEncoder();
		}
	}
}
