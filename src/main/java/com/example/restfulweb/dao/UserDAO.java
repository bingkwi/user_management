package com.example.restfulweb.dao;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.restfulweb.db.DbConnector;
import com.example.restfulweb.models.User;

//insertUser()
//findAll()
//findByUsername()
//deleteUser()

public class UserDAO {

	// Create connector
	private static final DbConnector connector = new DbConnector();

	public List<User> findAll() throws SQLException {
		// Execute sql statement
		String sql = "SELECT * FROM user";
		ResultSet rs = connector.connection().executeQuery(sql);

		// Process result
		List<User> userList = new ArrayList<User>(); // list
		while (rs.next()) {
			int id = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String salt = rs.getString("salt");
			Timestamp created = rs.getTimestamp("time_created");			
			User user = new User(id, username, password, salt, created);
			userList.add(user);
		}
		return userList;
	}

	public User findByUsername(String username) throws SQLException {
		String findByName = "SELECT * FROM user WHERE username = " + "\"" + username + "\"" + ";";
		ResultSet rs = connector.connection().executeQuery(findByName);
		if (rs.next()) { 
			int id = rs.getInt("id");
			String password = rs.getString("password");
			String salt = rs.getString("salt");
			Timestamp created = rs.getTimestamp("time_created");	
			User user = new User(id, username, password, salt, created);
//			System.out.println("name");
		return user;
		} else {
			return null;
		}
	}
	
	public User insertUser(User user) throws SQLException {
		String insertUser = "INSERT INTO user (username, password, salt, time_created)" + 
							"VALUES(" 
							+ "\"" + user.getUsername()+ "\"" + ", " 
							+ "\"" + user.getPassword() + "\"" + ", " 
							+ "\"" + user.getSalt() + "\"" + ", " 
							+ "\"" + user.getCreated() + "\"" + ");";
		connector.connection().executeUpdate(insertUser);
//		System.out.println(); 
		return findByUsername(user.getUsername());	
	}

	public void deleteUser(int id) throws SQLException {
		String deleteUser = "DELETE FROM user WHERE id =  " + id + ";";
		connector.connection().executeUpdate(deleteUser);
	}

	public static Timestamp parseTime(String dateTimeString) throws ParseException {
		// instantiate dateFormatString ---> define date then parse it ---> using
		// Timestamp constructor by predefined millisec

		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
//		String dateTimeString = "2020/08/07 09:40:22";

		Date date = simpleDate.parse(dateTimeString);
		long milli = date.getTime();
		Timestamp timestamp = new Timestamp(milli);
		return timestamp;
	}
	
	
	public static void main(String[] args) throws SQLException, ParseException {
		UserDAO userDAO = new UserDAO();

		User user = new User("user023", "16565", "26565", parseTime("2020/05/01 20:50:22.0"));
//		User userFindAll = userDAO.findAll();
//		User userFindName = userDAO.findByUsername("user03");
//		System.out.println(userFindName);
		userDAO.insertUser(user);
		userDAO.deleteUser(2);
		
	}
}
