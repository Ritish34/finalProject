package services;

import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.UserDBimp;

public class checkEmail {
	private final static Logger logger = LogManager.getLogger(checkEmail.class);
	
	public static boolean checkDup(String email) throws ClassNotFoundException, SQLException {
		
		logger.info("email = "+email);
//		//DBOperations.getData("select * from user where email='"+email+"';")
//		
//		//making object 
//		UserDBimp dao = new UserDBimp();
//		
//		if(dao.checkEmail("select * from user where email='"+email+"';")) {
//			logger.info("Email is Duplicate");
//			return true;
//		}
//		else {
//			logger.info("Email is not Duplicate");
//			return false;
//		}
		return false;
	}
}
