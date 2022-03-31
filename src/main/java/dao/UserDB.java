package dao;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import model.Address;
import model.User;

public interface UserDB {
	public boolean checkEmail(String query) throws ClassNotFoundException, SQLException;
	
	public int getUserid(String email) throws ClassNotFoundException, SQLException;
	
	public int saveUserData(User obj) throws ClassNotFoundException, SQLException, FileNotFoundException;
	
	public boolean saveAddress(Address obj) throws ClassNotFoundException, SQLException;
	
	public User getRole(String email,String pass) throws ClassNotFoundException, SQLException ;

	
}
