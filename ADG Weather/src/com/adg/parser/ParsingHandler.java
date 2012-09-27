package com.adg.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ParsingHandler {
	//array
	private static final String DATA = "data";
	private static final String CURRENT = "current_condition";
	private static final String WEATHER = "weather";
	private static final String REQUEST = "request";
	private static final String DESC = "weatherDesc";
	
	//common
	private static final String PRECIPMM = "percipMM";
	private static final String WINDIR = "winddir16Point";
	private static final String WINDEGREE = "winddirDegree";
	private static final String KMPH = "windspeedKmph";
	private static final String MPH = "windspeedMiles";
	    
	private static final String VALUE = "value"; //part of weatherDesc array
	
	// under current
	private static final String CLOUDCOVER = "cloudcover";
	private static final String HUMIDITY = "humidity";
	private static final String TIME = "ovservation_time";
	private static final String PRESSURE = "pressure";
	private static final String C = "temp_C";
	private static final String F = "temp_F";
	private static final String VISIBILITY = "visibility";
	private static final String WEATHERCODE = "weatherCode";
	
	// under weather
	private static final String DATE = "date";
	private static final String MAXC = "tempMaxC";
	private static final String MAXF = "tempMaxF";
	private static final String MINC = "tempMinC";
	private static final String MINF = "tempMinF";
	
	// under request
	private static final String QUERY = "query";
	private static final String TYPE = "type";
	
	private String url = "";
	
	public ParsingHandler() {
		
	}
	
	public ParsingHandler(String u) {
		this.url = u;
	}
	
	public void startParsing(){
		if(url.equals("")) {
			Log.i("USER ERROR", "Initilize the URL");
		}
		else {
			JSONparser jParser;
			JSONObject json;
			JSONObject data;
			
			jParser = new JSONparser();
			data = null;
			
			json = jParser.getJSONFromUrl(url);
			try{
				data = json.getJSONObject(DATA);
				JSONArray current = data.getJSONArray(CURRENT);
				JSONArray weather = data.getJSONArray(WEATHER);
				JSONArray request = data.getJSONArray(REQUEST);
				JSONArray desc = data.getJSONArray(DESC);
				
				//current
				for(int i = 0; i < current.length() ; i++){
					JSONObject c = current.getJSONObject(i);
					
					//special
					String cloudCover = c.getString(CLOUDCOVER);
					String humidity = c.getString(HUMIDITY);
					String time = c.getString(TIME);
					String pressure = c.getString(PRESSURE);
					String tempC = c.getString(C);
					String tempF = c.getString(F);
					String visivility = c.getString(VISIBILITY);
					
					//shared
					String precip = c.getString(PRECIPMM);
					String code = c.getString(WEATHERCODE);
					
				}
				
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			}			
		}
	}
}