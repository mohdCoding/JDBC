import java.sql.*;

class UpdateApp {

    public static void main(String... args) {

        Connection connection = null;
        Statement statement = null;

        try{

            String url = "jdbc:mysql://localhost:3306/enterprise_java_batch";
            String uName = "root";
            String pwd = "root";

           connection = DriverManager.getConnection(url, uName, pwd);

           if(connection != null) {
                statement = connection.createStatement();

                if(statement != null) {
                    String sqlUpdateQuery = "update student set sname = 'jackie chan', marks = 40 where sname = 'jack'";
                    int noOfRows = statement.executeUpdate(sqlUpdateQuery);
                    System.out.println("No of rows updated is :: " + noOfRows);

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