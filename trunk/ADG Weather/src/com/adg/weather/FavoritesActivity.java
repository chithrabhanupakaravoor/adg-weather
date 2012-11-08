package com.adg.weather;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FavoritesActivity extends Activity {

	ArrayList<String> favorites = new ArrayList<String>();
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_locations);
        
        favorites = getIntent().getExtras().getStringArrayList("favs");
        
        ListView favListView = (ListView) findViewById(R.id.favoriteListView);
        FavoritesAdapter adapter = new FavoritesAdapter(getApplicationContext());
        favListView.setAdapter(adapter);
        
        favListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				String address = favorites.get(position);
				
				Intent in = new Intent();
				in.putExtra("address", address);
				
				setResult(3, in);
				finish();
			}
        	
        });
        
	}

	public class FavoritesAdapter extends BaseAdapter {
		
		Context c;
		
		public FavoritesAdapter(Context cont) {
			this.c = cont;
		}
	
		public int getCount() {
			return favorites.size();
		}
	
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}
	
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
	
		
		View child;
	    TextView location;
		
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater li = (LayoutInflater) c
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	
			child = li.inflate(R.layout.favorite_item, null);
			location = (TextView) child.findViewById(R.id.favoriteItemTextView);
			
			String text = favorites.get(position);
			
			location.setText(text);
			
			return child;
		}
		
	}
	
	
	
	
}
