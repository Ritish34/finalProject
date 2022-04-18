package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import model.User;

public interface UserDB {
	boolean checkEmail(String query) throws ClassNotFoundException, SQLException,IOException;
	
	int getUserid(String email) throws ClassNotFoundException, SQLException,IOException;
	
	int saveUserData(User obj,InputStream image) throws ClassNotFoundException, SQLException, FileNotFoundException,IOException;
	
	User getRole(String email,String pass) throws ClassNotFoundException, SQLException,IOException ;

	List<User> getAllUser() throws SQLException, ClassNotFoundException,IOException;
	
	int deleteUserById(int UserId) throws SQLException, ClassNotFoundException,IOException;
	
	List<User> getUser(int userid) throws ClassNotFoundException, SQLException, IOException;

	InputStream getImage(String email) throws SQLException, ClassNotFoundException, IOException;

	boolean updateUser(User user) throws ClassNotFoundException, SQLException,IOException;

	boolean setImage(String email, InputStream image) throws SQLException, ClassNotFoundException,IOException;

	boolean updatePassword(String pass, String email) throws ClassNotFoundException, SQLException, IOException;

	String getPass(String email) throws SQLException, ClassNotFoundException,IOException;
}
