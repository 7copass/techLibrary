package com.techLead.library.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
		http.httpBasic().and().authorizeHttpRequests()
				.antMatchers(HttpMethod.POST, "/library/create-user").permitAll()
				.antMatchers(HttpMethod.POST, "/library/login").permitAll()
				.antMatchers(HttpMethod.GET, "/books/**").permitAll()
				.antMatchers(HttpMethod.POST, "/books/register-book/**").hasAnyRole("USER", "ADMIN")
				.antMatchers(HttpMethod.PUT, "/books/update/{id}/{idUser}").hasAnyRole("USER")
				.antMatchers(HttpMethod.PUT, "/books/update-any/{id}").hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/books/delete/{idBook}/{idUser}").hasRole("USER")
				.antMatchers(HttpMethod.DELETE, "/books/delete-one/any/{idBook}").hasRole("ADMIN")
				.anyRequest()
				.authenticated().and()
				.httpBasic().and()
				.csrf().disable();
		return http.build();
	} 
	
	

}
