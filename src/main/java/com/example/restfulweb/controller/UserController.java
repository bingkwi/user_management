package com.example.restfulweb.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restfulweb.dao.UserDAO;
import com.example.restfulweb.jwt.UserJwtUtil;
import com.example.restfulweb.jwt.UserToken;
import com.example.restfulweb.models.User;

@RestController
//@RequestMapping("/Users")

public class UserController {

	private UserDAO userDAO = new UserDAO();
	
	@GetMapping(value = "/users", produces = "application/json")
	public List<User> findAll() throws SQLException {
		return userDAO.findAll();
	}
	
	@GetMapping("/users/{username}")
	public User getUser(@PathVariable("username") String username) throws SQLException {
		return userDAO.findByUsername(username);
	}
	
	@PostMapping(value = "/users", produces = "application/json")
	public User createUser(@RequestBody User user) throws SQLException {
		return userDAO.insertUser(user);
	}
	
	@DeleteMapping("/{id}")
	public User deleteUser(@PathVariable("id") int id) {
		return deleteUser(id);
	}
	
	@PostMapping("/session")
	public UserToken login(@RequestBody User user) throws SQLException {
		User userSignIn = userDAO.findByUsername(user.getUsername());
		if( userSignIn == null) {
			return null;
		}else {
			if(userSignIn.getPassword().equals(user.getPassword())) {
				UserToken authenticate = new UserToken (UserJwtUtil.createJWT(user.getUsername()));
				return authenticate;
//				return "Login successfully";
			}
			return null;
		}	
	}
	
	@DeleteMapping("/session/{token}")
	public String logout(@PathVariable("token") String token) {
		 UserJwtUtil.putTokenToBlacklist(token);	
		 return "OK";
	}
}
