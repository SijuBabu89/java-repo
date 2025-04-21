package com.buddywindow.core.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.buddywindow.core.service.filters.AuthTokenFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    	
	@Autowired
	private AuthTokenFilter authTokenFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
				.authorizeHttpRequests(
						(requests) -> requests.requestMatchers("/api/auth/welcome-msg",
								"/api/auth/login", "/api/auth/token/authenticate", "/api/noti/welcome-msg", "/api/noti/send/email")
								.permitAll().anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer
						.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable())) // to make accessible h2
																							// console, it works as
																							// frame
				.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
						.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
				.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
