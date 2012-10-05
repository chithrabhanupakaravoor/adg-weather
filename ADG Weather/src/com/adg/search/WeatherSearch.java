package com.adg.search;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.adg.parser.ParsingHandler;
import com.adg.weather.R;

public class WeatherSearch extends Activity {
	
	static final String BASE_URL = "http://free.worldweatheronline.com/feed/weather.ashx?";
	String searchFor = "q=";
	static final String FORMAT = "&format=json";
	static final String NUM_DAYS = "&num_of_days=5";
	static final String API_KEY = "&key=845adebec4142346121409";
	String city = "";
	String zip = "";
	
	TextView cloudTextView;
	TextView humidityTextView;
	TextView timeTextView;
	TextView precipTextView;
	TextView tempCTextView;
	TextView tempFTextView;
	TextView visibilityTextView;
	TextView codeTextView;
	TextView valueTextView;
	TextView windPointTextView;
	TextView windDegreeTextView;
	TextView kmphTextView;
	TextView mphTextView;
	
	ParsingHandler ph;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_dialog_box);
        Bundle bun = getIntent().getExtras();
        city=(String) bun.get("city");
        zip=(String) bun.getString("zip");
        GetWeatherSearch search = new GetWeatherSearch(getApplicationContext());
        search.execute((Integer)null);
    }
	
	class GetWeatherSearch extends AsyncTask{
		
		Context context;
		
		public GetWeatherSearch(Context context){
			this.context = context;
		}
	
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if(!city.equals("")){
			searchFor += city;
			}else{
			searchFor += zip;
			}
			
			cloudTextView = (TextView)findViewById(R.id.textCloud);
			humidityTextView = (TextView)findViewById(R.id.textHum);
			timeTextView = (TextView)findViewById(R.id.textTime);
			precipTextView = (TextView)findViewById(R.id.precip);
			tempCTextView = (TextView)findViewById(R.id.textCel);
			tempFTextView = (TextView)findViewById(R.id.textFar);
			visibilityTextView = (TextView)findViewById(R.id.textVis);
			codeTextView = (TextView)findViewById(R.id.textCode);
			valueTextView = (TextView)findViewById(R.id.textValue);
			windPointTextView = (TextView)findViewById(R.id.textWPoint);
			windDegreeTextView = (TextView)findViewById(R.id.textWDegree);
			kmphTextView = (TextView)findViewById(R.id.textKmph);
			mphTextView = (TextView)findViewById(R.id.textMph);
		}
		
		@Override
		protected Object doInBackground(Object... arg0) {
			Log.i("url", BASE_URL+searchFor+FORMAT+NUM_DAYS+API_KEY);
			ph = new ParsingHandler(BASE_URL+searchFor+FORMAT+NUM_DAYS+API_KEY);
			ph.startParsing();
			return null;
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			Bundle bun = ph.getBundleCurrent();
			String cloudCover = bun.getString("CloudCover");
			String humidity = bun.getString("Humidity");
			String time = bun.getString("Time");
			String precip = bun.getString("Precip");
			String tempC = bun.getString("TempC");
			String tempF = bun.getString("TempF");
			String visibility = bun.getString("Visibility");
			String code = bun.getString("Code");
			String value = bun.getString("Value");
			String windPoint = bun.getString("WindPoint");
			String windDegree = bun.getString("WindDegree");
			String kmph = bun.getString("Kmph");
			String mph = bun.getString("Mph");	
			
			Log.i("Bundle", bun.toString());
			
			
			cloudTextView.setText(""+cloudCover);
			humidityTextView.setText(""+humidity);
			timeTextView.setText(""+time);
			precipTextView.setText(""+precip);
			tempCTextView.setText(""+tempC);
			tempFTextView.setText(""+tempF);
			visibilityTextView.setText(""+visibility);
			codeTextView.setText(""+code);
			valueTextView.setText(""+value);
			windPointTextView.setText(""+windPoint);
			windDegreeTextView.setText(""+windDegree);
			kmphTextView.setText(""+kmph);
			mphTextView.setText(""+mph);
			
		}		
	}
}

