package services;

import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.DBOperations;

public class regdata {
	private final static Logger logger = LogManager.getLogger(regdata.class);
	
	public static boolean regUser(String fname,String lname,String email,String pass, String date,String phone,
			String gender,String favlangs,String file,String[] address,String[] zip,String[] city,
			String[] state,String[] contry) {
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd") ;
		String currentDateTime = format.format(date);
		
		return true;
	}
	
	private static int findUser(String email) throws ClassNotFoundException, SQLException {
		int id = DBOperations.getUserid(email);
		logger.debug("User Id "+id);
		return id;
	}
	
}
