package BlobPgms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.util.JdbcConnection;

public class RetrievingVideo_BlobData {

	public static void main(String[] args) {
        
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			connection = JdbcConnection.getJdbcConnection();
			
			if(connection != null) {
				String query = "select name, video from video_table where name = ?";
				pstmt = connection.prepareStatement(query);
				if(pstmt != null) {
					pstmt.setString(1, "Ronaldo_Status");
					resultSet = pstmt.executeQuery();
					
					if(resultSet != null) {
						if(resultSet.next()) {
							String name = resultSet.getString("name");
							InputStream io = resultSet.getBinaryStream("video");
							
							byte[] buffer = new byte[2024];
							File file = new File("download_ronaldo_status_Amazing.mp4");
							file.createNewFile();
							FileOutputStream fos = new FileOutputStream(file);
						    if(io != null) {
						    	while(io.read(buffer) > 0) {
						    		fos.write(buffer);
						    	}
						    }
						    
						    System.out.println("File downloaded sucessfully");
						}
					}
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcConnection.closeJdbcConnection(resultSet, pstmt, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
