package ma.inetum.brique;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import ma.inetum.brique.services.AutenticationSuccess;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {
	//
	@Autowired
	AutenticationSuccess autenticationSuccess ;
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private DataSource dataSource;
	//
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	//
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

//		 Setting Service to find User in the database.
//		 And Setting PassswordEncoder
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

	}
	//	@Bean
//    public UserDetailsService userDetailsService() {
//        return new CustomUserDetailsService();
//    }
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	//
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests().antMatchers(

				"/assets1/**", "/registration", "/login", "/logout").permitAll();

		http.authorizeRequests().anyRequest().authenticated();

		http.authorizeRequests().and().formLogin()//

				// Submit URL of login page.

				// .loginProcessingUrl("/login") // Submit URL

				.successHandler( autenticationSuccess )//.defaultSuccessUrl("/home")

				.loginPage("/login")

				.failureUrl("/public/login?error=true")//

				.permitAll()

				// .usernameParameter("username")//

				// .passwordParameter("password")

				//Config for Logout Page

				.and()

				.logout().logoutUrl("/logout").logoutSuccessUrl("/login");
//
//		http
//		.csrf().disable()
//		.authorizeRequests()
//            .antMatchers("/assets1/**", "/registration", "/login").permitAll()
//            .anyRequest().authenticated()
//            .and()
//        .formLogin().loginPage("/login").loginProcessingUrl("/login")
//          .successHandler(autenticationSuccess).defaultSuccessUrl("/home")
//           .and()
//        .logout().logoutUrl("/logout") .logoutSuccessUrl("/login")
//            .permitAll()
//            ;
		http.sessionManagement().maximumSessions(1);
//		http.csrf().disable();
//
//		// The pages does not require login
//		http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
//
//		// /userInfo page requires login as ROLE_USER or ROLE_ADMIN.
//		// If no login, it will redirect to /login page.
////		http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
//
//		// For ADMIN only.
////		http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
//
//		// When the user has logged in as XX.
//		// But access a page that requires role YY,
//		// AccessDeniedException will be thrown.
//		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
//
//		// Config for Login Form
////		http.authorizeRequests().and().formLogin()//
////				// Submit URL of login page.
////				.loginProcessingUrl("/j_spring_security_check") // Submit URL
////				.loginPage("/login")//
////				.defaultSuccessUrl("/")//
////				.failureUrl("/login?error=true")//
////				.usernameParameter("username")//
////				.passwordParameter("password")
////				// Config for Logout Page
////				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
//		http.authorizeRequests().antMatchers("/","/assets/**", "/resources/**","/resources/public/**").permitAll();
//		//http.authorizeRequests().anyRequest().authenticated();
//
//		http.authorizeRequests().and().formLogin()//
//		// Submit URL of login page.
//		//.loginProcessingUrl("/login") // Submit URL
//		.loginPage("/login")//
//		.defaultSuccessUrl("/user")//
//		.failureUrl("/login?error=true")//
//		.usernameParameter("username")//
//		.passwordParameter("password")
//		// Config for Logout Page
//		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");

		// Config Remember Me.
//		http.authorizeRequests().and() //
//				.rememberMe().tokenRepository(this.persistentTokenRepository()) //
//				.tokenValiditySeconds(1 * 24 * 60 * 60); // 24h

	}
	// Token stored in Table (Persistent_Logins)
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(this.dataSource);
		return db;
	}

}