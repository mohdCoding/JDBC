package BlobPgms;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

import com.jdbc.util.JdbcConnection;
public class InsetingBlobData {

	public static void main(String[] args) {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = JdbcConnection.getJdbcConnection();
			if(connection != null) {
				String query = "insert into store_image(name, image) values (?, ?)";
				pstmt = connection.prepareStatement(query);
				String name = "homescreen";
				
				File f=new File("homescreen.png");
				FileInputStream fis=new FileInputStream(f);
				
				if(pstmt != null) {
					pstmt.setString(1, name);
					pstmt.setBlob(2, fis);
					
					int rowsAffected = pstmt.executeUpdate();
					System.out.println(f.getAbsolutePath());
					System.out.println(rowsAffected + " :: has been affected");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
