package services;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.UserDBimp;
import model.User;
import util.Encryption;
import model.Address;

public class UserService {
	//logger 
	private final static Logger logger = LogManager.getLogger(checkEmail.class);
	
	public boolean checkDupEmail(String email) throws ClassNotFoundException, SQLException{
		
		logger.info("inside userimp");
		logger.info("email => "+email);
		//DBOperations.getData("select * from user where email='"+email+"';")
		
		UserDBimp dao = new UserDBimp();
		
		if(dao.checkEmail("select * from user where email='"+email+"';")) {
			logger.info("Email is Duplicate");
			return true;
		}
		else {
			logger.info("Email is not Duplicate");
			return false;
		}
	}
	
	public User getUserRole(String email,String pass) throws ClassNotFoundException, SQLException {
		UserDBimp dao = new UserDBimp();
		//return User Object
		User user = dao.getRole(email, pass);
		return user;
	}
	
	public boolean saveUser(User user,ArrayList<Address> list) throws NoSuchAlgorithmException, ParseException, ClassNotFoundException, SQLException, FileNotFoundException {
		
		Encryption en = new Encryption();
		
		user.setPassword(en.enctry(user.getPassword()));
		
		//create object of dao
		UserDBimp dao = new UserDBimp();
		
		int num = dao.saveUserData(user);
		logger.debug("Address Store Sucessfully ? "+num);
		
		if(num == 1) {
			int userid = dao.getUserid(user.getEmail());
			logger.debug(userid);
			
			boolean flag = false;
			
			for(Address a : list) {
				a.setUserid(userid);
				flag = dao.saveAddress(a);
				if(!flag) {
					break;
				}
			}
			
			logger.debug("Address Store Sucessfully ? "+flag);
			return flag;
			
		}
		else {
			return false;
		}
		
	}
}
