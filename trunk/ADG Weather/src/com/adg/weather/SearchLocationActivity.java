package com.adg.weather;

import java.util.ArrayList;

import com.adg.adapter.SearchAdapter;
import com.adg.handlers.MessageHandler;
import com.adg.object.SearchObj;
import com.adg.parser.SearchParsingHandler;
import com.adg.search.WeatherSearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SearchLocationActivity extends Activity {

	EditText city;
	EditText zip;
	Button search;
	Context context;
	SearchParsingHandler sph;
	ArrayList<SearchObj> so = new ArrayList<SearchObj>();
	String key = "&key=845adebec4142346121409";
	String begining ="http://free.worldweatheronline.com/feed/weather.ashx?";
	String q = "q=";
	String middle = "&format=json&num_of_days=5";
	MessageHandler mh;
	ListView lv;
	SearchAdapter searchAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_location);
		city = (EditText) findViewById(R.id.cityState);
		zip = (EditText) findViewById(R.id.zipcode);
		search = (Button) findViewById(R.id.searchButt);
		context = getApplicationContext();
		mh = new MessageHandler(this);
		
		
		
		search.setOnClickListener(new OnClickListener(){
			public void onClick(View view){

				if(city.getText().toString().equals("")&&zip.getText().toString().equals("")){
					Toast.makeText(context, "Please enter a city or zipcode to search", Toast.LENGTH_SHORT).show();
				}else if(!zip.getText().toString().equals("")){
					Intent in = new Intent();
					Bundle bun = new Bundle();
					String url = begining + q + zip.getText().toString() + middle + key;
					Log.i("Find url", url);
					bun.putString("url", url);
					in.putExtras(bun);
					//startActivity(in);
					setResult(1, in);
					finish();	
				}else{
					lookingForCity LFC = new lookingForCity(context, view, city.getText().toString());
					LFC.execute((Integer)null);
				}		
			}
		});
	}
	public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Locations"); 
		for(int j = 0; j < so.size(); j++) {
			menu.add(0, v.getId(), 0, so.get(j).getAreaName()+", "+so.get(j).getRegionName()+" "+so.get(j).getContryName());
		}
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			sendToActivity(0);
			return true;
		case 1:
			sendToActivity(1);
			return true;
		case 2:
			sendToActivity(2);
			return true;
		}
		return super.onContextItemSelected(item);
	}
	
	public void sendToActivity(int j){
		Intent in = new Intent();
		Bundle bun = new Bundle();
		String cityS = so.get(j).getAreaName()+"&"+so.get(j).getRegionName()+","+so.get(j).getContryName();
//		bun.putString("city", cityS );
//		bun.putString("zip", zip.getText().toString());
		String url = begining + q + cityS + middle + key;
		bun.putString("url", url);
		in.putExtras(bun);
//		startActivity(in);
		setResult(1, in);
		finish();
	}


	public class lookingForCity extends AsyncTask{
		
		Context context;
		View view;
		String cityP;
		
		lookingForCity(Context c, View v, String ciz){
			this.context = c;
			this.view = v;
			this.cityP = ciz;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mh.sendEmptyMessage(6);
			lv = (ListView) findViewById(R.id.listView1);
		}
		@Override
		protected Object doInBackground(Object... arg0) {
			sph = new SearchParsingHandler(cityP);
			Log.i("City parsing", cityP);
			sph.startParsing();
			so = sph.getSo();
			return null;
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			Log.i("city parsing", ""+so.size());
			searchAdapter = new SearchAdapter(getApplicationContext(), so);
			lv.setAdapter(searchAdapter);
			mh.sendEmptyMessage(0);
			lv.setOnItemClickListener(new OnItemClickListener(){
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					Intent in = new Intent();
					Bundle bun = new Bundle();
					String url = begining + q + so.get(arg2).getLatitude()+","+so.get(arg2).getLongitude() + middle + key;
					Log.i("Find url", url);
					bun.putString("url", url);
					in.putExtras(bun);
					//startActivity(in);
					setResult(1, in);
					finish();	
				}
			});
			//openContextMenu(view);
		}
		
	}

}