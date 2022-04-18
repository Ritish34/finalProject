package dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.Address;

public interface AddressDB {
	boolean saveAddress(Address obj) throws ClassNotFoundException, SQLException,IOException;

	List<Address> getAddress(int userid) throws SQLException, ClassNotFoundException,IOException;

	boolean deleteAddress(int id) throws ClassNotFoundException, SQLException,IOException;

	boolean updateAddress(Address obj) throws ClassNotFoundException, SQLException,IOException;
}
