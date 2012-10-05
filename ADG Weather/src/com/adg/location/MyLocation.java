package com.adg.location;



public class MyLocation {
	
	long lng;
	long lat;
	String address;
	
	public MyLocation() {
		
	}
	
	public MyLocation(long lg, long lt, String adr) {
		this.lng = lg;
		this.lat = lt;
		this.address = adr;
	}

	public long getLng() {
		return lng;
	}

	public void setLng(long lng) {
		this.lng = lng;
	}

	public long getLat() {
		return lat;
	}

	public void setLat(long lat) {
		this.lat = lat;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
