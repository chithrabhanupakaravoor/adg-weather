package com.adg.parser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.adg.object.SearchObj;

import android.os.Bundle;
import android.util.Log;

public class SearchParsingHandler {
	//start
	private static final String SEARCH_API = "search_api";
	//main array
	private static final String RESULT = "result";
	//object
	private static final String LAT = "latitude";
	private static final String LONG = "longitude";
	private static final String POP = "population";
	//other arrays
	private static final String AREA = "areaName";
	private static final String COUNTRY = "country";
	private static final String REGION = "region";
	private static final String WEATHERURL = "weatherUrl";
	//object in all other arrays
	private static final String VALUE = "value";
	
	
	String start = "http://www.worldweatheronline.com/feed/search.ashx?";
	String key = "key=845adebec4142346121409";
	String query = "&query=";
	String end = "&num_of_results=3&format=json";
	String location = "";
	ArrayList<SearchObj> so = new ArrayList<SearchObj>();
	
	public SearchParsingHandler(){
	}
	
	public SearchParsingHandler(String L){
		this.location = L;
	}
	
	public void startParsing(){
		if(location.equals("")) {
			Log.i("USER ERROR", "Initilize the Location");
		}
		else {
			String url = start + key + query + location + end;
			Log.i("Search url", url);
			JSONparser jParser;
			JSONObject json;
			JSONObject search;
			
			jParser = new JSONparser();
			search = null;
			
			json = jParser.getJSONFromUrl(url);
			try{
				search = json.getJSONObject(SEARCH_API);
				JSONArray result = search.getJSONArray(RESULT);
				for(int i = 0; i < result.length(); i++){
					//SearchObj
					SearchObj sear = new SearchObj();
					//main array
					JSONObject r = result.getJSONObject(i);
					
					//arrays
					JSONArray area = r.getJSONArray(AREA);
					JSONArray country = r.getJSONArray(COUNTRY);
					JSONArray region = r.getJSONArray(REGION);
					JSONArray weatherUrl = r.getJSONArray(WEATHERURL);
					
					//objects
					String latitude = r.getString(LAT);
					String longitude = r.getString(LONG);
					String population = r.getString(POP);
					String areaName = forLoopArray(area);
					String contryName = forLoopArray(country);
					String regionName = forLoopArray(region);
					String WeatherUrlName = forLoopArray(weatherUrl);
					
					sear.setAreaName(areaName);
					sear.setContryName(contryName);
					sear.setLatitude(latitude);
					sear.setLongitude(longitude);
					sear.setPopulation(population);
					sear.setRegionName(regionName);
					sear.setWeatherUrlName(WeatherUrlName);
					
					so.add(sear);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
	}
	public String forLoopArray(JSONArray ja){
		String js = "";
		try{
		for(int j = 0; j< ja.length(); j++){
			JSONObject a = ja.getJSONObject(j);
			js = a.getString(VALUE);
		}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return js;
	}
	
	public Bundle getSeachBundle(int position){
		Bundle bundle = new Bundle();
		bundle.putString( "latitude", so.get(position).getLatitude());
		bundle.putString("longitude", so.get(position).getLongitude());
		bundle.putString("population", so.get(position).getPopulation());
		bundle.putString("areaName", so.get(position).getAreaName());
		bundle.putString("contryName", so.get(position).getContryName());
		bundle.putString("regionName", so.get(position).getRegionName());
		bundle.putString("WeatherUrlName", so.get(position).getWeatherUrlName());
		return bundle;
	}

	public ArrayList<SearchObj> getSo() {
		return so;
	}

	public void setSo(ArrayList<SearchObj> so) {
		this.so = so;
	}
	
}
