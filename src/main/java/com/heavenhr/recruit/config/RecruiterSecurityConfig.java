package com.heavenhr.recruit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class RecruiterSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().
			antMatchers(HttpMethod.PUT, "api/v1/candidate/apply/**").hasRole("CANDIDATE").
			antMatchers("api/v1/**").hasRole("RECRUITER").
			antMatchers(HttpMethod.GET, "/swagger**").hasAnyRole("RECRUITER").
			and().httpBasic().and().
				sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();
		;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("recruiter").password("recruiter123").roles("RECRUITER").and().
									  withUser("candidate").password("candidate123").roles("CANDIDATE");
	}

}
