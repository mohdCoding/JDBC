package BlobPgms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.util.JdbcConnection;
import com.mysql.cj.jdbc.Blob;

public class RetrievingBlobData {

	public static void main(String[] args) throws IOException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			connection = JdbcConnection.getJdbcConnection();
			if(connection != null) {
				
				String query = "select name, image from store_image where name = ?";
				String iname = "homescreen";
				
				pstmt = connection.prepareStatement(query);
				if(pstmt != null) {
				pstmt.setString(1, iname);
				
				resultSet = pstmt.executeQuery();
				
				if(resultSet != null) {
					if(resultSet.next()) {
						
						String name = resultSet.getString(1);
						InputStream io = resultSet.getBinaryStream(2);
						FileOutputStream fos = new FileOutputStream("download_homescreen.png");
						int count = 0;
						
						byte[] buffer = new byte[2048];
						
						while(io.read(buffer) > 0 ) {
							fos.write(buffer);
						}
						
				}
			}
				
				
				
		}
				
	}
		} catch (SQLException e) {
			e.printStackTrace();
		} 


	}

}
