package com.example.restfulweb.jwt;

import java.nio.charset.MalformedInputException;
import java.security.Key;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

public class UserJwtUtil {
	private static String secretKey = "A_SECRET_KEY";
	private static byte[] secretKeyBytes = secretKey.getBytes();
	private static Key key = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());
	
	private static List<String> blackList = new ArrayList<String>();

	public static String createJWT(String username) {
		String jws = Jwts.builder()
				.setIssuer("my_server_name")
				.claim("username", username)
				.setExpiration(Date.from(Instant.now().plusSeconds(30)))
				.signWith(SignatureAlgorithm.HS256, key)
				.compact();
		return jws;

	}

	public static boolean validateToken(String token) {
		

//		String Jwts;

		if (blackList.contains(token)) {
			return false;
		}

		Claims claims = null; // initialize

		try {
			claims = (Claims) Jwts.parser() // assign value
								.setSigningKey(key) // the SAME key used for signing
								.parse(token)
								.getBody();
		} catch (ExpiredJwtException | MalformedJwtException | ClassCastException e) {
			return false;
		}

		boolean hasUsernameClaim = claims.containsKey("username");
		return hasUsernameClaim;

	}

	public static void putTokenToBlacklist(String token) {
		boolean isTokenValid = validateToken(token);
		if (!isTokenValid)
			throw new IllegalArgumentException();

		blackList.add(token);
	}

}
