package com.adg.weather;

import java.util.ArrayList;

import com.adg.object.SearchObj;
import com.adg.parser.SearchParsingHandler;
import com.adg.search.WeatherSearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchLocationActivity extends Activity {

	EditText city;
	EditText zip;
	Button search;
	Context context;
	SearchParsingHandler sph;
	ArrayList<SearchObj> so = new ArrayList<SearchObj>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_location);
		city = (EditText) findViewById(R.id.cityState);
		zip = (EditText) findViewById(R.id.zipcode);
		search = (Button) findViewById(R.id.searchButt);
		context = getApplicationContext();
		
		
		
		search.setOnClickListener(new OnClickListener(){
			public void onClick(View view){

				if(city.getText().equals(null)&&zip.getText().equals(null)){
					Toast.makeText(context, "Please enter a city or zipcode to search", Toast.LENGTH_SHORT).show();
				}else if(!zip.getText().equals(null)){
					Intent in = new Intent(SearchLocationActivity.this, WeatherSearch.class);
					Bundle bun = new Bundle();
					bun.putString("zip", zip.getText().toString());
					bun.putString("city", "");
					in.putExtras(bun);
					startActivity(in);
					
				}else{
					
					sph = new SearchParsingHandler(city.getText().toString());
					sph.startParsing();
					so = sph.getSo();
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
		Intent in = new Intent(SearchLocationActivity.this, WeatherSearch.class);
		Bundle bun = new Bundle();
		bun.putString("city", so.get(j).getAreaName()+", "+so.get(j).getRegionName()+" "+so.get(j).getContryName() );
		bun.putString("zip", "");
		in.putExtras(bun);
		startActivity(in);
	}


	public class lookingForCity extends AsyncTask{
		
		Context context;
		
		lookingForCity(Context c){
			this.context = c;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		@Override
		protected Object doInBackground(Object... arg0) {
			return null;
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
		}

		
		
	}

}
