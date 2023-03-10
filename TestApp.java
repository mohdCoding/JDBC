import com.mysql.cj.jdbc.Driver;
import java.sql.*;

class TestApp {

    public static void main(String[] args) throws Exception {

        // 1. Load and Register the driver
        Driver driver = new Driver();
        DriverManager.registerDriver(driver);

        // 2. Establish the connection b/w the jp and db
        // Syntax: <mainprotocolo>:<subprotocol>://<subname>(db name)
        String url = "jdbc:mysql://localhost:3306/enterprise_java_batch";
        String userName = "root";
        String password = "root";
        Connection connection= DriverManager.getConnection(url, userName, password);

        // 3. Create a Statement Object
        Statement statement = connection.createStatement();
        
        // 4. Send and execute the query
        String sqlSelectQuery = "SELECT sid, sname, marks FROM student WHERE marks >=90;";

        // 5. Process the data from ResultSet
        ResultSet resultSet = statement.executeQuery(sqlSelectQuery);
        System.out.println("sid\tsname\tmarks");
        while(resultSet.next()) {
              Integer id = resultSet.getInt(1);
              String name = resultSet.getString(2);
              Integer marks = resultSet.getInt(3);
              System.out.println(id + "\t" + name + "\t" + marks);
        }

        // 6. Close the Connection
        connection.close();

    }
}