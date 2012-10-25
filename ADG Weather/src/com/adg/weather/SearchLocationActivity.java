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
import android.view.KeyEvent;
import android.view.Menu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
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
    ArrayList<SearchObj> searchObjectArray = new ArrayList<SearchObj>();
    String key = "&key=845adebec4142346121409";
    String begining = "http://free.worldweatheronline.com/feed/weather.ashx?";
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
		
        search.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                getText(view);
            }
        });
		
        zip.setOnKeyListener(
                new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN)
                        && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    getText(v);
                    return true;
                }
                return false;
            }
        });
        city.setOnKeyListener(
                new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN)
                        && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    getText(v);
                    return true;
                }
                return false;
            }
        });
		
    }
	
    public void getText(View view) {
        if (city.getText().toString().equals("")
                && zip.getText().toString().equals("")) {
            Toast.makeText(context, "Please enter a city or zipcode to search", Toast.LENGTH_SHORT).show();
        } else if (!zip.getText().toString().equals("")) {
            Intent in = new Intent();
            Bundle bun = new Bundle();
            String url = begining + q + zip.getText().toString() + middle + key;

            Log.i("Find url", url);
            bun.putString("url", url);
            bun.putString("zip", zip.getText().toString());
            in.putExtras(bun);
            setResult(2, in);
            finish();	
        } else {
            String cityString = city.getText().toString();

            cityString = cityString.trim();
            cityString = cityString.replace(" ", "%20");
            lookingForCity LFC = new lookingForCity(context, view, cityString);

            LFC.execute((Integer) null);
        }		
    }

    public class lookingForCity extends AsyncTask {
		
        Context context;
        View view;
        String cityP;
		
        lookingForCity(Context c, View v, String ciz) {
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
            searchObjectArray = sph.getSo();
            Log.i("Search Objects Array Size", "" + searchObjectArray.size());
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            Log.i("city parsing", "" + searchObjectArray.size());
            searchAdapter = new SearchAdapter(getApplicationContext(),
                    searchObjectArray);

            lv.setAdapter(searchAdapter);
            mh.sendEmptyMessage(0);
            lv.setOnItemClickListener(
                    new OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0, View arg1,
                        int arg2, long arg3) {
                    Intent in = new Intent();
                    Bundle bun = new Bundle();
                    String url = begining + q
                            + searchObjectArray.get(arg2).getLatitude() + ","
                            + searchObjectArray.get(arg2).getLongitude()
                            + middle + key;

                    Log.i("Find url", url);
                    bun.putString("url", url);
                    bun.putString("location",
                            searchObjectArray.get(arg2).getAreaName() + ", "
                            + searchObjectArray.get(arg2).getRegionName());
                    bun.putString("lat",
                            "" + searchObjectArray.get(arg2).getLatitude());
                    bun.putString("lng",
                            "" + searchObjectArray.get(arg2).getLongitude());
                    in.putExtras(bun);
                    setResult(1, in);
                    finish();	
                }
            });
        }	
    }
}
