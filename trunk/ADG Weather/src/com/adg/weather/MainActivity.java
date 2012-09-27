package com.adg.weather;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener {

	private TextView tv;
	private LocationManager locationManager;
	private String provider;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        
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
      int lat = (int) (location.getLatitude());
      int lng = (int) (location.getLongitude());
      tv.setText("Latitude: "+String.valueOf(lat)+ "\nLongitude: "+String.valueOf(lng));
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
}
