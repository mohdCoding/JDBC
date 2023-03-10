package com.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnection {
	
	
	 private JdbcConnection() {
		 
	 }
	
	public static Connection getJdbcConnection() throws SQLException {
		
		String url = "jdbc:mysql://localhost:3306/enterprise_java_course";
		String username = "root";
		String password = "root123";
		
		Connection connection = DriverManager.getConnection(url, username, password);
		
		return connection;
	}
	
	public static void closeJdbcConnection(ResultSet resultSet, Statement statement, Connection connection) throws
	    SQLException {
		
		if(resultSet != null) 
		     resultSet.close();
		
	    if(statement != null)
	    	statement.close();
		
		if(connection != null)
			connection.close();
		
	}

}
