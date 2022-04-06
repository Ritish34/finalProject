package dao;

import java.sql.SQLException;
import java.util.List;

import model.Address;

public interface AddressDB {
	public boolean saveAddress(Address obj) throws ClassNotFoundException, SQLException;

	List<Address> getAddress(int userid) throws SQLException, ClassNotFoundException;
}
