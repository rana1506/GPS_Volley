package com.example.gps_volley;

import android.app.Activity;
import android.location.Location;
import android.widget.TextView;

public class ThreadMain extends MyThreadHandlerClass{
    MainActivity mainactivity;
    Location location;
    String temp1, temp2;
    ThreadGPS threadGPS;
    ThreadVolley threadVolley;

    public ThreadMain(int delay,MainActivity activity)
    {
        super(delay);
        mainactivity= activity;
        temp1=temp2="";
        location=new Location(""); location.setLatitude(22); location.setLongitude(91);

        threadGPS=new ThreadGPS(mainactivity.getApplicationContext(), 20000);
        threadVolley=new ThreadVolley(mainactivity.getApplicationContext(),30000);
    }

    @Override
    public void doWork() {
        temp1=threadGPS.text;
        location=threadGPS.location;
        threadVolley.location=location;
        temp1=threadGPS.x+" : "+String.valueOf(location.getLatitude()+" "+String.valueOf(location.getLongitude()));

        //temp2=threadVolley.text;
        mainactivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainactivity.textView1.setText(temp1);// present location obtained
                mainactivity.textView2.setText(threadVolley.location.toString());// location send to web
            }
        });
    }
}
