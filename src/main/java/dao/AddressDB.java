package dao;

import java.sql.SQLException;
import java.util.List;

import model.Address;

public interface AddressDB {
	public boolean saveAddress(Address obj) throws ClassNotFoundException, SQLException;

	public List<Address> getAddress(int userid) throws SQLException, ClassNotFoundException;

	public boolean deleteAddress(int id) throws ClassNotFoundException, SQLException;

	boolean updateAddress(Address obj) throws ClassNotFoundException, SQLException;
}
