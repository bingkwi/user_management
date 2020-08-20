package com.example.restfulweb.jwt;

public class UserToken {
	
	String token;
	public UserToken() {}
	
	public UserToken(String authentication) {
		this.token = authentication;
	}
	
	public void setToken(String auth) {
		this.token = auth;
	}
	
	public String getToken() {
		return token;
		
	}
}
