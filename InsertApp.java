import java.sql.*;

class InsertApp {

    public static void main(String[] args) {

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
                     
                    String sqlInsertQuery = "insert into student (sid, sname, marks) values (8, 'zidane', 78), (9, 'Karim benzema', 100), (10, 'messi', 110);";
                   
                    int noOfRows = statement.executeUpdate(sqlInsertQuery);
                    System.out.println("No of rows affected is :: " + noOfRows);
                 }
            }
        }catch(SQLException se) {
            se.printStackTrace();
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(statement != null)
                   statement.close();

                if(connection != null) 
                   connection.close();
                
            }catch(SQLException se1) {
                se1.printStackTrace();
            }catch(Exception e1) {
                e1.printStackTrace();
            }
        }

    }
}