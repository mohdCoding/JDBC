import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class RetrievingTheEmployeeData {

	public static void main(String[] args) throws Exception {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Scanner scanner= null;
		
		try {
		
			String url = "jdbc:mysql://localhost:3306/enterprise_java_course";
			String userName = "root";
			String  password = "root123";
			
			connection  = DriverManager.getConnection(url, userName, password);
			
			if(connection != null) {
				
				scanner = new Scanner(System.in);
				if(scanner != null ) {
					System.out.println("Enter the id of an employee to see the details");
					int sid = scanner.nextInt();
					
					String query = "select id, name, gender, addr_code, dob, doj, dom from employee_details where id = ?";
					pstmt = connection.prepareStatement(query);
					
					if(pstmt != null) {
						pstmt.setInt(1, sid);
						resultSet = pstmt.executeQuery();
						
						if(resultSet != null) {
							if(resultSet.next()) {
							   int id = resultSet.getInt("id");
							   String name = resultSet.getString("name");
							   String gender = resultSet.getString("gender");
							   int addr_code =resultSet.getInt("addr_code");
							   java.sql.Date sqlDob = resultSet.getDate("dob");
							   java.sql.Date sqlDoj = resultSet.getDate("doj");
							   java.sql.Date sqlDom = resultSet.getDate("dom");
							   
							   SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
							   String dob = sdf.format(sqlDob);
							   
							   sdf = new SimpleDateFormat("MM-dd-yyyy");
							   String doj = sdf.format(sqlDoj);
							   
							   String dom = String.valueOf(sqlDom);
							   
							   String aquery = "select state, district, pincode, city, street, doorno from employee_address where addr_code = ?";
							   
							   pstmt = connection.prepareStatement(aquery);
							   pstmt.setInt(1, addr_code);
							   
							   resultSet = pstmt.executeQuery();
							   
							   if(resultSet.next()) {
								   String state = resultSet.getString(1);
								   String district = resultSet.getString(2);
								   int pincode = resultSet.getInt(3);
								   String city = resultSet.getString(4);
								   String street = resultSet.getString(5);
								   String doorno = resultSet.getString(6);
								   
								  
								   System.out.println("id        ::  " + id);
								   System.out.println("name      ::  " + name);
								   System.out.println("gender    ::  " + gender);
								   System.out.println("state     ::  " + state);
								   System.out.println("district  ::  " + district);
								   System.out.println("pincode   ::  " + pincode);
								   System.out.println("city      ::  " + city);
								   System.out.println("street    ::  " + street);
								   System.out.println("doorno    ::  " + doorno);
								   System.out.println("dob       ::  " + dob);
								   System.out.println("doj       ::  " + doj);
								   System.out.println("dom       ::  " + dom);
							   }
							   
							 }
						}
					}
					
				}
				
			}

		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(resultSet != null)
					resultSet.close();
				
				if(pstmt != null)
					pstmt.close();
				
				if(connection != null) 
					connection.close();
				
				if(scanner != null) 
					scanner.close();
			} catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
}