package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.jdbc.util.JdbcConnection;

public class TransactionApp {

	public static void main(String ...args) {
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		
		try {
			connection = JdbcConnection.getJdbcConnection();
			
			if(connection != null) {
				System.out.println("Before the transaction");
				System.out.println("==============================");
				String query = "select name, balance from accounts";
				
				
				     stmt = connection.createStatement();
				     if(stmt != null) {
				    	 resultSet = stmt.executeQuery(query);
							
							if(resultSet != null) {
								while(resultSet.next()) {
									System.out.println(resultSet.getString("name"));
									System.out.println(resultSet.getInt("balance"));
								}
							}
				     }
					
				
				
				System.out.println("===================================");
				System.out.println("After the transaction");
				System.out.println("=====================================");
				
				connection.setAutoCommit(false);
				
				String query1 = "update accounts set balance = balance - 3000 where name = 'ronaldo'";
				String query2 = "update accounts set balance = balance + 3000 where name = 'bruno'";
				
			    stmt.executeUpdate(query1);
			    stmt.executeUpdate(query2);
			    
			    Scanner in = new Scanner(System.in);
			    System.out.println("do you want to send 3000 to bruno :: yes or no");
			    String choice = in.next();
			    
			    if(choice.equalsIgnoreCase("yes")) {
			    	connection.commit();
			    } else {
			    	connection.rollback();
			    	System.out.println("transaction failed");
			    }
				
			    
			    resultSet = stmt.executeQuery("select name, balance from accounts");
			    while(resultSet.next()) {
			    	System.out.println(resultSet.getString("name"));
			    	System.out.println(resultSet.getInt("balance"));
			    	System.out.println("------------------------------");
			    }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
