package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.AddressDB;
import dao.AddressDbImp;
import model.Address;

public class AddressServiceImp implements AddressService{

	@Override
	public boolean saveAddress(Address obj) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		AddressDB dao = new AddressDbImp();
			return dao.saveAddress(obj);
	}
	
	public boolean saveAdd(List<Address> list,int userid) throws ClassNotFoundException, SQLException,IOException {
		
		boolean flag = false;
		boolean flag1 = false;
		
		if(list.isEmpty()) {
			return flag;
		}
		else {
			AddressDB dao = new AddressDbImp();
			
			List<Address> add = dao.getAddress(userid);
			
			for (Address a : list) {
				a.setUserid(userid);
				
				flag1 = isSameAddress(add,a);
				
				if(!flag1) {
					flag = dao.saveAddress(a);
					if (!flag) {
						break;
					}
					else
					{
						add.add(a);
					}
				}
			}
			return flag;
		}
	}
	
	public boolean isSameAddress(List<Address> list,Address obj) {
		
		boolean flag = false;
		
		for(Address address : list) {
			if(address.getAddress().equals(obj.getAddress()))
				flag = true;
			if(address.getCity().equals(obj.getCity()))
				flag = true;
			if(address.getContry().equals(obj.getContry()))
				flag = true;
			if(address.getState().equals(obj.getState()))
				flag = true;
			if(address.getZip() ==obj.getZip())
				flag = true;
		}
		return flag;
	}

	@Override
	public List<Address> getUserAddress(int userid) throws ClassNotFoundException, SQLException,IOException {
		// TODO Auto-generated method stub
		AddressDB dao = new AddressDbImp();
		
		return dao.getAddress(userid);
	}
	
	@Override
	public boolean deleteAddress(String[] arr) throws NumberFormatException, ClassNotFoundException, SQLException,IOException {
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
	public boolean updateAddress(List<Address> list, List<Address> updatelist,int userid) throws ClassNotFoundException, SQLException, IOException {
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
