package StoredProcedure;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.util.JdbcConnection;
import java.sql.CallableStatement;

public class Demo1 {

	public static void main(String[] args) {
		
		Connection connection = null;
		CallableStatement cstmt = null;
		ResultSet resultSet  = null;
		
		
		try {
			connection = JdbcConnection.getJdbcConnection();
			
			// stored procedure
			String storedProcedure= "{call getStudent(?)}";
			
			cstmt = connection.prepareCall(storedProcedure);
			
			cstmt.setInt(1, 1);
			
			cstmt.execute();
			
			resultSet = cstmt.getResultSet();
			
			if(resultSet.next()) {
				System.out.println(resultSet.getInt(1));
				System.out.println(resultSet.getString(2));
				System.out.println(resultSet.getInt(3));
			}
       		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
