package services;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.UserDB;
import dao.UserDBimp;
import model.User;
import util.Encryption;
import model.Address;

public class UserServiceImp implements UserService {
	// logger
	private final static Logger logger = LogManager.getLogger(UserServiceImp.class);

	public boolean checkDupEmail(String email) throws ClassNotFoundException, SQLException {

		logger.info("inside userimp");
		logger.debug("email => " + email);

		UserDB dao = new UserDBimp();

		if (dao.checkEmail("select * from user where email='" + email + "';")) {
			logger.debug("Email is Duplicate");
			return true;
		} else {
			logger.debug("Email is not Duplicate");
			return false;
		}
	}

	public User getUserRole(String email, String pass) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		UserDB dao = new UserDBimp();
		
		Encryption en = new Encryption();

		pass = en.enctry(pass);
		
		// return User Object
		User user = dao.getRole(email, pass);
		return user;
	}

	public boolean saveUser(User user, ArrayList<Address> list,Part filePart) throws NoSuchAlgorithmException, ParseException,
			ClassNotFoundException, SQLException, IOException {

		InputStream inputStream= filePart.getInputStream();
		
		Encryption en = new Encryption();

		user.setPassword(en.enctry(user.getPassword()));

		// create object of dao
		UserDB dao = new UserDBimp();

		int num = dao.saveUserData(user,inputStream);
		logger.debug("Address Store Sucessfully ? " + num);

		if (num == 1) {
			int userid = dao.getUserid(user.getEmail());
			logger.debug(userid);

			boolean flag = false;
			
			AddressService ser = new AddressServiceImp();

			for (Address a : list) {
				a.setUserid(userid);
				flag = ser.saveAddress(a);
				if (!flag) {
					break;
				}
			}

			logger.debug("Address Store Sucessfully ? " + flag);
			return flag;

		} else {
			return false;
		}

	}

	@Override
	public List<User> getAllUser() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		UserDB dao = new UserDBimp();
		
		return dao.getAllUser();
	}
	
	@Override
	public List<User> getUser(int userid) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		UserDB dao = new UserDBimp();
		
		return dao.getUser(userid);
	}
	
	public int deleteUserById(int UserId) throws ClassNotFoundException, SQLException {
		UserDB dao = new UserDBimp();
		
		return dao.deleteUserById(UserId);
	}
}
