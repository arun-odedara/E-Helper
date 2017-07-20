package com.example.emergency.bean;

public class Location {

	private double lat;
	private double lang;
	private String currentaddress;
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLang() {
		return lang;
	}
	public void setLang(double lang) {
		this.lang = lang;
	}
	public String getCurrentaddress() {
		return currentaddress;
	}
	public void setCurrentaddress(String currentaddress) {
		this.currentaddress = currentaddress;
	}
	public Location(double lat, double lang, String currentaddress) {
		super();
		this.lat = lat;
		this.lang = lang;
		this.currentaddress = currentaddress;
	}
	
	
}
