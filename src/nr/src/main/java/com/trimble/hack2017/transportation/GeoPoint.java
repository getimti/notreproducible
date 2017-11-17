package com.trimble.hack2017.transportation;

public class GeoPoint {

	private double lat;	
	private double lng;
	private String name;

	public GeoPoint() {

	}
	
	public GeoPoint(double lat, double lng) {
		this.lat=lat;
		this.lng=lng;
	}
	
	public GeoPoint(double lat, double lng, String name) {
		this.lat=lat;
		this.lng=lng;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

}