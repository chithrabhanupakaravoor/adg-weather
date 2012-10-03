package com.adg.object;

public class SearchObj {
	
	String latitude;
	String longitude;
	String population;
	String areaName;
	String contryName;
	String regionName;
	String WeatherUrlName;
	
	public SearchObj(){
		
	}
	public SearchObj(String latitude, String longitude, String population, String areaName,
			String contryName, String regionName, String WeatherUrlName){
		this.areaName = areaName;
		this.contryName = contryName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.population = population;
		this.regionName = regionName;
		this.WeatherUrlName = WeatherUrlName;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getPopulation() {
		return population;
	}
	public void setPopulation(String population) {
		this.population = population;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getContryName() {
		return contryName;
	}
	public void setContryName(String contryName) {
		this.contryName = contryName;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getWeatherUrlName() {
		return WeatherUrlName;
	}
	public void setWeatherUrlName(String weatherUrlName) {
		WeatherUrlName = weatherUrlName;
	}
	

}
