package com.ty.blogmanagement.util;

import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {

	// flow -3rd-step

	@Value("${app.secret.key}")
	private String secretKey;

	public String generateToken(String subject) {
		System.err.println("util -- 1");
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);

		return Jwts.builder().setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(Keys.hmacShaKeyFor(keyBytes), SignatureAlgorithm.HS256).compact();

	}

	// this method will extract the payload from jwt token
	public String getSubject(String token) {

		return getClaims(token).getSubject();// invoking the get token method

	}

	private Claims getClaims(String token) {
		// getting the payload from jwt token and returning to get subject method
		// here we give secrect key and algorithim name to decode the data from
		// jwt token to normal data
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(keyBytes)).build().parseClaimsJws(token).getBody();
	}

	public boolean isValidToken(String token, String username) {

		// String tokenUserName = getSubject(token);
		// checking whether the token email and the data base email are same or not
		String tokenEmail = getSubject(token);
		// chicking the token is expaired or not
		return tokenEmail.equals(username) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {

		return getExpirationDate(token).before(new Date(System.currentTimeMillis()));
	}

	private Date getExpirationDate(String token) {
		return getClaims(token).getExpiration();
	}

}
