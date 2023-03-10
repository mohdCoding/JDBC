package BlobPgms;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.util.JdbcConnection;
import com.mysql.cj.jdbc.Blob;

public class RetrievingAudio_BlobData {

	public static void main(String[] args) {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
			try {
				connection = JdbcConnection.getJdbcConnection();
				
				if(connection != null) {
					String query = "select author_name, music_name, audio from store_music where music_name = ? " +
				                   "and author_name = ?";
					pstmt = connection.prepareStatement(query);
					pstmt.setString(1, "Bad Boy");
					pstmt.setString(2, "Marwa Loud");
					resultSet = pstmt.executeQuery();
					
					if(resultSet != null) {
						if(resultSet.next()) {
							String authorName = resultSet.getString(1);
							String musicName = resultSet.getString(2);
						 	InputStream is = resultSet.getBinaryStream(3);
						 	
						 	File musicFile = new File("download_badboy.mp3");
						 	musicFile.createNewFile();
						 	
						 	FileOutputStream fos = new FileOutputStream(musicFile);
						 	
						 	byte[] buffer = new byte[3000];
						 	
						 	while(is.read(buffer) > 0) {
						 		fos.write(buffer);
						 	}
						 	
						 	System.out.println("Author Name: " + authorName);
						 	System.out.println("Music Name : " + musicName);
						 	System.out.println("File is downloaded sucessfully");
						 	
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
				
		


	}

}
