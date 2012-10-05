package com.adg.location;



public class MyLocation {
	
	long lng;
	long lat;
	String address;
	String country;
	
	public MyLocation() {
		
	}
	
	public MyLocation(long lg, long lt, String adr, String cou) {
		this.lng = lg;
		this.lat = lt;
		this.address = adr;
		this.country = cou;
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

	public String getCountry() {
		return country;
	}
	
	public void setCountry(String c) {
		this.country = c;
	}
	
	
}
