import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class InsertingEmployeeData {

	public static void main(String[] args) throws Exception {
		
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner= null;
		
		try {
			
			 String url = "jdbc:mysql://localhost:3306/enterprise_java_course";
			 String userName = "root";
			 String password = "root123";
			
			connection  = DriverManager.getConnection(url, userName, password);
			
			if(connection != null) {
				String query = "INSERT INTO employee(name, address, gender, dob, doj, dom) VALUES (?, ?, ?, ?, ?, ?)";
				
				pstmt = connection.prepareStatement(query);
				
				scanner = new Scanner(System.in);
				
				if(scanner != null) {
				
					System.out.println("Enter your name");
					String name = scanner.nextLine();
					
					System.out.println("Enter your address");
					String address = scanner.nextLine();
					
					System.out.println("Enter your gender");
					String gender = scanner.next();
					
					System.out.println("Enter Your Date Of Birth: (dd-MM-yyyy)");
					String sdob = scanner.next();
					
					System.out.println("Enter the Date of Joining (MM-dd-yyyy)");
					String sdoj = scanner.next();
					
					System.out.println("Enter the Date of Manufacture (yyyy-MM-dd)");
					String sdom = scanner.next();
					
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date utilDob = sdf1.parse(sdob);
					
					long lDob = utilDob.getTime();
					java.sql.Date sqlDob = new java.sql.Date(lDob);
					
					sdf1 = new SimpleDateFormat("MM-dd-yyyy");
					java.util.Date utilDoj= sdf1.parse(sdoj);
					
					long lDoj = utilDoj.getTime();
					java.sql.Date sqlDoj = new java.sql.Date(lDoj);
					
					java.sql.Date sqlDom = java.sql.Date.valueOf(sdom);
					
				if(pstmt != null) {
					pstmt.setString(1, name);
					pstmt.setString(2, address);
					pstmt.setString(3, gender);
					pstmt.setDate(4, sqlDob);
					pstmt.setDate(5, sqlDoj);
					pstmt.setDate(6, sqlDom);
					
					Integer rowsAffected = pstmt.executeUpdate();
					System.out.println(rowsAffected + " :: rows has been affected");
				}
		    	}
				
			}
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e ) {
			  e.printStackTrace();
		}finally {
			
			try {
				
				if(pstmt != null)
					pstmt.close();
				
				if(connection != null) 
					connection.close();
				
				if(scanner != null) 
					scanner.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
	}

}
