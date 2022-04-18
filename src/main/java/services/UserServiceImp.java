package services;

import java.io.FileInputStream;
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
	private final static Logger logger = LogManager.getLogger(UserServiceImp.class.getName());

	public boolean checkDupEmail(String email) throws ClassNotFoundException, SQLException, IOException {

		logger.info("inside userimp");
		logger.debug("email => " + email);

		UserDB dao = new UserDBimp();

		if (dao.checkEmail(email)) {
			logger.debug("Email is Duplicate");
			return true;
		} else {
			logger.debug("Email is not Duplicate");
			return false;
		}
	}

	public User getUserRole(String email, String pass) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, IOException {
		UserDB dao = new UserDBimp();
		
		User user = new User();
		
		Encryption en = new Encryption();
//
//		pass = en.encrypt(pass);
		
		// return User Object
		String hash = dao.getPass(email);
		
		if(hash != null) {
			if(en.decrypt(pass, hash)) {
				user = dao.getRole(email, hash);
			}
			else {
				user.setId(0);
				user.setRole(null);
			}
		}
		else {
			user.setId(0);
			user.setRole(null);
		}
		return user;
	}

	public boolean saveUser(User user, ArrayList<Address> list,Part filePart) throws NoSuchAlgorithmException, ParseException,
			ClassNotFoundException, SQLException, IOException {

		InputStream inputStream= filePart.getInputStream();
		
		
		Encryption en = new Encryption();

		user.setPassword(en.encrypt(user.getPassword()));

		// create object of dao
		UserDB dao = new UserDBimp();
		
		int num ;
		
		if(inputStream.available() == 0) {
//			File folderInput = new File("");//D://eclipse-workspace//FinalProject//src//main//resources//image/defualtimage.jpg
			try(InputStream newinputStream = new FileInputStream("D://eclipse-workspace//FinalProject//src//main//resources//image/defualtimage.jpg");){
				num = dao.saveUserData(user,newinputStream);	
			}
		}
		else {
			num = dao.saveUserData(user,inputStream);
		}
		logger.debug("Address Store Sucessfully ? " + num);

		if (num == 1) {
			int userid = dao.getUserid(user.getEmail());
			logger.debug(userid);

			boolean flag = false;
			
			AddressServiceImp ser = new AddressServiceImp();
			
			flag = ser.saveAdd(list, userid);

			logger.debug("Address Store Sucessfully ? " + flag);
			return flag;

		} else {
			return false;
		}

	}

	@Override
	public List<User> getAllUser() throws ClassNotFoundException, SQLException, IOException {
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
	
	@Override
	public int deleteUserById(int UserId) throws ClassNotFoundException, SQLException, IOException {
		UserDB dao = new UserDBimp();
		
		return dao.deleteUserById(UserId);
	}
	
	@Override
	public int updateUser(User user, Part filePart) throws IOException, ClassNotFoundException, SQLException {
		
		boolean flag = false;
		
		InputStream inputStream= filePart.getInputStream();
		
		UserDB dao = new UserDBimp();
		
		flag = dao.updateUser(user);
		if(inputStream.available() != 0) {
			flag = dao.setImage(user.getEmail(), inputStream);
		}
		
		if(flag) {
			return dao.getUserid(user.getEmail());
		}
		else {
			return -1;
		}
	}
	
	@Override
	public boolean updatePassword(String pass, String email) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, IOException {
		
		Encryption en = new Encryption();
		
		UserDB dao = new UserDBimp();
		
		return dao.updatePassword(en.encrypt(pass), email);
	}
}
