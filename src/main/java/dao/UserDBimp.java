package dao;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
	
	public List<User> getUser(int userid) throws ClassNotFoundException, SQLException, IOException{
		List<User> list = new ArrayList<User>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
			String sql = "SELECT * FROM user where userid=?";
			ps = DBConnectivity.getConnection().prepareStatement(sql);
			ps.setInt(1, userid);
			ps.executeQuery();
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Blob blob = rs.getBlob("picture");

				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				byte[] imageBytes = outputStream.toByteArray();
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);

				inputStream.close();
				outputStream.close();
				
				User user = new User();
				user.setId(rs.getInt(1));
				user.setFname(rs.getString(2));
				user.setLname(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPhone(rs.getString(5));
				user.setGender(rs.getString(6));
				user.setDob(rs.getString(7));
				user.setLang(rs.getString(8));
//				user.setImage(rs.getBlob(10));
				user.setBase64Image(base64Image);
				user.setRole(rs.getString("role"));//role is field name
				list.add(user);
			}

	return list;

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
					user.setRole(rs.getString("role"));//role is field name
					list.add(user);
				}

		return list;
	}
	
	@Override
	public int deleteUserById(int UserId) throws SQLException, ClassNotFoundException {
		PreparedStatement ps = null;
				String sql = "DELETE FROM user WHERE UserId=?";
				ps = DBConnectivity.getConnection().prepareStatement(sql);
				ps.setInt(1, UserId);
				int num = ps.executeUpdate();
				System.out.println("done");
				return num;
	}
	
	@Override
	public boolean updateUser(User user,InputStream image) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE user SET firstname=?,lastname=?,gender=?,dob=?,lang=?,picture=? WHERE (email = ?)";
		PreparedStatement ps = DBConnectivity.getConnection().prepareStatement(sql);
		ps.setString(1, user.getFname());
		ps.setString(2, user.getLname());
		ps.setString(3, user.getGender());
		ps.setString(4, user.getDob());
		ps.setString(5, user.getLang());
		ps.setBlob(6, image);
		ps.setString(7, user.getEmail());
		
		int num = ps.executeUpdate();
		
		if (num !=0)
			return true;
		else
			return false;
	}
}
