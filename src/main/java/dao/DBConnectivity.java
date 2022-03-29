package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectivity {

	private static Connection connection=null;
	private DBConnectivity() {

	}
	//make Connection
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		if(connection == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalprojectdb","root","pass");
			
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedatabase","root","pass");
		}
		return connection;
	}
	
	public static void closeConnection() {
		try {
			connection.close();
		}
		catch(Exception e) {
			
		}
	}
	
	
}
