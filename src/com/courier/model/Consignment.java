package com.courier.model;

public class Consignment {

	private int consignmentId;
	private String accptedDate;
	private double packageWeight;
	private double cost;
	private Address addressFrom;
	private Address addressTo;
	private Employee employee;
	private Customer customer;

	public Consignment() {
		// TODO Auto-generated constructor stub
	}

	public Consignment(int consignmentId, String accptedDate, double packageWeight, double cost, Address addressFrom,
			Address addressTo, Employee employee, Customer customer) {
		super();
		this.consignmentId = consignmentId;
		this.accptedDate = accptedDate;
		this.packageWeight = packageWeight;
		this.cost = cost;
		this.addressFrom = addressFrom;
		this.addressTo = addressTo;
		this.employee = employee;
		this.customer = customer;
	}
	

	public Consignment(String accptedDate, double packageWeight, double cost, Address addressFrom, Address addressTo,
			Employee employee, Customer customer) {
		super();
		this.accptedDate = accptedDate;
		this.packageWeight = packageWeight;
		this.cost = cost;
		this.addressFrom = addressFrom;
		this.addressTo = addressTo;
		this.employee = employee;
		this.customer = customer;
	}

	public int getConsignmentId() {
		return consignmentId;
	}

	public void setConsignmentId(int consignmentId) {
		this.consignmentId = consignmentId;
	}

	public String getAccptedDate() {
		return accptedDate;
	}

	public void setAccptedDate(String accptedDate) {
		this.accptedDate = accptedDate;
	}

	public double getPackageWeight() {
		return packageWeight;
	}

	public void setPackageWeight(double packageWeight) {
		this.packageWeight = packageWeight;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Address getAddressFrom() {
		return addressFrom;
	}

	public void setAddressFrom(Address addressFrom) {
		this.addressFrom = addressFrom;
	}

	public Address getAddressTo() {
		return addressTo;
	}

	public void setAddressTo(Address addressTo) {
		this.addressTo = addressTo;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Consignment [consignmentId=" + consignmentId + ", accptedDate=" + accptedDate + ", packageWeight="
				+ packageWeight + ", cost=" + cost + ", addressFrom=" + addressFrom + ", addressTo=" + addressTo
				+ ", employee=" + employee + ", customer=" + customer + "]";
	}

}
