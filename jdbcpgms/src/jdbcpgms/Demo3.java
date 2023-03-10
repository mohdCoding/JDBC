package jdbcpgms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.jdbc.util.JdbcConnection;

public class Demo3 {

	public static void main(String[] args) throws Exception {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the name: ");
		String name = in.next();
		System.out.print("Enter the dob: yyyy-MM-dd");
		String sdob = in.next();
		

		
		
		java.sql.Date sqlDob = java.sql.Date.valueOf(sdob);
		
		try {
			
			connection = JdbcConnection.getJdbcConnection();
			
			if(connection != null) {
				String query = "insert into userdata (name, dob) values (?, ?)";
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, name);
				pstmt.setDate(2, sqlDob);
				int rowsAffected = pstmt.executeUpdate();
				System.out.println("No of rows Affected :: " + rowsAffected);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				JdbcConnection.closeJdbcConnection(null, pstmt, connection);
			} catch(SQLException se) {
				se.printStackTrace();
			}
			
			
		}
		

	}

}
