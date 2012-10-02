package com.adg.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adg.object.Weather;
import com.adg.weather.DateFinder;
import com.adg.weather.R;
import com.adg.weather.WeatherCode;

public class WeatherAdapter extends BaseAdapter {
	
	Context context;
	ArrayList<Weather> fiveDay = new ArrayList<Weather>();
	DateFinder df = new DateFinder();

	public WeatherAdapter(Context c, ArrayList<Weather> f){
		this.context = c;
		this.fiveDay = f;
		Log.i("WeatherAdapter", "Weather adater is being called");
	}
	public int getCount() {
		return fiveDay.size();
	}

	public Object getItem(int arg0) {
		return null;
	}

	public long getItemId(int arg0) {
		return 0;
	}
	
	View child;
	TextView val;
	TextView d;
	TextView temp;
	ImageView iv;
	String weatherCode;
	WeatherCode wc;

	public View getView(int arg0, View arg1, ViewGroup arg2) {
		LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		child = li.inflate(R.layout.five_day_item, null);
		val = (TextView) child.findViewById(R.id.valueText);
		d = (TextView) child.findViewById(R.id.dateTextView);
		temp = (TextView) child.findViewById(R.id.tempText);
		iv = (ImageView) child.findViewById(R.id.fiveDayImage);
		weatherCode = fiveDay.get(arg0).getWeatherCode();
		wc = new WeatherCode(Integer.parseInt(weatherCode));
		
		d.setText(df.dayOfWeek(fiveDay.get(arg0).getDate()));
		temp.setText(fiveDay.get(arg0).getMaxF() +"\u00B0 F- " + fiveDay.get(arg0).getMinF()+ "\u00B0 F");
		val.setText(fiveDay.get(arg0).getValue());
		iv.setImageResource(wc.getDrawableIcon());
		
		Log.i("WeatherAdapter", fiveDay.get(arg0).getDate());
		Log.i("WeatherAdapter", fiveDay.get(arg0).getValue());
		
		return child;
	}

}
