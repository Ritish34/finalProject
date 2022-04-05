package dao;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import model.Address;
import model.User;

public class UserDBimp implements UserDB {

	private final static Logger logger = LogManager.getLogger(UserDB.class);
	
	@Override
	public boolean checkEmail(String query) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ps = DBConnectivity.getConnection().prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		BasicConfigurator.configure();
		boolean flag = rs.next();
		logger.info("Email is Already present or not =>" + flag);
		return flag;
	}

	@Override
	public int getUserid(String email) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
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

	@Override
	public int saveUserData(User obj,InputStream image) throws ClassNotFoundException, SQLException, FileNotFoundException {
		// TODO Auto-generated method stub
		String query = "insert into user(firstname,lastname,email,phone,gender,dob,lang,password,role,picture) values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = DBConnectivity.getConnection().prepareStatement(query);
		ps.setString(1, obj.getFname());
		ps.setString(2, obj.getLname());
		ps.setString(3, obj.getEmail());
		ps.setString(4, obj.getPhone());
		ps.setString(5, obj.getGender());
		ps.setString(6, obj.getDob());
		ps.setString(7, obj.getLang());
		ps.setString(8, obj.getPassword());
		ps.setString(9, obj.getRole());
		ps.setBlob(10, image);

		int num = ps.executeUpdate();
		return num;
	}

	@Override
	public boolean saveAddress(Address obj) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String query = "insert into address values(NULL,?,?,?,?,?,?)";
		PreparedStatement ps = DBConnectivity.getConnection().prepareStatement(query);
		ps.setInt(1, obj.getUserid());
		ps.setString(2, obj.getAddress());
		ps.setInt(3, obj.getZip());
		ps.setString(4, obj.getCity());
		ps.setString(5, obj.getState());
		ps.setString(6, obj.getContry());

		int num = ps.executeUpdate();
		if (num != 0)
			return true;
		else
			return false;
	}

	@Override
	public User getRole(String email, String pass) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String query = "select * from user where email=? and password = ?";
		PreparedStatement ps = DBConnectivity.getConnection().prepareStatement(query);
		
		ps.setString(1, email);
		ps.setString(2, pass);
		
		ResultSet rs = ps.executeQuery();
		
		User user = new User();
		if(rs.next()) {
			
			user.setId(rs.getInt("userid"));
			user.setRole(rs.getString("role"));
			user.setEmail(rs.getString("email"));
			user.setFname(rs.getString("firstname"));
		}
		else {
			user.setId(0);
			user.setRole(null);
		}
		
		return user;
	}

	@Override
	public List<User> getAllUser() throws SQLException, ClassNotFoundException {
		List<User> list = new ArrayList<User>();

			PreparedStatement ps = null;
			ResultSet rs = null;
			
				String sql = "SELECT * FROM user where Role=?";
				ps = DBConnectivity.getConnection().prepareStatement(sql);
				ps.setString(1, "User");
				ps.executeQuery();
				rs = ps.executeQuery();
				while (rs.next()) {
					User user = new User();
					
					user.setId(rs.getInt(1));
					user.setFname(rs.getString(2));
					user.setLname(rs.getString(3));
					user.setEmail(rs.getString(4));
					user.setPhone(rs.getString(5));
					user.setGender(rs.getString(6));
					user.setDob(rs.getString(7));
					user.setLang(rs.getString(8));
					user.setRole(rs.getString("role"));
					list.add(user);
				}

		return list;
	}
	
	public int deleteUserById(int UserId) throws SQLException, ClassNotFoundException {
		PreparedStatement ps = null;
				String sql = "DELETE FROM user WHERE UserId=?";
				ps = DBConnectivity.getConnection().prepareStatement(sql);
				ps.setInt(1, UserId);
				int num = ps.executeUpdate();
				System.out.println("done");
				return num;
	}
}
