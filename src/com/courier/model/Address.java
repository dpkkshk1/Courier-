package com.courier.model;

public class Address {

	private int addressId;
	private String street;
	private String pincode;
	private City city;
	public Address() {
		// TODO Auto-generated constructor stub
	}
	
	public Address(String street, String pincode, City city) {
		super();
		this.street = street;
		this.pincode = pincode;
		this.city = city;
	}

	public Address(int addressId, String street, String pincode, City city) {
		super();
		this.addressId = addressId;
		this.street = street;
		this.pincode = pincode;
		this.city = city;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", street=" + street + ", pincode=" + pincode + ", city=" + city
				+ "]";
	}
	
}
