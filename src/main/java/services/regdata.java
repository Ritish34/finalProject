package services;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
			String[] state,String[] contry) throws ClassNotFoundException, SQLException, IOException, ParseException, NoSuchAlgorithmException {
		
		
		boolean flag1,flag2=false;
		
//		String pattern = "YYYY/MM/DD";
//	    SimpleDateFormat format = new SimpleDateFormat(pattern);
//	    Date date1 = (Date) format.parse(date);
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		Date myDate = (Date) sdf.parse(date);
//		sdf.applyPattern("yyyy/MM/dd");
//		Date date1 = Date.valueOf();
//		Date date = new Date(date);
		
//		java.sql.Date date1 = java.sql.Date.valueOf(date);
		java.sql.Date date1 = java.sql.Date.valueOf(date);
		
		pass = enctry(pass);
		
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
	
	private static String enctry(String ele) throws NoSuchAlgorithmException {
		 /* MessageDigest instance for MD5. */  
        MessageDigest m = MessageDigest.getInstance("MD5");  
          
        /* Add plain-text password bytes to digest using MD5 update() method. */  
        m.update(ele.getBytes());  
          
        /* Convert the hash value into bytes */   
        byte[] bytes = m.digest();  
          
        /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
        StringBuilder s = new StringBuilder();  
        for(int i=0; i< bytes.length ;i++)  
        {  
            s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
        }  
          
        /* Complete hashed password in hexadecimal format */  
        return (s.toString());
	}
}
