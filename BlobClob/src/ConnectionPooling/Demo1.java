package ConnectionPooling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;


public class Demo1 {

	public static void main(String[] args)  {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/enterprise_java_course");
		ds.setUser("root");
		ds.setPassword("root123");
		
		try {
			connection = ds.getConnection();
			String query = "select * from student where marks >= ?";
			pstmt = connection.prepareStatement(query);
		    pstmt.setInt(1, 56);
		    
		    resultSet = pstmt.executeQuery();
		    
		    if(resultSet.next()) {
		    	System.out.println(resultSet.getInt("id"));
		    	System.out.println(resultSet.getString("name"));
		    	System.out.println(resultSet.getInt("marks"));
		    }
		    
		    pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
