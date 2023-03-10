import java.sql.*;

class DeleteApp {

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
                    String sqlDeleteQuery = "delete from student where sname = 'justin';";
                    int noOfRows = statement.executeUpdate(sqlDeleteQuery);
                    System.out.println("No of rows deleted is :: " + noOfRows);

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