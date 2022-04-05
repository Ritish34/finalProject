package services;

import java.sql.SQLException;

import model.Address;

public interface AddressService {
	public boolean saveAddress(Address obj) throws ClassNotFoundException, SQLException;
}
