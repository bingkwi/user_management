package com.example.restfulweb.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnector{
	
	private static final String DRIVER_NAME="com.mysql.cj.jdbc.Driver";
	private static final String TEMPLATE = "jdbc:mysql://%s:%s/%s?user=%s&password=%s";
	private static final String HOST ="localhost";
	private static final String PORT ="3306";
	private static final String DB_NAME = "web";
	private static final String USER = "root";
	private static final String PASSWORD = "bich@quynh";
	
	private Connection connection;
	
	//Load driver
	public void loadDriver() {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Build connection string based on predefined formula
	public static String buildConnectionString(String template, String host, String port,
										String dbName, String user, String password) {
		return String.format(template, host, port, dbName, user, password);
	}
	
	//Create connection
	public DbConnector connection() throws SQLException {
		if(connection == null) {
			String connectionString = DbConnector.buildConnectionString(TEMPLATE, HOST, PORT, DB_NAME, USER, PASSWORD);
			connection = DriverManager.getConnection(connectionString);
		}
		return this;
		
	}
	
	public ResultSet executeQuery(String sql) throws SQLException {
		return connection.createStatement()
						 .executeQuery(sql);
	}
	
	public int executeUpdate(String sql) throws SQLException {
		return connection.createStatement().executeUpdate(sql);
	}
	
	public static void main (String [] args) throws SQLException {
		DbConnector connector = new DbConnector();
		String sql = "SELECT * FROM user";
		ResultSet rs = connector.connection().executeQuery(sql);
		
		while (rs.next()) {
			System.out.println("User manager: (id=" + rs.getInt(1)
								     +" ,username=" + rs.getString(2)
								     +" ,password=" + rs.getString(3)
								     +" ,salt=" + rs.getString(4)
								     +" ,created at=" + rs.getTimestamp(5)
								     +")");
		}
	}
}
