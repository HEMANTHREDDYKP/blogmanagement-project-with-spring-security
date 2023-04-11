package com.ty.blogmanagement.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ty.blogmanagement.util.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtil jwtUtil;
	

	@Autowired
	private UserDetailsService userDetailsService;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// getting the token from postman and storing into token
		String token = request.getHeader("Auth");
		// checking whether token is null or not
		if (token != null) {
			// invokes getSubject method in jwtUtil class
			// which accepts token as input
			//at-last we get the email or username present in the token
			
			String email = jwtUtil.getSubject(token);
			if(email!=null&& SecurityContextHolder.getContext().getAuthentication()==null) {
				//getting the user details using email or username present in database
				UserDetails user = userDetailsService.loadUserByUsername(email);
				if(jwtUtil.isValidToken(token,user.getUsername())) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					
				}
			}
		}

		filterChain.doFilter(request, response);
	}

}
