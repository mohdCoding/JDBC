package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import com.jdbc.util.JdbcConnection;

public class CommitRollbackSavePoint {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement stmt = null;
		
		try {
			con = JdbcConnection.getJdbcConnection();
			if(con != null) {
				stmt = con.createStatement();
				
				if(stmt != null) {
					con.setAutoCommit(false);
					stmt.executeUpdate("insert into football (nation, name) values ('Portugal', 'Ronaldo')");
					stmt.executeUpdate("insert into football (nation, name) values ('Argentina', 'Messi')");
					stmt.executeUpdate("insert into football (nation, name) values ('Brazil', 'Neymar')");
					Savepoint sp = con.setSavepoint();
					stmt.executeUpdate("insert into football (nation, name) values ('serbia', 'son hueg min')");
					con.rollback(sp);
					stmt.executeUpdate("insert into football (nation, name) values ('france', 'Mbappe')");
					con.commit();
					
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
