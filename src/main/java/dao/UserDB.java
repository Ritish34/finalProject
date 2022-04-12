package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import model.User;

public interface UserDB {
	public boolean checkEmail(String query) throws ClassNotFoundException, SQLException;
	
	public int getUserid(String email) throws ClassNotFoundException, SQLException;
	
	public int saveUserData(User obj,InputStream image) throws ClassNotFoundException, SQLException, FileNotFoundException;
	
	public User getRole(String email,String pass) throws ClassNotFoundException, SQLException ;

	public List<User> getAllUser() throws SQLException, ClassNotFoundException;
	
	public int deleteUserById(int UserId) throws SQLException, ClassNotFoundException;
	
	public List<User> getUser(int userid) throws ClassNotFoundException, SQLException, IOException;

	InputStream getImage(String email) throws SQLException, ClassNotFoundException;

	boolean updateUser(User user) throws ClassNotFoundException, SQLException;

	boolean setImage(String email, InputStream image) throws SQLException, ClassNotFoundException;

	boolean updatePassword(String pass, String email) throws ClassNotFoundException, SQLException;

	String getPass(String email) throws SQLException, ClassNotFoundException;
}
