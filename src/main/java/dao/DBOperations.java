package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DBOperations {
	
	public DBOperations() {
		
	}
	private final static Logger logger = LogManager.getLogger(DBOperations.class);
	
	public static boolean getData(String query) throws SQLException, ClassNotFoundException {
		
		PreparedStatement ps = DBConnectivity.getConnection().prepareStatement(query);
//		ResultSet rs=stmt.executeQuery(query);
		ResultSet rs = ps.executeQuery();
		
		BasicConfigurator.configure();
		boolean flag = rs.next();
		logger.info("Email is Already present or not =>"+flag);
		return flag;
	}
}
