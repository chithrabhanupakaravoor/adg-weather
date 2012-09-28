package com.adg.weather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.adg.object.Weather;
import com.adg.parser.ParsingHandler;

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
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener {

	public TextView tv;
	private LocationManager locationManager;
	private String provider;
	private Context myContext;
	public String addressText = "";
	ArrayList<Weather> fiveDay = new ArrayList<Weather>();
	Weather curr = new Weather();
	long lat;
	long lng;
	ParsingHandler parsingHandler;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    
        myContext = getApplicationContext();
        
        tv = (TextView) findViewById(R.id.textView1);
        
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
        // Criteria to select best provider
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);
        
        
		if (location != null) {
			System.out.println("Provider: " + provider + " has been selected.");
			onLocationChanged(location);
		} else {
			tv.setText("Location not available");
		}
		
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
      tv.setText("Latitude: "+lat+ "\nLongitude: "+lng);
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
      // TODO Auto-generated method stub

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

	// AsyncTask encapsulating the reverse-geocoding API. Since the geocoder API
	// is blocked,
	// we do not want to invoke it from the UI thread.
	public class ReverseGeocodingTask extends AsyncTask<Location, Void, Void> {
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			tv.setText(addressText);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		Context mContext;

		public ReverseGeocodingTask(Context context) {
			super();
			mContext = context;
		}

		@Override
     protected Void doInBackground(Location... params) {
         Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());

         Location loc = params[0];
         List<Address> addresses = null;
         try {
             // Call the synchronous getFromLocation() method by passing in the lat/long values.
             addresses = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
         } catch (IOException e) {
             e.printStackTrace();
         }
         if (addresses != null && addresses.size() > 0) {
             Address address = addresses.get(0);
             // Format the first line of address (if available), city, and country name.
             addressText = String.format("%s, %s, %s",
                     address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                     address.getLocality(),
                     address.getCountryName());
             
             
         }
         // parsing the Data
         	String key = "&format=json&num_of_days=5&key=845adebec4142346121409";
			String begining = "http://free.worldweatheronline.com/feed/weather.ashx?q=";//[lat],[lon]
			String url = begining + lat + "," + lng + key;
			parsingHandler = new ParsingHandler(url);
			fiveDay = parsingHandler.getFiveDay();
			curr = parsingHandler.getCurr();
         return null;
     }
		
		
	}

}
