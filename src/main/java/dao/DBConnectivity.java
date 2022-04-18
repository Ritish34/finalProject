package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DBConnectivity {

	private static DBConnectivity obj;
	public Connection connection;
	private final static Logger logger = LogManager.getLogger(DBConnectivity.class);
	private DBConnectivity() throws ClassNotFoundException, SQLException, IOException{  

	   // Getting ClassLoader obj
	   ClassLoader classLoader = this.getClass().getClassLoader();
	   // Getting resource(File) from class loader
	   File configFile=new File(classLoader.getResource("database.properties").getFile());
	 
	   InputStream inputStream = new FileInputStream(configFile);
	   BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8));
		
	  
	   	String classname = reader.readLine();
	   	String url = reader.readLine();
        String username = reader.readLine();
        String pass = reader.readLine();
        
        reader.close();
		//Database connection made
		Class.forName(classname);
		connection = DriverManager.getConnection(url,username,pass);
	}

	//method to return instance of class
	  public static DBConnectivity getObj() throws ClassNotFoundException, SQLException, IOException{
	    if (obj == null)
	    {
	      // if instance is null, initialize
	    	obj = new DBConnectivity ();
	    }
	    return obj;
	  }
	
//	//make Connection
//	public Connection getConnection() throws ClassNotFoundException, SQLException{
//		return connection;
//	}
	
	public void closeConnection() {
		try {
			connection.close();
		}
		catch(Exception e) {
			logger.debug(e);
		}
	}
}
