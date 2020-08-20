package com.example.restfulweb.jwt;


import java.nio.charset.MalformedInputException;
import java.security.Key;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

public class UserJwtUtil {
	
	public static List<String> blackList = new ArrayList<String>();
	
	public static String createJWT(String username) {		
		String secretKey = "điền cmn secret key vào đây";
		byte[] secretKeyBytes = secretKey.getBytes();	
	
		Key key = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());
		String jws = Jwts.builder()	
						.setIssuer("my_server_name")
						.claim("username", username)
						.setExpiration(Date.from(Instant.now().plusSeconds(30)))
						.signWith(SignatureAlgorithm.HS256, key)
						.compact();
		return jws;
						
	}
	
//	public static void validateToken() {
//		String secretKey = "điền cmn secret key vào đây";
//		byte[] secretKeyBytes = secretKey.getBytes();	
//	
//		Key key = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());
//
//		String token;
//		
//		try {
//		
//			claims = (Claims)parser.parse(token)
//									.getBody();
//		}catch (ExpiredJwtException | MalformedJwtException | ClassCastException)
//		
//	}

}
