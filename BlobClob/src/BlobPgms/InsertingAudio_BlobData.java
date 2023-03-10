package BlobPgms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.jdbc.util.JdbcConnection;

public class InsertingAudio_BlobData {

	public static void main(String[] args) {

		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = JdbcConnection.getJdbcConnection();
			if(connection != null) {
				
				String query = "insert into store_music(author_name, music_name, audio)" +
				               "values (?, ?, ?)";
				
				pstmt = connection.prepareStatement(query);
				
				if(pstmt != null) {
					String authorName = "Marwa Loud";
					String musicName = "Bad Boy";
					File audioFile = new File("badboy.mp3");
					FileInputStream badBoy = new FileInputStream(audioFile);
					pstmt.setString(1, authorName);
					pstmt.setString(2, musicName);
					pstmt.setBlob(3, badBoy);
					
					int rowsAffected = pstmt.executeUpdate();
					System.out.println(rowsAffected + " :: rows has been affected");
				}
				
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
