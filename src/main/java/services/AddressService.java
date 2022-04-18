package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.Address;

public interface AddressService {
	boolean saveAddress(Address obj) throws ClassNotFoundException, SQLException, IOException;

	List<Address> getUserAddress(int userid) throws ClassNotFoundException, SQLException, IOException;

	boolean deleteAddress(String[] arr) throws NumberFormatException, ClassNotFoundException, SQLException, IOException;

	boolean updateAddress(List<Address> list, List<Address> updatelist, int userid)
			throws ClassNotFoundException, SQLException, IOException;
}
