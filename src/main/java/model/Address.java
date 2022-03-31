package model;

public class Address {
	int userid;
	String address;
	int zip;
	String city;
	String state;
	String contry;
	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @return the zip
	 */
	public int getZip() {
		return zip;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @return the contry
	 */
	public String getContry() {
		return contry;
	}
	
	//-----------------------------------------------------------------------------------------------
	
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @param zip the zip to set
	 */
	public void setZip(int zip) {
		this.zip = zip;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @param contry the contry to set
	 */
	public void setContry(String contry) {
		this.contry = contry;
	}
}
