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
	
	@Override
	public boolean deleteAddress(String[] arr) throws NumberFormatException, ClassNotFoundException, SQLException {
		boolean flag = false;
		if(arr.length == 0) {
			return flag;
		}
		for(String id : arr) {
			AddressDB dao = new AddressDbImp();
			
			flag = dao.deleteAddress(Integer.parseInt(id));
		}
		return flag;
	}
	
	@Override
	public boolean updateAddress(List<Address> list, List<Address> updatelist,int userid) throws ClassNotFoundException, SQLException {
		boolean flag = false;
			
		if(!list.isEmpty()) {
			AddressService ser = new AddressServiceImp();
			
			for (Address a : list) {
				a.setUserid(userid);
				flag = ser.saveAddress(a);
				if (!flag) {
					break;
				}
			}
		}
		
		if(!updatelist.isEmpty()) {
			AddressDB dao = new AddressDbImp();
			
			for (Address a : updatelist) {
				flag = dao.updateAddress(a);
				if (!flag) {
					break;
				}
			}
		}
		
		return flag;	
	}
}
