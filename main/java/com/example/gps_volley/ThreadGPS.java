package com.example.gps_volley;

import android.content.Context;
import android.location.Location;

public class ThreadGPS extends MyThreadHandlerClass{
    int x=0;
    String text="loading...";
    Location location;
    GPSTracker gpsTracker;
    Context context;
    public ThreadGPS(Context context, int delay)
    {
        super(delay);
        this.context=context;
        gpsTracker=new GPSTracker(context);

        location=new Location(""); location.setLatitude(22); location.setLongitude(91);
    }

    @Override
    public void doWork()
    {
        x++;
        try {
            Location location_temp = gpsTracker.getLocation();
            if (location_temp != null)
                location = location_temp;
        }catch (Exception ex){}

        text=String.valueOf(x)+": "+Double.toString(location.getLatitude()) + " " +  Double.toString(location.getLongitude());
    }
}
