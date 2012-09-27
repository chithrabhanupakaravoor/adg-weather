package com.adg.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONparser {
	/*
	 * String key = "845adebec4142346121409";
	 * String longLat = "http://free.worldweatheronline.com/feed/weather.ashx?q=[lat],[lon]&format=json&num_of_days=5&key=";
	 * String zip ="http://free.worldweatheronline.com/feed/weather.ashx?q=[Zipcode]&format=json&num_of_days=5&key=";
	 * String city = "http://free.worldweatheronline.com/feed/weather.ashx?q=[city]%2c+[state],[contry]&format=json&num_of_days=5&key=";
	 * String ip = "http://free.worldweatheronline.com/feed/weather.ashx?q=[ip]&format=json&num_of_days=5&key=";
	 */
	static InputStream inputStream = null;
	static JSONObject jObj = null;
	static String json = "";
	
	public JSONparser() {
		
	}

	public JSONObject getJSONFromUrl(String url) {
		//Log.i("URL", url);
        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
 
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();           
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            inputStream.close();
            //Log.i("RESULT", sb.toString());
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
 
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
 
        // return JSON String
        return jObj;
 
    }
	
}
