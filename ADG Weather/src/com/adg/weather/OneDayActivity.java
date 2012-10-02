package com.adg.weather;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class OneDayActivity extends Activity {
	
	DateFinder df = new DateFinder();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.one_day_layout);
		
		TextView date = (TextView) findViewById(R.id.dayDateView);
		TextView max = (TextView) findViewById(R.id.maxDayView);
		TextView min = (TextView) findViewById(R.id.minDayView);
		TextView perc = (TextView) findViewById(R.id.percipDayView);
		TextView wind = (TextView) findViewById(R.id.windDayView);
		TextView v = (TextView) findViewById(R.id.valueDayText);
		ImageView value = (ImageView) findViewById(R.id.dayImageView);
		
		
		Bundle bundle = getIntent().getExtras();
		String weatherCode = bundle.getString("Code");
		WeatherCode wc = new WeatherCode(Integer.parseInt(weatherCode));
		
		v.setText(bundle.getString("Value"));
		date.setText(df.dayOfWeek(bundle.getString("Date")));
		max.setText(bundle.getString("MaxF")+"\u00B0 F");
		min.setText(bundle.getString("MinF")+"\u00B0 F");
		perc.setText(bundle.getString("Precip")+ "mm");
		double w = Double.parseDouble(bundle.getString("Mph"));
		if(w <= 0.0){
			wind.setText("There will be no wind.");
		}else{
			wind.setText("The wind will blow " + bundle.getString("WindPoint") 
		    + " at about" + bundle.getString("Mph")+"mph");
		}
		value.setImageResource(wc.getDrawableIcon());
		
		
	}

}
