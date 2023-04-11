package com.ty.blogmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ty.blogmanagement.filter.SecurityFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UnAuthorizedUserAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private SecurityFilter securityFilter;

	// flow 2nd step
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {

		System.err.println("flow --AM--003");
		AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
		return authenticationManagerBuilder.build();
	}

	//// flow 2nd step

	@Bean
	AuthenticationProvider authenticationProvider() {
		System.err.println("flow --AP--002");
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
		return daoAuthenticationProvider;

	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		System.err.println("flow SECURITY FILTER CHAIN--004");
		httpSecurity.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/user", "/login").permitAll()
				.requestMatchers("/users").hasAuthority("ADMIN")
				.requestMatchers("/user/*").hasAuthority("ADMIN")
				.requestMatchers("/user/*/post","/user/*/post/*","/user/*/post/*/postComment/*", "/user/*/post/*/tag/*",
						"/user/*/post/*/category/*")
				.hasAuthority("USER")
				.and().httpBasic()
				.and()
				
				.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint)
				.and()
				
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				// To Verify user from second request onwards............
				.and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.authenticationProvider(authenticationProvider());

		return httpSecurity.build();
	}

	

}
