package com.courier.model;


public class TrackUpdate {

	private Consignment consignment;
	private String currentDate;
	private City city;
	private int status;
	
	public TrackUpdate() {
		// TODO Auto-generated constructor stub
	}

	public TrackUpdate(Consignment consignment, String currentDate, City city, int status) {
		super();
		this.consignment = consignment;
		this.currentDate = currentDate;
		this.city = city;
		this.status = status;
	}

	public Consignment getConsignment() {
		return consignment;
	}

	public void setConsignment(Consignment consignment) {
		this.consignment = consignment;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TrackUpdate [consignment=" + consignment + ", currentDate=" + currentDate + ", city=" + city
				+ ", status=" + status + "]";
	}
	
}
