package com.adg.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.adg.object.Weather;
import com.adg.weather.R;

public class WeatherAdapter extends BaseAdapter {
	
	Context context;
	ArrayList<Weather> fiveDay = new ArrayList<Weather>();

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

	public View getView(int arg0, View arg1, ViewGroup arg2) {
		LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		child = li.inflate(R.layout.five_day_item, null);
		val = (TextView) child.findViewById(R.id.valueText);
		d = (TextView) child.findViewById(R.id.dateTextView);
		temp = (TextView) child.findViewById(R.id.tempText);
		
		d.setText(fiveDay.get(arg0).getDate());
		temp.setText(fiveDay.get(arg0).getMaxC() +"\u00B0 C- " + fiveDay.get(arg0).getMinC()+ "\u00B0 C");
		val.setText(fiveDay.get(arg0).getValue());
		
		return child;
	}

}
