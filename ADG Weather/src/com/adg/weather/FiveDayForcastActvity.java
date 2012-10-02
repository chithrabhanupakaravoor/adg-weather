package com.adg.weather;

import java.util.ArrayList;

import com.adg.adapter.WeatherAdapter;
import com.adg.object.Weather;
import com.adg.parser.ParsingHandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

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
		Bundle urlBundle = getIntent().getExtras();
		url = urlBundle.getString("URL");
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
			Log.i("FiveDayForcast", "url: "+url);
			ph = new ParsingHandler(url);
			ph.startParsing();
			fiveDay = ph.getFiveDay();
			return null;
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			lv = (ListView) findViewById(R.id.fiveDayList);
			rapter = new WeatherAdapter(getApplicationContext(), fiveDay);
			lv.setAdapter(rapter);
			Log.i("FiveDayForcast", "rapter set");
			 lv.setOnItemClickListener(new OnItemClickListener(){
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						Bundle bundle = ph.getBundleFive(arg2);
						Intent in = new Intent(getApplicationContext(), OneDayActivity.class);
						in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						in.putExtras(bundle);
						startActivity(in);
					}
			});
		}
		
		@Override
		protected void onCancelled() {
			super.onCancelled();
		}
	}
	
}
