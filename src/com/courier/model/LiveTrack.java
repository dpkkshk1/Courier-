package com.courier.model;

public class LiveTrack {

	private Consignment consignment;
	
	public LiveTrack() {
		// TODO Auto-generated constructor stub
	}

	public LiveTrack(Consignment consignment) {
		super();
		this.consignment = consignment;
	}

	public Consignment getConsignment() {
		return consignment;
	}

	public void setConsignment(Consignment consignment) {
		this.consignment = consignment;
	}

	@Override
	public String toString() {
		return "LiveTrack [consignment=" + consignment + "]";
	}
}