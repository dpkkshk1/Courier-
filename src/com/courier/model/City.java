package com.courier.model;

public class City {

	private int cityId;
	private String cityName;
	private State state;
	public City() {
		// TODO Auto-generated constructor stub
	}
	public City(int cityId, String cityName, State state) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.state = state;
	}
	
	public City(int cityId, State state) {
		super();
		this.cityId = cityId;
		this.state = state;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", cityName=" + cityName + ", state=" + state + "]";
	}
	
}
