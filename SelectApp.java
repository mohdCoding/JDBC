import java.sql.*;

class SelectApp {

    public static void main(String[] args)  {
        // in jdbc 3.X version we need to explicitly register driver
        // Driver driver=new Driver();
        // DriverManager.registerDriver(driver);

       // from jdbc 3.5 it will load and register the driver for us
       // Class.forName("com.mysql.cj.jdbc.Driver");

       // from jdbc 4.X version we dont need to explicitly load and register
       // the driver. since we set the classpath. through our url our jvm will
       // identify the specific db jar and open META-INF and open services
       // and open java.sql.Driver(I) the it will load and register the driver
       // for us so benefit is no need to remember diff db vendor driver name

       Connection connection = null;
       Statement statement = null;
       ResultSet resultSet = null;
       
       try{
           String url = "jdbc:mysql://localhost:3306/enterprise_java_batch";
           String userName = "root";
           String password = "root";
           connection = DriverManager.getConnection(url, userName, password);

        if(connection != null) {
          statement = connection.createStatement();
          
           if(statement != null) {
              String sqlSelectQuery = "select * from student;";
              resultSet = statement.executeQuery(sqlSelectQuery);

              if(resultSet != null) {
                System.out.println("id\tname\tmarks");
                while(resultSet.next()) {
                 int id = resultSet.getInt("sid");
                 String name = resultSet.getString("sname");
                 int marks = Integer.parseInt(resultSet.getString("marks"));
                 System.out.println(id + "\t" + name + "\t" + marks);
                }
              }
           }
        }
       }catch(SQLException se) {
          se.printStackTrace();
       }catch(Exception e) {
          e.printStackTrace() ;

       }finally {
        try{

         if(resultSet != null)
              resultSet.close();
         
         if(statement != null)
              statement.close();

         if(connection != null) 
              connection.close();
        }catch(SQLException se) {
            se.printStackTrace();
        }
         
       }
     }
}