package ClobPgms;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.jdbc.util.JdbcConnection;

public class InsertingTxtInDb {

	public static void main(String[] args) {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = JdbcConnection.getJdbcConnection();
			
			if(connection != null) {
				String query= "insert into emp_city_info (name, city_file) values " +
			                   "(?, ?)";
				pstmt = connection.prepareStatement(query);
				
				pstmt.setString(1, "Abul");
				
				File file = new File("Coimbature.txt");
				FileReader reader = new FileReader(file);
				
				
				 // pstmt.setCharacterStream(2, reader);// -- it also works fine
				  pstmt.setClob(2, reader); // this also works fine
				
			    int rowsAffected = pstmt.executeUpdate();
			    if(rowsAffected > 0 ) 
			        System.out.println(rowsAffected + " :: rows has been affected");
			    else
			    	System.out.println("cant insert the data");
				
				;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			try {
				JdbcConnection.closeJdbcConnection(null, pstmt, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
