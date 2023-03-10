package BlobPgms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.jdbc.util.JdbcConnection;

public class InsertingVideo_BlobData {

	public static void main(String[] args) {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = JdbcConnection.getJdbcConnection();
			
			if(connection != null) {
				String query = "insert into video_table(name, video) values(?, ?)";
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, "Ronaldo_Status_Video");
				
				File file = new File("ronaldo_status.mp4");
				InputStream fis = new FileInputStream(file);
				pstmt.setBlob(2, fis);;
				
				int rowsAffected = pstmt.executeUpdate();
				
				if(rowsAffected > 0 ) {
					System.out.println(rowsAffected + " :: rows has been affected");
				} else {
					System.out.println("Cannot able to inser the data");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			try {
				JdbcConnection.closeJdbcConnection(null, pstmt, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
 }