package com.courier.model;

public class ExchangePoints {

	private int exchangePointId;
	private City city;

	public ExchangePoints() {
		// TODO Auto-generated constructor stub
	}

	public ExchangePoints(int exchangePointId, City city) {
		super();
		this.exchangePointId = exchangePointId;
		this.city = city;
	}

	public int getExchangePointId() {
		return exchangePointId;
	}

	public void setExchangePointId(int exchangePointId) {
		this.exchangePointId = exchangePointId;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "ExchangePoints [exchangePointId=" + exchangePointId + ", city=" + city + "]";
	}
	
}
