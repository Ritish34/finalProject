package services;

import java.sql.SQLException;

import dao.DBOperations;

public class CheckLoginUser {
	public static String getUser(String email,String pass) throws ClassNotFoundException, SQLException {
		//return role
		String role = DBOperations.getRole(email,pass);
		return role;
	}
}
