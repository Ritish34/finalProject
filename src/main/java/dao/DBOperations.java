package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DBOperations {

	public DBOperations() {

	}

	private final static Logger logger = LogManager.getLogger(DBOperations.class);

	public static boolean getData(String query) throws SQLException, ClassNotFoundException {

		PreparedStatement ps = DBConnectivity.getConnection().prepareStatement(query);
//		ResultSet rs=stmt.executeQuery(query);
		ResultSet rs = ps.executeQuery();

		BasicConfigurator.configure();
		boolean flag = rs.next();
		logger.info("Email is Already present or not =>" + flag);
		return flag;
	}

	public static int getUserid(String email) throws ClassNotFoundException, SQLException {
		String query = "select * from user where email=?";
		PreparedStatement ps = DBConnectivity.getConnection().prepareStatement(query);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		int id = 0;
		if (rs.next()) {
			id = rs.getInt("userid");
		}
		return id;
	}

	public static boolean addUserData(String fname, String lname, String email, String pass, Date date, String phone,
			String gender, String favlangs) throws ClassNotFoundException, SQLException, FileNotFoundException {
		// ,File file

		String query = "insert into user values(NULL,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = DBConnectivity.getConnection().prepareStatement(query);
		ps.setString(1, fname);
		ps.setString(2, lname);
		ps.setString(3, email);
		ps.setString(4, phone);
		ps.setString(5, gender);
		ps.setDate(6, date);
		ps.setString(7, favlangs);
		ps.setString(8, pass);
		ps.setString(9, null);
		// FileInputStream fis=new FileInputStream(file);
//		ps.setBinaryStream(9, (InputStream) fis, (int) (file.length()));
		ps.setString(10, "User");

		int num = ps.executeUpdate();
		if (num != 0)
			return true;
		else
			return false;
	}

	public static boolean addAddress(int id, String address, String zip, String city, String state, String contry)
			throws ClassNotFoundException, SQLException {

		String query = "insert into address values(NULL,?,?,?,?,?,?)";
		PreparedStatement ps = DBConnectivity.getConnection().prepareStatement(query);
		ps.setInt(1, id);
		ps.setString(2, address);
		ps.setString(3, zip);
		ps.setString(4, city);
		ps.setString(5, state);
		ps.setString(6, contry);

		int num = ps.executeUpdate();
		if (num != 0)
			return true;
		else
			return false;
	}
}
