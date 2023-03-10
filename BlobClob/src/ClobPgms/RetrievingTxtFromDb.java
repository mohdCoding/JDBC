package ClobPgms;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.util.JdbcConnection;

public class RetrievingTxtFromDb {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcConnection.getJdbcConnection();
			
			if(connection != null) {
				String query= "select name,city_file from emp_city_info where name = ?";
				
				pstmt = connection.prepareStatement(query);
				
				pstmt.setString(1, "Abul");
				
				resultSet = pstmt.executeQuery();
				
				if(resultSet != null) {
					if(resultSet.next()) {
						
						String name = resultSet.getString(1);
						Reader reader = resultSet.getCharacterStream(2);
						
						FileWriter file = new FileWriter("download_abul_info.txt");
						
						char[] ch = new char[50];
						
						while(reader.read(ch) > 0) {
							file.write(ch);
						}
						
						file.flush();
						
						System.out.println(name + " :: File downloaded succesfully");
					}
				}
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
