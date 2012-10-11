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
	Boolean isF;

	public WeatherAdapter(Context c, ArrayList<Weather> f, Boolean isF){
		this.context = c;
		this.fiveDay = f;
		this.isF = isF;
	}
	public int getCount() {
		return fiveDay.size();
	}

	public Object getItem(int arg0) {
		return arg0;
	}

	public long getItemId(int arg0) {
		return 0;
	}
	
	public String getMonth(String month) {
		String month2;
		
		switch (Integer.parseInt(month)) {
		case 1:
			month2 = "January";
			break;
		case 2:
			month2 = "February";
			break;
		case 3:
			month2 = "March";
			break;
		case 4:
			month2 = "April";
			break;
		case 5:
			month2 = "May";
			break;
		case 6:
			month2 = "June";
			break;
		case 7:
			month2 = "July";
			break;
		case 8:
			month2 = "August";
			break;
		case 9:
			month2 = "September";
			break;
		case 10:
			month2 = "October";
			break;
		case 11:
			month2 = "November";
			break;
		case 12:
			month2 = "December";
			break;
		default:
			month2 = month;
			break;
		}
		return month2;
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
		
		String date = fiveDay.get(arg0).getDate();
		String[] mdy = date.split("-");
		String year = mdy[0];
		String month = mdy[1];
		String day = mdy[2];
		String m1 = getMonth(month);
		

		DateFinder df = new DateFinder();
		String dow = df.dayOfWeek(date);
		
		String currTemp;
		
		d.setText(dow+", "+day+" "+m1+", "+year);
		
		if(isF){
			currTemp = fiveDay.get(arg0).getMaxF() +"\u00B0 F  /  " + fiveDay.get(arg0).getMinF()+ "\u00B0 F";
		}else{
			currTemp = fiveDay.get(arg0).getMaxC() +"\u00B0 C  /  " + fiveDay.get(arg0).getMinC()+ "\u00B0 C";
		}
		
		temp.setText(currTemp);
		val.setText(fiveDay.get(arg0).getValue());
		
		
		ImageView iv = (ImageView) child.findViewById(R.id.fiveDayImage);
		int weatherCode = Integer.parseInt(fiveDay.get(arg0).getWeatherCode());
		WeatherCode wc = new WeatherCode(weatherCode);
		iv.setImageResource(wc.getDrawableIcon());
		
		Log.i("WeatherAdapter", fiveDay.get(arg0).getDate());
		Log.i("WeatherAdapter", fiveDay.get(arg0).getValue());
		
		return child;
	}

}
