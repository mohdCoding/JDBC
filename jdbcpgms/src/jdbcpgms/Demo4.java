package jdbcpgms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.jdbc.util.JdbcConnection;

public class Demo4 {

	public static void main(String[] args) throws ParseException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Scanner in=new Scanner(System.in);
		System.out.print("Enter the name: ");
		String name = in.next();
		
		try {
			
			connection = JdbcConnection.getJdbcConnection();
			
			if(connection != null) {
				String query = "select id, name, dob from userdata where name = ?";
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, name);
				
				
				resultSet = pstmt.executeQuery();
				if(resultSet != null) {
					
					if(resultSet.next()) {
						System.out.println("id\tname\tdob");
						int id = resultSet.getInt(1);
						String uname =  resultSet.getString(2);
						java.sql.Date sqldob =    resultSet.getDate(3);
						
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						String sdob = sdf.format(sqldob);
						System.out.println(id + "\t" + uname + "\t" + sdob);
					} else {
						System.out.println("NO data is matched in database");
					}
				}
			
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				JdbcConnection.closeJdbcConnection(resultSet, pstmt, connection);
				
				if(in != null) 
					in.close();
			} catch(SQLException se) {
				se.printStackTrace();
			}
			
			
		}
		

	}

}
