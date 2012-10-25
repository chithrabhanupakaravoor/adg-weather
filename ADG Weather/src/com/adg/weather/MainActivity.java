package com.adg.weather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.adg.handlers.MessageHandler;
import com.adg.location.MyLocation;
import com.adg.object.Weather;
import com.adg.parser.ParsingHandler;
import com.adg.search.WeatherSearch;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.net.ConnectivityManager;
import android.os.Message;
import android.provider.Settings;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements LocationListener {

	//shared prefs
	public static final String PREF_FILE = "ADGWeatherPrefs";
	private SharedPreferences sharedPreferences;
	
	public static final String API_KEY = "845adebec4142346121409";
	public static final String URL_1 = "http://free.worldweatheronline.com/feed/weather.ashx?q=";
	public static final String URL_2 = "&format=json&num_of_days=5&key=";
	
	public static final String FACEBOOK_APP_ID = "399359630137899";
	
	TextView descText;
	TextView cityText;
	TextView tempText;
	TextView precipText;
	
	TextView maxText;
	TextView minText;
	TextView cloudText;
	TextView humidityText;
	TextView pressureText;
	TextView visibText;
	TextView windText;
	
	Button searchButton;
	Button gpsButton;
	Button savedLocButton;
	Button saveToFaveButton;
	Button facebookButton;
	
	EditText searchQueryCity;
	EditText searchQueryCountry;
	
	boolean isF = true;
	ToggleButton toggleFC;

	
	List<MyLocation> favoriteLocations = new LinkedList<MyLocation>();
	
	private LocationManager locationManager;
	private String provider;
	private String gpsProvider;
	private String netProvider;
	private Context myContext;
	
	public String addressText = "";
	public String city = "";
	public String country = "";
	public String state = "";
	public String addressLine = "";
	public String favoriteUrl;
	public String theZIP = "";
	String searchLat = "";
	String searchLng = "";
	
	ImageView iv;
	ArrayList<Weather> fiveDay = new ArrayList<Weather>();
	List<Address> addressSearchList;
	Weather curr = new Weather();
	long lat;
	long lng;
	ParsingHandler parsingHandler;
	MessageHandler messageHandler;
	Bundle urlBundle = new Bundle();
	Location location;
	String urlBata = "";
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		Log.i("onActivity","calling onActivityResult");
		
		if(resultCode == 1) {
			Bundle bun = data.getExtras();
			urlBata = bun.getString("url");
			Log.i("URL from Search", ""+urlBata);
			addressLine = bun.getString("location");
			favoriteUrl = bun.getString("url");
			searchLat = bun.getString("lat");
			searchLng = bun.getString("lng");
			theZIP = "";
			
			SearchParsingTask searchTask = new SearchParsingTask(getApplicationContext(), urlBata);
			searchTask.execute((Integer)null);
		}
		if(resultCode == 2) {
			Bundle bun = data.getExtras();
			urlBata = bun.getString("url");
			Log.i("URL from Search", ""+urlBata);
			//addressLine = bun.getString("location");
			favoriteUrl = bun.getString("url");
			theZIP = bun.getString("zip");
			
			SearchParsingTask searchTask = new SearchParsingTask(getApplicationContext(), urlBata);
			searchTask.execute((Integer)null);
		}
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        
        
        
        favoriteLocations.clear();
        
        myContext = getApplicationContext();
        messageHandler = new MessageHandler(this);
        
        iv = (ImageView) findViewById(R.id.weatherIcon);
        descText = (TextView) findViewById(R.id.descrip);
        cityText = (TextView) findViewById(R.id.location);
        tempText = (TextView) findViewById(R.id.temp);
        precipText = (TextView) findViewById(R.id.precip);
        maxText = (TextView) findViewById(R.id.maxTextView);
        minText = (TextView) findViewById(R.id.minTextView);
        cloudText = (TextView) findViewById(R.id.cloudCover);
    	humidityText = (TextView) findViewById(R.id.humidity);
    	pressureText = (TextView) findViewById(R.id.pressure);
    	visibText = (TextView) findViewById(R.id.visibility);
    	windText = (TextView) findViewById(R.id.wind);
    	toggleFC = (ToggleButton) findViewById(R.id.toggleButton1);
    	facebookButton = (Button) findViewById(R.id.facebookButton);
    	
    	
    	
    	
        gpsButton = (Button) findViewById(R.id.gpsButton);
        savedLocButton = (Button) findViewById(R.id.saveLocButton);
        registerForContextMenu(savedLocButton);
        searchButton = (Button) findViewById(R.id.searchButton);
        saveToFaveButton = (Button) findViewById(R.id.saveToFaveButton);
        
        
        
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("Do you want to enable GPS");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 2);
			}
		})
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
        
        
    
        
		
        //hide everything while loading
        hideViews();
        
        
        Button fiveDayButton = (Button) findViewById(R.id.button5Day);
        
        fiveDayButton.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0){
				Intent in = new Intent(MainActivity.this, FiveDayForcastActvity.class);
				in.putExtras(urlBundle);
				startActivity(in);
			}
		});
           
        
        facebookButton.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				Facebook facebook = new Facebook(FACEBOOK_APP_ID);
			    AsyncFacebookRunner mAsyncRunner = new AsyncFacebookRunner(facebook); 
			    
			    
			    facebook.authorize(MainActivity.this, new DialogListener() {
		            public void onComplete(Bundle values) {}

		            public void onFacebookError(FacebookError error) {}

		            public void onError(DialogError e) {}

		            public void onCancel() {}
		
		        });
			}
		});
        
        
        searchButton.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				Intent in = new Intent(MainActivity.this, SearchLocationActivity.class);
				startActivityForResult(in, 1);
			}
		});
         
        saveToFaveButton.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				if(saveToFaveButton.getText().toString().equals("Save to Favorites")) {
					String key = addressLine;
					//String vals = ""+lng+"~"+lat+"~"+country;
					String vals = favoriteUrl;
					Log.i("Saving Location", key+" URL: "+vals);
					SavePreferences(key,vals);
					saveToFaveButton.setText("Remove from Favorites");
				} 
				else if(saveToFaveButton.getText().toString().equals("Remove from Favorites")){
					doDeletion();
					saveToFaveButton.setText("Save to Favorites");
				}		
			}
		});
        

        
        
        
        savedLocButton.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				LoadPreferences();
				openContextMenu(view);
			}
		});

        
        
		

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteriaGPS = new Criteria();
        provider = locationManager.getBestProvider(criteriaGPS, false);
        
        if(provider == null) { 
		// create alert dialog
        	AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
        }

        location = locationManager.getLastKnownLocation(provider);

		if (location != null) {
			System.out.println("Provider: " + provider + " has been selected.");
			onLocationChanged(location);
		} else {
			descText.setText("Location not available");
		}
		
		
		Button GPS2 = (Button) findViewById(R.id.gpsButton);
		
		GPS2.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				
				Log.i("GPS Button", "clicked");
				
				locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		        Criteria criteriaGPS = new Criteria();
		        provider = locationManager.getBestProvider(criteriaGPS, false);
		        
//		        if(provider == null) { 
//				// create alert dialog
//		        	AlertDialog alertDialog = alertDialogBuilder.create();
//					alertDialog.show();
//		        }

		        location = locationManager.getLastKnownLocation(provider);
				
				
				ReverseGeocodingTask rgt = new ReverseGeocodingTask(
						getApplicationContext());
				rgt.execute(location, null, null);
			}
		});
		ReverseGeocodingTask rgt = new ReverseGeocodingTask(myContext);
		rgt.execute(location, null, null);
		
		
		
		
		
    }
	
	private void SavePreferences(String key, String coor) {
		sharedPreferences = getApplicationContext().getSharedPreferences(
				PREF_FILE, 0);
		Map allLocations = sharedPreferences.getAll();
		if (allLocations.containsKey(key)) {
			Toast.makeText(myContext, "Location already exists", Toast.LENGTH_SHORT).show();
		} else {
			SharedPreferences.Editor editor = sharedPreferences.edit();
			// editor.putString(key, value);
			editor.putString(key, coor);
			editor.commit();
			Toast.makeText(myContext, key+" added to favorites", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void onToggleClicked(View view){
		boolean on = ((ToggleButton) view).isChecked();
		
		if(on){
			isF = true;
		}else{
			isF = false;
		}
		setView(fiveDay, curr);
		urlBundle.putBoolean("isF", isF);
	}

	private void deleteSavedLocation(String address) {
		sharedPreferences = getApplicationContext().getSharedPreferences(PREF_FILE, 0);
		Map allLocations = sharedPreferences.getAll();
		if(allLocations.containsKey(address)) {
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.remove(address);
			editor.commit();
			Toast.makeText(myContext, address+" removed from favorites", Toast.LENGTH_SHORT).show();
		}
	}
	
	private void LoadPreferences() {
		sharedPreferences = getApplicationContext().getSharedPreferences(PREF_FILE, 0);
		Map allLocations = sharedPreferences.getAll();
		favoriteLocations.clear();
		
		List<String> keys = new ArrayList<String>(allLocations.keySet());
		List<String> values = new ArrayList<String>(allLocations.values());
		
		
		for(int i = 0; i < keys.size(); i++ ) {
			MyLocation myLocation = new MyLocation();
			
			String adr = keys.get(i);
			myLocation.setAddress(adr);
			
			String theUrl = values.get(i);
			myLocation.setUrlToParse(theUrl);
			
			favoriteLocations.add(myLocation);
		}
	}

    /* Request updates at startup */
    @Override
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(provider, 400, 1, this);
		LoadPreferences();
		for (int i = 0; i < favoriteLocations.size(); i++) {
			if (favoriteLocations.get(i).getAddress()
					.equals(cityText.getText().toString())) {
				saveToFaveButton.setText("Remove from Favorites");
			} else {
				saveToFaveButton.setText("Save to Favorites");
			}
		}
		favoriteLocations.clear();
	}

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
      super.onPause();
      locationManager.removeUpdates(this);
    }

    public void onLocationChanged(Location location) {
      lat = (long) (location.getLatitude());
      lng = (long) (location.getLongitude());
      //tv.setText("Latitude: "+lat+ "\nLongitude: "+lng);
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    public void onProviderEnabled(String provider) {
      Toast.makeText(this, "Enabled new provider " + provider,
          Toast.LENGTH_SHORT).show();

    }

    public void onProviderDisabled(String provider) {
      Toast.makeText(this, "Disabled provider " + provider,
          Toast.LENGTH_SHORT).show();
      finish();
    }
   
    


    @Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		
		menu.setHeaderTitle("Saved Locations"); 
		for(int j = 0; j < favoriteLocations.size(); j++) {
			menu.add(0, v.getId(), 0, favoriteLocations.get(j).getAddress());
			
		}
		
		//menu.add(0, v.getId(), 0, "Option 2");
	}
    
    @Override
	public boolean onContextItemSelected(MenuItem item) {
    	for(int i = 0; i < favoriteLocations.size(); i++) {
    		if(item.getTitle().equals(favoriteLocations.get(i).getAddress())) {
    			String ads = favoriteLocations.get(i).getAddress();
    			String url = favoriteLocations.get(i).getUrlToParse();
    	
    			FavParsTask favoriteParsingTask = new FavParsTask(myContext, ads, url);
    			favoriteParsingTask.execute((Integer)null);
    		}
    	}
    	
    	
		return true;
	}
    
    
    public void hideViews() {
    	descText.setVisibility(View.GONE);
    	cityText.setVisibility(View.GONE);
    	tempText.setVisibility(View.GONE);
    	precipText.setVisibility(View.GONE);
    	
    	maxText.setVisibility(View.GONE);
    	minText.setVisibility(View.GONE);
    	iv.setVisibility(View.GONE);
    	cloudText.setVisibility(View.GONE);
    	humidityText.setVisibility(View.GONE);
    	pressureText.setVisibility(View.GONE);
    	visibText.setVisibility(View.GONE);
    	windText.setVisibility(View.GONE);
    	
    }
    
    public void showViews() {
    	descText.setVisibility(View.VISIBLE);
    	cityText.setVisibility(View.VISIBLE);
    	tempText.setVisibility(View.VISIBLE);
    	precipText.setVisibility(View.VISIBLE);
    	
    	maxText.setVisibility(View.VISIBLE);
    	minText.setVisibility(View.VISIBLE);
    	iv.setVisibility(View.VISIBLE);
    	cloudText.setVisibility(View.VISIBLE);
    	humidityText.setVisibility(View.VISIBLE);
    	pressureText.setVisibility(View.VISIBLE);
    	visibText.setVisibility(View.VISIBLE);
    	windText.setVisibility(View.VISIBLE);
    	
    }
    
    public class SearchParsingTask extends AsyncTask {

    	Context context;
    	String myURL;
    	
    	public SearchParsingTask (Context contxt, String theURL) {
    		this.context = contxt;
    		this.myURL = theURL;
    	}
    	
    	@Override
		protected void onPreExecute() {
			super.onPreExecute();
			messageHandler.sendEmptyMessage(9);
		}
    	
    	
		@Override
		protected Object doInBackground(Object... params) {
			
			
			if (!theZIP.equals("")) {
				Geocoder geocoder = new Geocoder(context);
				List<Address> addresses = null;
				try {
					addresses = geocoder.getFromLocationName(theZIP, 1);

				} catch (IOException e) {
					e.printStackTrace();
				}
				if (addresses.size() > 0) {
					Log.i("Address by zip", ""
							+ addresses.get(0).getAddressLine(0));
					addressLine = addresses.get(0).getAddressLine(0);
				}
			}
			else {
				Geocoder geocoder = new Geocoder(context);
				List<Address> addresses = null;
				try {
					
					Double slat = Double.parseDouble(searchLat);
					Double slng = Double.parseDouble(searchLng);
					
					addresses = geocoder.getFromLocation(slat,
							slng, 1);

				} catch (IOException e) {
					e.printStackTrace();
				}
				if (addresses.size() > 0) {
					Log.i("Address by zip", ""
							+ addresses.get(0).getAddressLine(0));
					String currentAdd = addresses.get(0).getPostalCode();
					
					try {
						addresses = geocoder.getFromLocationName(currentAdd, 1);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					addressLine = addresses.get(0).getAddressLine(0);
				}
			}
			
			
			parsingHandler = new ParsingHandler(myURL);
			urlBundle.putString("URL", myURL);
			urlBundle.putString("Address", addressLine);
			parsingHandler.startParsing();
			fiveDay = parsingHandler.getFiveDay();
			curr = parsingHandler.getCurr();
			
			
			return null;
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			setView(fiveDay, curr);
			messageHandler.sendEmptyMessage(0);
			
		}

		
    	
    }
    
    
    public class FavParsTask extends AsyncTask{
    	
    	Context contaxt;
    	
    	String address;
    	String parseURL;
    	
    	public FavParsTask(Context c){
    		super();
    		this.contaxt = c;
    	}
    	
    	
    	
    	public FavParsTask(Context c, String adrs, String url){
    		super();
    		this.contaxt = c;
    		this.address = adrs;
    		this.parseURL = url;
    	}
    	
    	@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			addressLine = address;
			messageHandler.sendEmptyMessage(9);
		}

		@Override
		protected Object doInBackground(Object... arg0) {
//			String url;
//			if (!urlBata.equals("")) {
//				url = urlBata;
//				Log.i("FAV URL", url);
//				urlBundle.putString("URL", url);
//			} else {
//				url = URL_1 + latitude + ".00," + longitude + ".00" + URL_2
//						+ API_KEY;
//				Log.i("FAV URL", url);
//				urlBundle.putString("URL", url);
//			}
			Log.i("Fave Parse URL",""+parseURL);
			parsingHandler = new ParsingHandler(parseURL);
			urlBundle.putString("URL", parseURL);
			urlBundle.putString("Address", addressLine);
			parsingHandler.startParsing();
			fiveDay = parsingHandler.getFiveDay();
			curr = parsingHandler.getCurr();
		
			//Log.i("5", ""+fiveDay.size());
			//Log.i("one day", "WeatherCode: "+curr.getWeatherCode());
			
			return null;
		}

		@Override
		protected void onPostExecute(Object result) {
			super.onPostExecute(result);
			
			setView(fiveDay, curr);
			messageHandler.sendEmptyMessage(0);
		}
    	
    }
    
	// AsyncTask encapsulating the reverse-geocoding API. Since the geocoder API
	// is blocked,
	// we do not want to invoke it from the UI thread.
	public class ReverseGeocodingTask extends AsyncTask<Location, Void, Void> {
		
		
		String url;
		Context mContext;

		public ReverseGeocodingTask(Context context) {
			super();
			mContext = context;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			messageHandler.sendEmptyMessage(9);
		}
		
		@Override
		protected Void doInBackground(Location... params) {

			Geocoder geocoder = new Geocoder(mContext);

			//Log.i("GeoCoder", "Is present: "+geocoder.isPresent());
			
			Location loc = params[0];
			List<Address> addresses = null;
			try {
				// Call the synchronous getFromLocation() method by passing in
				
				// the lat/long values.
				
				//addressSearchList = geocoder.getFromLocationName("Palatine, IL, USA", 10);
				
				addresses = geocoder.getFromLocation(loc.getLatitude(),
						loc.getLongitude(), 1);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException npe) {
				npe.printStackTrace();
			}
			
			if (addresses != null && addresses.size() > 0) {
				Address address = addresses.get(0);
				// Format the first line of address (if available), city, and
				// country name.
				addressText = String.format(
						"%s, %s, %s",
						address.getMaxAddressLineIndex() > 0 ? address
								.getAddressLine(0) : "", address.getLocality(),
						address.getCountryName());

				city = address.getLocality();
				country = address.getCountryName();
				addressLine = address.getAddressLine(1);
				Log.i("Address Line", ""+addressLine);
				
			}
			String url = URL_1 + lat + ".00," + lng + ".00" + URL_2 + API_KEY;

			urlBundle.putString("URL", url);
			urlBundle.putString("Address", addressLine);
			favoriteUrl = url;
			Log.i("URL", url);

			parsingHandler = new ParsingHandler(url);
			parsingHandler.startParsing();
			fiveDay = parsingHandler.getFiveDay();
			curr = parsingHandler.getCurr();
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			setView(fiveDay, curr);
			
			
//			LoadPreferences();
//	        for(int j = 0; j < favoriteLocations.size(); j++) {
//	        	Log.i("", ""+favoriteLocations.get(j).getAddress());
//	        	String comp = favoriteLocations.get(j).getAddress();
//	        	if(addressLine.equals(comp)) {
//	        		Log.i("", ""+comp+" exists in favorites and is to be deleted");
//	        		deleteSavedLocation(comp);
//	        		Log.i("Deletion", comp+" deleted");
//	        	}
//	        }
			
		
		}
	}
	
	
	public void doDeletion() {
		favoriteLocations.clear();
		LoadPreferences();
        for(int j = 0; j < favoriteLocations.size(); j++) {
        	Log.i("", ""+favoriteLocations.get(j).getAddress());
        	String comp = favoriteLocations.get(j).getAddress();
        	if(addressLine.equals(comp)) {
        		Log.i("", ""+comp+" exists in favorites and is to be deleted");
        		deleteSavedLocation(comp);
        		Log.i("Deletion", comp+" deleted");
        		saveToFaveButton.setText("Save to Favorites");
        	}
        }
        favoriteLocations.clear();
	}
	
	
	public void setView(ArrayList<Weather> fd, Weather c){
		if (fd.size() > 0) {
			String maxF = fd.get(0).getMaxF();
			String minF = fd.get(0).getMinF();
			String maxC = fd.get(0).getMaxC();
			String minC = fd.get(0).getMinC();
			String cloudCover = curr.getCloudcover();
			String humidity = curr.getHumidity();
			String pressure = curr.getPressure();
			String visibility = curr.getVisibility();
			String windDir = curr.getWindPoint();
			String windMph = curr.getMph();
			String windKmph = curr.getKmph();
			String currF = curr.getF();
			String currC = curr.getC();
			String maxTemp;
			String minTemp;
			String currTemp;
			if (isF) {
				maxTemp = maxF + "\u00B0 F";
				minTemp = minF + "\u00B0 F";
				currTemp = currF + "\u00B0 F";
			} else {
				maxTemp = maxC + "\u00B0 C";
				minTemp = minC + "\u00B0 C";
				currTemp = currC + "\u00B0 C";
			}
			// =====================================
			favoriteLocations.clear();
			LoadPreferences();
			for (int j = 0; j < favoriteLocations.size(); j++) {
				Log.i("", "" + favoriteLocations.get(j).getAddress());
				String comp = favoriteLocations.get(j).getAddress();
				if (addressLine.equals(comp)) {
					// Log.i("",
					// ""+comp+" exists in favorites and is to be deleted");
					// deleteSavedLocation(comp);
					// Log.i("Deletion", comp+" deleted");
					saveToFaveButton.setText("Remove from Favorites");
					break;
				} else {
					saveToFaveButton.setText("Save to Favorites");
				}
			}
			favoriteLocations.clear();
			// =====================================

			// Log.i("COORDINATES","Long: "+lng +"Lat: "+lat);
			descText.setText(c.getValue());
			tempText.setText(currTemp);
			precipText.setText("Precipitation: " + c.getPercip());
			
			// maxText.setText("Max: "+curr.getMaxF() + "\u00B0 F");
			// minText.setText("Min: "+curr.getMinF() + "\u00B0 F");
			maxText.setText("Max: " + maxTemp + "  /");
			minText.setText("  Min: " + minTemp);

			String weatherCode = c.getWeatherCode();
			// Log.i("WEATHER CODE", ""+weatherCode);
			WeatherCode wc = new WeatherCode(Integer.parseInt(weatherCode));
			cityText.setText(addressLine);
			iv.setImageResource(wc.getDrawableIcon());
			cloudText.setText("Cloud Cover: " + cloudCover);
			humidityText.setText("Humidity: " + humidity);
			pressureText.setText("Pressure: " + pressure);
			visibText.setText("Visibility: " + visibility);
			if (windMph.equals("0")) {
				windText.setText("there is little to no wind today");
			} else {
				windText.setText("The wind is blowing " + windDir + " at "
						+ windMph + " mph");
			}
			showViews();
			messageHandler.sendEmptyMessage(0);
		}
/*		
<<<<<<< .mine

=======
		//=====================================
		favoriteLocations.clear();
		LoadPreferences();
        for(int j = 0; j < favoriteLocations.size(); j++) {
        	Log.i("", ""+favoriteLocations.get(j).getAddress());
        	String comp = favoriteLocations.get(j).getAddress();
        	if(addressLine.equals(comp)) {
        		//Log.i("", ""+comp+" exists in favorites and is to be deleted");
        		//deleteSavedLocation(comp);
        		//Log.i("Deletion", comp+" deleted");
        		saveToFaveButton.setText("Remove from Favorites");
        		break;
        	}
        }
        favoriteLocations.clear();
		//=====================================
		
		//Log.i("COORDINATES","Long: "+lng +"Lat: "+lat);
		descText.setText(c.getValue());
		tempText.setText(currTemp);
		precipText.setText("Precipitation: "+c.getPercip()+ "mm");
		windSpeedText.setText("Wind Speed:"+c.getMph() + " mph");
		//maxText.setText("Max: "+curr.getMaxF() + "\u00B0 F");
		//minText.setText("Min: "+curr.getMinF() + "\u00B0 F");
		maxText.setText("Max: "+maxTemp + "  /");
		minText.setText("  Min: "+minTemp);
		
		String weatherCode = c.getWeatherCode();
		//Log.i("WEATHER CODE", ""+weatherCode);
		WeatherCode wc = new WeatherCode(Integer.parseInt(weatherCode));
		cityText.setText(addressLine);
		iv.setImageResource(wc.getDrawableIcon());
		cloudText.setText("Cloud Cover: "+cloudCover+"%");
    	humidityText.setText("Humidity: "+humidity+"%");
    	pressureText.setText("Pressure: "+pressure + " mb");
    	visibText.setText("Visibility; "+visibility+" km");
    	if(windMph.equals("0")){
    		windText.setText("there is little to no wind today");
    	}else{
    		windText.setText("The wind is blowing "+windDir+" at "+ windMph +" mph");
    	}
		showViews();
		messageHandler.sendEmptyMessage(0);
>>>>>>> .r64
*/

	}
}
