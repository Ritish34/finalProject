package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
