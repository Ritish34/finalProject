package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Address;

public class AddressDbImp implements AddressDB {
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
	public List<Address> getAddress(int userid) throws SQLException, ClassNotFoundException {
		String query = "select * from address where userid = ?";
		PreparedStatement ps = DBConnectivity.getConnection().prepareStatement(query);
		ps.setInt(1, userid);
		ResultSet rs = ps.executeQuery();
		
		List<Address> list = new ArrayList<Address>();
		
		while(rs.next()) {
			Address obj = new Address();
			obj.setAddressid(rs.getInt(1));
			obj.setUserid(userid);
			obj.setAddress(rs.getString(3));
			obj.setZip(rs.getInt(4));
			obj.setCity(rs.getString(5));
			obj.setState(rs.getString(6));
			obj.setContry(rs.getString(7));
			list.add(obj);
		}
		
		return list;
	}
}
