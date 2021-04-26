package com.example.gps_volley;
import android.os.Handler;// We need to use this Handler package
public class MyThreadHandlerClass {
    public Handler handler;
    public Runnable runnableCode;

    public MyThreadHandlerClass(int delay) {
        handler = new Handler();
        // Define the code block to be executed
        runnableCode = new Runnable() {
            @Override
            public void run() {
                // Do something here on the main thread
                //Log.d("Handlers", "Called on main thread");
                // Repeat this the same runnable code block again another 2 seconds
                // 'this' is referencing the Runnable object
                doWork();
                handler.postDelayed(this, delay);
            }
        };
        // Start the initial runnable task by posting through the handler
        handler.post(runnableCode);
    }
    public void doWork(){}
}
