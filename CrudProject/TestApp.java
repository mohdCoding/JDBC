import java.sql.*;
import java.util.*;

class TestApp {

    public static void main(String[] args) {
        
        System.out.println("1. Create a new Student Record\n" + 
                           "2. Read The student Records\n" +
                           "3. Update the existing student Record\n" +
                           "4. Delete an existing student Record\n");

       Scanner in = new Scanner(System.in);
       
        System.out.println("Enter Your Choice");
       int choice = in.nextInt();

       Connection connection = null;
       Statement statement = null;
       ResultSet resultSet = null;
       Integer noOfRows = null;

    try {

      
        String url = "jdbc:mysql://localhost:3306/crudproject";
       String userName = "root";
       String password = "root";

       connection = DriverManager.getConnection(url, userName, password);

       
    if (connection != null) {
        statement = connection.createStatement();
       if(choice == 1) {
               System.out.println("Enter the name of student");
               String name = in.next();
               System.out.println("Enter the age of student");
               Integer age = in.nextInt();
               System.out.println("Enter the marks of student");
               Integer marks = in.nextInt();

               String insertQuery = "insert into student (name, age, marks) values ('" + 
               name + "', " + age + ", " + marks + ");";

               noOfRows = statement.executeUpdate(insertQuery);
               System.out.println(noOfRows + " Student Record added successfully");


             
       } else if(choice == 2) {
             String selectQuery = "select id, name, age, marks from student;";
             resultSet = statement.executeQuery(selectQuery);

             if(resultSet != null) {
                 System.out.println("id\tname\tage\tmarks");
                while(resultSet.next()) {
                    System.out.println(
                       resultSet.getInt(1)     +  "\t" + 
                       resultSet.getString(2) + "\t"  +
                       resultSet.getInt(3)    +  "\t" +
                       resultSet.getInt(4));
                }
             }
       } else if(choice == 3) {
           
          System.out.println("1. To update a student Name");
          System.out.println("2. To update a student age");
          System.out.println("3. To update a student mark");

          System.out.println("Select Your Choice");
          int option = in.nextInt();
          String query;
          String sname;
    
          if(option == 1) {
              System.out.println("Enter the name to update");
              String name = in.next();

              System.out.println("Enter the existing name to replace with this current name");
              sname = in.next();

              query = "update student set name = '" + name + "' where name = '" + 
                     sname + "'";

              noOfRows = statement.executeUpdate(query);

              if(noOfRows == 0) 
                System.out.println("Invalid name -> no matching data found");
              else
               System.out.println(noOfRows + " name has updated success fully");

          } else if (option == 2) {

              System.out.println("Enter the age to update");
              int age = in.nextInt();

              System.out.println("Enter the existing name to replace with this current age");
              sname = in.next();

             query = "update student set age = " + age + " where name = '" + 
                     sname + "'";

              noOfRows = statement.executeUpdate(query);

              if(noOfRows == 0) 
                System.out.println("Invalid name -> no matching data found");
              else
               System.out.println(noOfRows + " age has updated success fully");

          } else if (option == 3) {

             System.out.println("Enter the mark to update");
             int mark = in.nextInt();

             System.out.println("Enter the existing name to replace with this current mark");
             sname = in.next();

             query = "update student set marks = " + mark + " where name = '" + 
                     sname + "'";

              noOfRows = statement.executeUpdate(query);

              if(noOfRows == 0) 
                System.out.println("Invalid name -> no matching data found");
              else
               System.out.println(noOfRows + " mark has updated success fully");

          } else {

             System.out.println("Invalid Option select from 1, 2, 3");
          }



       } else if(choice == 4) {
            System.out.println("Enter the name of a student to delete the record");
            String name = in.next();
            String deleteQuery = "delete from student where name = '" +
            name + "'";
            noOfRows = statement.executeUpdate(deleteQuery);
            if(noOfRows == 0)
              System.out.println("Invalid student name -> no matchig records found");
            else  
              System.out.println(noOfRows + " student data has deleted succesfully");
       } else {
           System.out.println("Invalid option");
       }
    }


    } catch (SQLException se) {
        se.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {

        try {
           
           if (resultSet != null) 
               resultSet.close();
            
           if (statement != null)
              statement.close();

           if (connection != null) 
              connection.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      
    }

}