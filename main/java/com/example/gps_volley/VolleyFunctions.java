package com.example.gps_volley;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



        import android.app.ProgressDialog;
        import android.content.Context;
        import android.util.Log;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;

        import com.android.volley.DefaultRetryPolicy;
        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.RetryPolicy;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonObjectRequest;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Map;

public class VolleyFunctions {

    String url;
    Context context;
    RequestQueue queue;
    ProgressDialog loading;
    String mainobj=null;



    public VolleyFunctions(Context context)
    {
        this.context=context;
        url = "https://script.google.com/your_address";
    }

    public String getItems()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mainobj=response;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        queue = Volley.newRequestQueue(this.context);  // this = context
        queue.add(stringRequest);
        return  mainobj;
    }
    public void postItem(final String id, final String lat, final String lng)
    {
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        mainobj=response;
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                        mainobj=error.toString();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("action", "addItem");
                params.put("id", id);
                params.put("lat", lat);
                params.put("lng", lng);

                return params;
            }
        };
        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        postRequest.setRetryPolicy(policy);
        queue = Volley.newRequestQueue(this.context);  // this = context
        queue.add(postRequest);
    }
    public void getLastLat()
    {
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response

                        Log.d("Response", response);
                        mainobj=response;
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                        mainobj=error.toString();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("action", "getLastLat");
                return params;
            }
        };
        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        postRequest.setRetryPolicy(policy);
        queue = Volley.newRequestQueue(this.context);  // this = context
        queue.add(postRequest);
    }
    public void getLatLong()  {
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        try{
                            JSONObject jsonObject= new JSONObject(response.toString());
                            //extracting json array from response string
                            //JSONArray jsonArray = jsonObject.getJSONArray("items");
                            //JSONObject jsonRow = jsonArray.getJSONObject(0);
                            //get value from jsonRow
                            String latstring = jsonObject.getString("lat");
                            String longstring = jsonObject.getString("lng");
                            mainobj=latstring+"  "+longstring;
                        }catch (Exception ex)
                        {
                            mainobj=ex.toString();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                        mainobj=error.toString();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("action", "getLatLong");
                return params;
            }
        };
        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        postRequest.setRetryPolicy(policy);
        queue = Volley.newRequestQueue(this.context);  // this = context
        queue.add(postRequest);
    }
    public String getMacAddress() {
        /*WifiManager wimanager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String macAddress = wimanager.getConnectionInfo().getMacAddress();
        if (macAddress == null) {
            macAddress = "unknown";//"Device don't have mac address or wi-fi is disabled";
        }
        return macAddress;*/
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }

}

