package com.adg.weather;

import java.util.ArrayList;

import com.adg.object.Weather;
import com.adg.parser.ParsingHandler;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FiveDayForcastActvity extends Activity{
	
	DownloadWeather dw;
	ParsingHandler ph;
	String url = "";
	ArrayList<Weather> fiveDay = new ArrayList<Weather>();
	WeatherAdapter rapter;
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.five_day_list);
		
		dw = new DownloadWeather(getApplicationContext());
		dw.execute((Integer)null);
	}
	
	class DownloadWeather extends AsyncTask{

		Context context;
		
		public DownloadWeather(Context c){
			this.context = c;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		@Override
		protected Object doInBackground(Object... params) {
			ph = new ParsingHandler(url);
			fiveDay = ph.getFiveDay();
			return null;
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			lv = (ListView) findViewById(R.id.fiveDayList);
			rapter = new WeatherAdapter(getApplicationContext(), fiveDay);
			lv.setAdapter(rapter);
		}
		
		@Override
		protected void onCancelled() {
			super.onCancelled();
		}
	}
	public class WeatherAdapter extends BaseAdapter {
		
		Context context;
		ArrayList<Weather> fiveDay = new ArrayList<Weather>();

		public WeatherAdapter(Context c, ArrayList<Weather> f){
			this.context = c;
			this.fiveDay = f;
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
			child = getLayoutInflater().inflate(R.layout.five_day_item, null);
			val = (TextView) child.findViewById(R.id.valueText);
			d = (TextView) child.findViewById(R.id.dateTextView);
			temp = (TextView) child.findViewById(R.id.tempText);
			
			d.setText(fiveDay.get(arg0).getDate());
			temp.setText(fiveDay.get(arg0).getMaxC() +"\u00B0 C- " + fiveDay.get(arg0).getMinC()+ "\u00B0 C");
			val.setText(fiveDay.get(arg0).getValue());
			
			return child;
		}

	}
}
