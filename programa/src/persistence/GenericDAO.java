package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDAO {
	
	private Connection connection;
	
	public Connection getConnection(String dbName) throws SQLException, ClassNotFoundException {
		
		String hostName = "localhost";
		String userName = "sa";
		String password = "123456";
		
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		connection = DriverManager.getConnection(
				String.format("jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s",
						hostName, dbName, userName, password)
				);
		return connection;
	}
	

}
