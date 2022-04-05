package services;

import java.sql.SQLException;

import dao.AddressDB;
import dao.AddressDbImp;
import model.Address;

public class AddressServiceImp implements AddressService{

	@Override
	public boolean saveAddress(Address obj) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		AddressDB dao = new AddressDbImp();
			return dao.saveAddress(obj);
	}
}
