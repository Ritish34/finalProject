package services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import model.Address;
import model.User;

public interface UserService {
	boolean checkDupEmail(String email) throws ClassNotFoundException, SQLException, IOException; 
	
	User getUserRole(String email, String pass) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, IOException ;
	
	boolean saveUser(User user, ArrayList<Address> list,Part filePart) throws NoSuchAlgorithmException, ParseException,
	ClassNotFoundException, SQLException, IOException;
	
	List<User> getAllUser() throws ClassNotFoundException, SQLException, IOException;
	
	int deleteUserById(int UserId) throws ClassNotFoundException, SQLException, IOException ;

	List<User> getUser(int userid) throws ClassNotFoundException, SQLException, IOException;

	int updateUser(User user, Part filePart) throws IOException, ClassNotFoundException, SQLException;

	boolean updatePassword(String pass, String email)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, IOException;
	
}
