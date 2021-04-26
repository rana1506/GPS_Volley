package com.example.gps_volley;

import android.content.Context;
import android.location.Location;

public class ThreadVolley extends MyThreadHandlerClass{
    int x=0;
    String text="loading...";
    String latitude,longitude,id;
    Location location;
    VolleyFunctions volleyFunctions;
    Context context;
    public ThreadVolley(Context context, int delay)
    {
        super(delay);
        this.context=context;

        location=new Location("");
        location.setLatitude(22);
        location.setLongitude(91);

        volleyFunctions=new VolleyFunctions(context);

        id="akash";
    }

    @Override
    public void doWork()
    {
        x++;
        id=volleyFunctions.getMacAddress();
        latitude= String.valueOf(location.getLatitude());
        longitude= String.valueOf(location.getLongitude());
        try {
            volleyFunctions.postItem(id, latitude, longitude);
        }catch (Exception ex){}
        text=latitude + " " +  longitude;
    }
}
