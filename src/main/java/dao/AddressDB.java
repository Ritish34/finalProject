package dao;

import java.sql.SQLException;

import model.Address;

public interface AddressDB {
	public boolean saveAddress(Address obj) throws ClassNotFoundException, SQLException;
}
