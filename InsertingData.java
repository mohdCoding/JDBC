import java.sql.*;
import java.util.*;
class InsertingData {

    public static void main(String[] args) {
       
       Connection con = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       Scanner in = new Scanner(System.in);
       System.out.println("Enter the id of student");
       int id = in.nextInt();
    

       
       

       try {
          String url = "jdbc:mysql://localhost:3306/enterprise_java_course";
          String uName = "root";
          String pwd = "root123";
          
          con = DriverManager.getConnection(url, uName, pwd);

          if(con != null) {
              String query = "select id, name, marks from student where id = ?";
              pstmt = con.prepareStatement(query);

              if(pstmt != null) {
                    pstmt.setInt(1, id);
                   
                    rs = pstmt.executeQuery();
                    
                    if(rs != null) {

                       if(rs.next()) {
                          System.out.println(rs.getInt(1)+ ":" + rs.getString(2) + ":" + rs.getInt(3));
                       } else {
                        System.out.println("NO data matched");
                       }
                        
                       
                    }
              }


          }

       } catch(SQLException se) {
           se.printStackTrace();
       } finally {
           
           try {

            if(pstmt != null){
                pstmt.close();
            }

            if(rs != null) {
                rs.close();
            }
               
            if(con != null) {
               con.close();
            }

            if(in != null) {
                in.close();
            }
               
           } catch (SQLException  se) {
               se.printStackTrace();
           } catch(Exception e) {
               e.printStackTrace();
           }
       }

    
    }
}