package com.courier.model;

public class Customer {

	private int customerId;
	private String password;
	private String customerName;
	private String mobile;
	private String email;
	private Address address;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String password, String customerName, String mobile, String email, Address address) {
		super();
		this.password = password;
		this.customerName = customerName;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
	}

	public Customer(int customerId, String password, String customerName, String mobile, String email,
			Address address) {
		super();
		this.customerId = customerId;
		this.password = password;
		this.customerName = customerName;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", password=" + password + ", customerName=" + customerName
				+ ", mobile=" + mobile + ", email=" + email + ", address=" + address + "]";
	}
	
}
