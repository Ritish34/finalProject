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
	public boolean checkDupEmail(String email) throws ClassNotFoundException, SQLException; 
	
	public User getUserRole(String email, String pass) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException ;
	
	public boolean saveUser(User user, ArrayList<Address> list,Part filePart) throws NoSuchAlgorithmException, ParseException,
	ClassNotFoundException, SQLException, IOException;
	
	public List<User> getAllUser() throws ClassNotFoundException, SQLException;
	
	public int deleteUserById(int UserId) throws ClassNotFoundException, SQLException ;
	
}
