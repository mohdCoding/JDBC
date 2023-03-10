import java.sql.*;
import java.util.Scanner;

class InsertUserData {

    public static void main(String... args) {

        Connection connection = null;
        Statement statement = null;

        Scanner scanner =  new Scanner(System.in);

        String sname = null;
        Integer marks = null;

        try{

           if(scanner != null) {
             sname = scanner.nextLine();
             marks = scanner.nextInt();
           }

            String url = "jdbc:mysql://localhost:3306/enterprise_java_batch";
            String uName = "root";
            String pwd = "root";

           connection = DriverManager.getConnection(url, uName, pwd);

           if(connection != null) {
                statement = connection.createStatement();

                if(statement != null) {
                    String sqlInsertQuery = "insert into student (sname, marks) values ('" + sname + "'," + marks + ")";
                    
                    int noOfRows = statement.executeUpdate(sqlInsertQuery);
                    System.out.println("No of rows Inserted is :: " + noOfRows);

                }
           }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
          try{
              if(statement != null) 
                statement.close();
            
              if(connection != null) 
                connection.close();
          } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
           
            
        }
    }
}