package services;

import java.sql.SQLException;
import java.util.List;

import model.Address;

public interface AddressService {
	public boolean saveAddress(Address obj) throws ClassNotFoundException, SQLException;

	public List<Address> getUserAddress(int userid) throws ClassNotFoundException, SQLException;
}
