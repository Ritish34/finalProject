package services;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.Part;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.DBOperations;

public class regdata {
	private final static Logger logger = LogManager.getLogger(regdata.class);
	//,String file
	public static boolean regUser(String fname,String lname,String email,String pass, String date,String phone,
			String gender,String favlangs,String[] address,String[] zip,String[] city,
			String[] state,String[] contry) throws ClassNotFoundException, SQLException, IOException, ParseException {
		
		
		boolean flag1,flag2=false;
		
//		String pattern = "YYYY/MM/DD";
//	    SimpleDateFormat format = new SimpleDateFormat(pattern);
//	    Date date1 = (Date) format.parse(date);
		Date date1 = Date.valueOf(date);
		logger.info(date1);
//		File image= new File(file);
		
		flag1 = DBOperations.addUserData(fname, lname, email, pass, date1, phone, gender, favlangs);//image
		logger.debug("User Store Sucessfully ? "+flag1);
		
		if(flag1) {
			int userid = findUser(email);
			logger.debug(userid);
			
			if(userid !=0) {
				for(int i=0; i<address.length; i++) {
					flag2 = DBOperations.addAddress(userid, address[i], zip[i], city[i], state[i], contry[i]);
					if(!flag2) {
						break;
					}
				}
			}
				logger.debug("Address Store Sucessfully ? "+flag2);
				return flag2;
			
		}
		else {
			return false;
		}
		
	}
	
	private static int findUser(String email) throws ClassNotFoundException, SQLException {
		int id = DBOperations.getUserid(email);
		logger.debug("User Id "+id);
		return id;
	}
	
}
