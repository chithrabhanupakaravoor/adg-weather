package com.adg.weather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.adg.handlers.MessageHandler;
import com.adg.object.Weather;
import com.adg.parser.ParsingHandler;
import com.adg.search.WeatherSearch;

import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.provider.Settings;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener {

	//shared prefs
	public static final String PREF_FILE = "ADGWeatherPrefs";
	private SharedPreferences sharedPreferences;
	
	public static final String API_KEY = "845adebec4142346121409";
	public static final String URL_1 = "http://free.worldweatheronline.com/feed/weather.ashx?q=";
	public static final String URL_2 = "&format=json&num_of_days=5&key=";
	
	
	
	TextView descText;
	TextView cityText;
	TextView tempText;
	TextView precipText;
	TextView windSpeedText;
	TextView maxText;
	TextView minText;
	
	
	ImageButton searchButton;
	Button gpsButton;
	Button savedLocButton;
	
	EditText searchQueryCity;
	EditText searchQueryCountry;
	
	private LocationManager locationManager;
	private String provider;
	private Context myContext;
	
	public String addressText = "";
	public String city = "";
	public String country = "";
	public String state = "";
	public String addressLine = "";
	
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
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
    
        myContext = getApplicationContext();
        messageHandler = new MessageHandler(this);
        
        iv = (ImageView) findViewById(R.id.weatherIcon);
        descText = (TextView) findViewById(R.id.descrip);
        cityText = (TextView) findViewById(R.id.location);
        tempText = (TextView) findViewById(R.id.temp);
        precipText = (TextView) findViewById(R.id.precip);
        windSpeedText = (TextView) findViewById(R.id.windSpeed);
        maxText = (TextView) findViewById(R.id.maxTextView);
        minText = (TextView) findViewById(R.id.minTextView);
        
        gpsButton = (Button) findViewById(R.id.gpsButton);
        savedLocButton = (Button) findViewById(R.id.saveLocButton);
        searchButton = (ImageButton) findViewById(R.id.searchButton);
        
        searchQueryCity = (EditText) findViewById(R.id.editText1);
        searchQueryCountry = (EditText) findViewById(R.id.editText2);
        //searchQueryEditText.setVisibility(View.GONE);
        
        

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
           
        
        searchButton.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0){
				if(searchQueryCity.getText().equals(null)||searchQueryCountry.getText().equals(null)){
					Toast.makeText(myContext, "Please enter and city and country to search", Toast.LENGTH_SHORT).show();
				}else{
					Intent in = new Intent(MainActivity.this, WeatherSearch.class);
					Bundle bun = new Bundle();
					bun.putString("city", searchQueryCity.toString());
					bun.putString("country", searchQueryCountry.toString());
					in.putExtras(bun);
					startActivity(in);
				}	
			}
		});
         
        
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
        // Criteria to select best provider
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        location = locationManager.getLastKnownLocation(provider);
        
        
		if (location != null) {
			System.out.println("Provider: " + provider + " has been selected.");
			onLocationChanged(location);
		} else {
			descText.setText("Location not available");
		}

        gpsButton.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				ReverseGeocodingTask rgt = new ReverseGeocodingTask(myContext);
				rgt.execute(location, null, null);
				
			}
		});
     
        ReverseGeocodingTask rgt = new ReverseGeocodingTask(myContext);
		rgt.execute(location, null, null);
		
    }

    /* Request updates at startup */
    @Override
    protected void onResume() {
      super.onResume();
      locationManager.requestLocationUpdates(provider, 400, 1, this);
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
    }
   
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity, menu);
        return true;
    }

    
    public void hideViews() {
    	descText.setVisibility(View.GONE);
    	cityText.setVisibility(View.GONE);
    	tempText.setVisibility(View.GONE);
    	precipText.setVisibility(View.GONE);
    	windSpeedText.setVisibility(View.GONE);
    	maxText.setVisibility(View.GONE);
    	minText.setVisibility(View.GONE);
    	iv.setVisibility(View.GONE);
    	
    }
    
    public void showViews() {
    	descText.setVisibility(View.VISIBLE);
    	cityText.setVisibility(View.VISIBLE);
    	tempText.setVisibility(View.VISIBLE);
    	precipText.setVisibility(View.VISIBLE);
    	windSpeedText.setVisibility(View.VISIBLE);
    	maxText.setVisibility(View.VISIBLE);
    	minText.setVisibility(View.VISIBLE);
    	iv.setVisibility(View.VISIBLE);
    	
    }
    
	// AsyncTask encapsulating the reverse-geocoding API. Since the geocoder API
	// is blocked,
	// we do not want to invoke it from the UI thread.
	public class ReverseGeocodingTask extends AsyncTask<Location, Void, Void> {
		
		

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
			Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());

			
			
			Location loc = params[0];
			List<Address> addresses = null;
			try {
				// Call the synchronous getFromLocation() method by passing in
				
				// the lat/long values.
				
				addressSearchList = geocoder.getFromLocationName("Palatine, IL, USA", 10);
				
				addresses = geocoder.getFromLocation(loc.getLatitude(),
						loc.getLongitude(), 1);
			} catch (IOException e) {
				e.printStackTrace();
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

			}
			// parsing the Data
			//String key = "&format=json&num_of_days=5&key=845adebec4142346121409";
			
			String url = URL_1 + lat + ".00," + lng + ".00" + URL_2+API_KEY;
			urlBundle.putString("URL", url);
			Log.i("URL", url);
			parsingHandler = new ParsingHandler(url);
			parsingHandler.startParsing();
			fiveDay = parsingHandler.getFiveDay();
			curr = parsingHandler.getCurr();

			if(addressSearchList != null && addressSearchList.size() > 0) {
				for(int i=0; i < addressSearchList.size(); i++) {
					Log.i("Search Results",""+ addressSearchList.get(i).getAddressLine(1));
				}
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			String maxF = fiveDay.get(0).getMaxF();
			String minF = fiveDay.get(0).getMinF();
			
			//Log.i("COORDINATES","Long: "+lng +"Lat: "+lat);
			descText.setText(curr.getValue());
			tempText.setText(curr.getF() + "\u00B0 F");
			precipText.setText("Precipitation: "+curr.getPercip());
			windSpeedText.setText("Wind Speed:"+curr.getMph() + " mph");
			//maxText.setText("Max: "+curr.getMaxF() + "\u00B0 F");
			//minText.setText("Min: "+curr.getMinF() + "\u00B0 F");
			maxText.setText("Max: "+maxF + "\u00B0 F  /");
			minText.setText("  Min: "+minF + "\u00B0 F");
			
			String weatherCode = curr.getWeatherCode();
			//Log.i("WEATHER CODE", ""+weatherCode);
			WeatherCode wc = new WeatherCode(Integer.parseInt(weatherCode));
			cityText.setText(addressLine+"\n"+country);
			iv.setImageResource(wc.getDrawableIcon());
			
			
			showViews();
			messageHandler.sendEmptyMessage(0);
			
		}

		
	}

}
