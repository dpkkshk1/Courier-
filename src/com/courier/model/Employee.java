package com.courier.model;


public class Employee {

	private int employeeId;
	private String password;
	private String employeeName;
	private String dob;
	private String emailId;
	private String mobile;
	private Address address;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String password, String employeeName, String dob, String emailId, String mobile, Address address) {
		super();
		this.password = password;
		this.employeeName = employeeName;
		this.dob = dob;
		this.emailId = emailId;
		this.mobile = mobile;
		this.address = address;
	}

	public Employee(int employeeId, String password, String employeeName, String dob, String emailId,
			String mobile, Address address) {
		super();
		this.employeeId = employeeId;
		this.password = password;
		this.employeeName = employeeName;
		this.dob = dob;
		this.emailId = emailId;
		this.mobile = mobile;
		this.address = address;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", password=" + password + ", employeeName=" + employeeName
				+ ", dob=" + dob + ", emailId=" + emailId + ", mobile=" + mobile + ", address=" + address + "]";
	}
	
	
	
}
