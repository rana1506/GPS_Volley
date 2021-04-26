package com.example.gps_volley;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class GPSTracker implements LocationListener {
    LocationManager locationManager;
    Location location;
    Context context;
    GPSTracker(Context context)
    {
        this.context=context;
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {   //Toast.makeText(context,"Permition not granted",Toast.LENGTH_SHORT).show();
            Log.w("GPSTracker()","permission not granted");
        }
        locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 1000, 0, this);
    }
    public Location getLocation()
    {
        @SuppressLint("MissingPermission") Location l=locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
        Log.w("getLocation()","gps enabled: ");
        return l;
    }
    @Override
    public void onLocationChanged(Location location) { }
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) { }
    @Override
    public void onProviderEnabled(String s) { }
    @Override
    public void onProviderDisabled(String s) { }
}
