import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class InsertingTheEmployees {

	public static void main(String[] args) throws Exception {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Scanner scanner = null;
		
		try {
			
			 String url      = "jdbc:mysql://localhost:3306/enterprise_java_course";
			 String userName = "root";
			 String password = "root123";
			
			connection  = DriverManager.getConnection(url, userName, password);
			
			if(connection != null) {
				
				scanner = new Scanner(System.in);
				
				if(scanner != null) {
					
				    System.out.println("Enter your employee id: ");
				    int id = scanner.nextInt();
				    scanner.nextLine();
				    
				    System.out.println("Enter your name: ");
				    String name = scanner.nextLine();
				    
				    System.out.println("Enter your Gender  (M) or (F)");
				    String gender = scanner.next();
				    scanner.nextLine();
				    
				    
				    
				    System.out.println("Give your address details: ");
				    System.out.println("Enter Your State: ");
				    String state = scanner.nextLine();
				    
				    System.out.println("Enter your district: ");
				    String district = scanner.nextLine();
				    
				    System.out.println("Enter your pincode: ");
				    int pincode = scanner.nextInt();
				    scanner.nextLine();
				    
				    System.out.println("Enter your city: ");
				    String city = scanner.nextLine();
				    
				    System.out.println("Enter your street: ");
				    String street = scanner.nextLine();
				    
				    System.out.println("Enter your doorno: ");
				    String doorno = scanner.nextLine();
				    
				    System.out.println("Enter Your Date Of Birth: (dd-MM-yyyy)");
					String sdob = scanner.next();
					
					System.out.println("Enter the Date of Joining (MM-dd-yyyy)");
					String sdoj = scanner.next();
					
					System.out.println("Enter the Date of M (yyyy-MM-dd)");
					String sdom = scanner.next();
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date udob = sdf.parse(sdob);
					
					long ms = udob.getTime();
					java.sql.Date sqlDob = new java.sql.Date(ms);
					
					sdf = new SimpleDateFormat("MM-dd-yyyy");
					java.util.Date udoj = sdf.parse(sdoj);
					ms = udoj.getTime();
					
					java.sql.Date sqlDoj = new java.sql.Date(ms);
				    
					java.sql.Date sqlDom = java.sql.Date.valueOf(sdom);
					
				    
					
					String aquery = "insert into employee_address (state, district, pincode, city, street, doorno, id) values " +
					               "(?, ?, ?, ?, ?, ?, ?)";
					
					
					pstmt = connection.prepareStatement(aquery);
					pstmt.setString(1, state);
					pstmt.setString(2, district);
					pstmt.setInt(3, pincode);
					pstmt.setString(4, city);
					pstmt.setString(5, street);
					pstmt.setString(6, doorno);
					pstmt.setInt(7,id);
					
					pstmt.executeUpdate();
					
					
					

					String fetchAddrCode = "select addr_code from employee_address where id = ?";
					
					pstmt = connection.prepareStatement(fetchAddrCode);
					
					pstmt.setInt(1, id);
					
					 resultSet = pstmt.executeQuery();
					Integer addr_code = null;
					if(resultSet != null) {
						if(resultSet.next()) {
							addr_code = resultSet.getInt(1);
						}
					}
					
					String equery = "insert into employee_details (id, name, gender, addr_code, dob, doj, dom) values " +
        	                 "(?, ?, ?, ?, ?, ?, ?)";
					
					pstmt = connection.prepareStatement(equery);
					
					pstmt.setInt(1, id);
					pstmt.setString(2, name);
					pstmt.setString(3, gender);
					pstmt.setInt(4, addr_code);
					pstmt.setDate(5, sqlDob);
					pstmt.setDate(6, sqlDoj);
					pstmt.setDate(7, sqlDom);
								
					int rowsAffected = pstmt.executeUpdate();
					System.out.println(rowsAffected + " :: rows has been affected");
				}
				
				
				
			}
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				if(pstmt != null) 
					pstmt.close();
				
				if(resultSet != null)
					resultSet.close();
				
				if(connection != null)
					connection.close();
				
				if(scanner != null)
					scanner.close();
				
			} catch(SQLException se) {
				se.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} 
		
	}

}
