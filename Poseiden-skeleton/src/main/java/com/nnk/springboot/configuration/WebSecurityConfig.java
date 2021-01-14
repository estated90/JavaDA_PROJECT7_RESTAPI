package com.nnk.springboot.configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.web.context.WebApplicationContext;

import com.nnk.springboot.security.AuthenticationSuccessHandlerImpl;
import com.nnk.springboot.security.CustomLogoutHandler;
import com.nnk.springboot.security.CustomUserDetailsService;

/**
 * <p>Configuration of the security of the application</p>
 * @author nicol
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private WebApplicationContext applicationContext;
	private CustomUserDetailsService userDetailsService;
	@Autowired
	private AuthenticationSuccessHandlerImpl successHandler;
	@Autowired
	private DataSource dataSource;
    @Autowired
    private CustomLogoutHandler logoutHandler;

	@PostConstruct
	public void completeSetup() {
		userDetailsService = applicationContext.getBean(CustomUserDetailsService.class);
	}

	/**
	 * <p>Configuration of the http request and access</p>
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/user/**").access("hasAuthority('ADMIN')")
			.antMatchers("secure/article-details").hasAnyAuthority("ADMIN", "USER")
        	.anyRequest().authenticated()
				.and()
			.formLogin().permitAll()
				.successHandler(successHandler)
				.and()
			.logout()
            	.logoutSuccessHandler(logoutHandler)
            	.invalidateHttpSession(false)
	            .logoutUrl("/app-logout")
	            .logoutSuccessUrl("/")
	            .permitAll()
            .and()
            	.exceptionHandling().accessDeniedPage("/app/error")
			.and()
				.csrf();
	}

    /**
     * @param auth from the authentification builder
     * @throws Exception when no return from DB
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("select username,password,enabled from users where username = ?")
            .passwordEncoder(new BCryptPasswordEncoder())
            .authoritiesByUsernameQuery("select username, role from users where username = ?");
    }

	/**
	  * <p>Create exception to access Swagger</p>
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
				"/configuration/security", "/swagger-ui.html", "/webjars/**", "/actuator/**");
	}

	/**
	 * @return encrypted password
	 */
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * @return the authentification 
	 */
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	/**
	 * @return security evaluation
	 */
	@Bean
	public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
		return new SecurityEvaluationContextExtension();
	}

}
