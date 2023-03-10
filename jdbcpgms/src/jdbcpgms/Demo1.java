package jdbcpgms;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;


public class Demo1 {

	

	public static void main(String[] args) throws SQLException, Exception {

		Driver driver = new Driver();
	    DriverManager.registerDriver(driver);
		String url = "jdbc:mysql://localhost:3306/enterprise_java_course";
		String uName = "root";
		String pwd = "root123";
		Connection con = DriverManager.getConnection(url, uName, pwd);
		
		String name ="Sri";
		String pass ="Haran";
		
		String query = "select * from users where username = '" + name + "'-- and password = '" + pass + "'";
		Statement st= con.createStatement();

	
		
		
         ResultSet rs = st.executeQuery(query);
         
         while(rs.next()) {
        	 System.out.println(rs.getInt("id"));
        	 System.out.println(rs.getString("username"));
        	 System.out.println(rs.getString("password"));
         }
	}

}
