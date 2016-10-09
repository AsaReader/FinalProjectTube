//package tube.config;
//
//import java.security.SecureRandom;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import tube.security.TubeUserService;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//  
//	private static final int ENCODER_STRENGTH = 31;
//
//	@Autowired
//	DataSource dataSource;
//	
//	@Autowired
//	TubeUserService userDetailsService;
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//    		.formLogin()
//    			.loginPage("/user/login")
//    			.and()
//    		.authorizeRequests()
//    			.antMatchers(HttpMethod.POST, "/upload").authenticated()
//    			.antMatchers("/user/me/edit").authenticated()
//    			.anyRequest().permitAll();
////      .formLogin()
////        .loginPage("/user/login")
////      .and()
////        .logout()
////          .logoutSuccessUrl("/")
////      .and()
////      .rememberMe()
////        .tokenRepository(new InMemoryTokenRepositoryImpl())
////        .tokenValiditySeconds(2419200)
////        .key("spittrKey")
////      .and()
////       .httpBasic()
////         .realmName("Spittr")
////      .and()
////      .authorizeRequests()
////        .antMatchers("/").authenticated()
////        .antMatchers("/spitter/me").authenticated()
////        .antMatchers(HttpMethod.POST, "/spittles").authenticated()
////        .anyRequest().permitAll();
//  }
//  
//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth
//      .userDetailsService(userDetailsService)
//      .passwordEncoder(new BCryptPasswordEncoder(ENCODER_STRENGTH, SecureRandom.getInstance("SHA1PRNG")));
//  }
//
//  
//}
//
