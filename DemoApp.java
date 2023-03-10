import com.mysql.cj.jdbc.Driver;
import java.sql.*;

class DemoApp {



      Driver driver = new Driver();
      DriverManager.registerDriver(driver);

      String url = "jdbc:mysql://localhost:3306/studentdata";
      String userName = "root";
      String password = "root";
      Connection connection = DriverManager.getConnection(url, userName, password);

      Statement statement = connection.createStatement();
      String sqlSelectQuery = "SELECT id,name,marks FROM student WHERE marks >= 70 ";
      ResultSet rs = statement.executeQuery(sqlSelectQuery);

      System.out.println("id\tname\tmarks");
       
       while(rs.next()) {
            Integer id = Integer.parseInt(rs.getString("id"));
            String name = rs.getString("name");
            Integer mark = Integer.parseInt(rs.getString("marks"));
            System.out.println(id + "\t" + name + "\t" + mark);
       }
     

      connection.close();
    }
}