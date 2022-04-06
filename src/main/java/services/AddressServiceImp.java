package services;

import java.sql.SQLException;
import java.util.List;

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

	@Override
	public List<Address> getUserAddress(int userid) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		AddressDB dao = new AddressDbImp();
		
		return dao.getAddress(userid);
	}
}
