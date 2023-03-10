import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ReadingEmployeeData {

	public static void main(String[] args) {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Scanner scanner= null;
		
		try {

			 String url = "jdbc:mysql://localhost:3306/enterprise_java_course";
			 String userName = "root";
			 String password = "root123";
			
			connection  = DriverManager.getConnection(url, userName, password);
			
			if(connection != null) {
				String query = "Select name, address, gender, dob, doj, dom from employee where name = ?";
				pstmt = connection.prepareStatement(query);
				
				scanner = new Scanner(System.in);
				
				if(scanner != null) {
					System.out.println("Enter the name");
					String ename = scanner.nextLine();
					pstmt.setString(1, ename);
					
					resultSet = pstmt.executeQuery();
					
					if(resultSet != null ) {
						
						if(resultSet.next()) {
						    String name = resultSet.getString(1);
						    String address = resultSet.getString(2);
						    String gender = resultSet.getString(3);
						    
						    java.sql.Date sqlDob = resultSet.getDate(4);
						    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						    String sdob = sdf.format(sqlDob);
						   
						   java.sql.Date sqlDoj = resultSet.getDate(5);
						   sdf = new SimpleDateFormat("MM-dd-yyyy");
						   String sdoj = sdf.format(sqlDoj);
						   
						   java.sql.Date sqlDom = resultSet.getDate(6);  
						   String sdom = String.valueOf(sqlDom);
						   
						   System.out.println("name    :: " + name);
						   System.out.println("address :: " + address);
						   System.out.println("gender  :: " + gender);
						   System.out.println("dob     :: " + sdob);
						   System.out.println("doj     :: " + sdoj);
						   System.out.println("dom     :: " + sdom);
						   
						   
						 
						}else {
							System.out.println("NO data has matched");
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
