package com.digin.www.digin;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by Senal on 11/5/2015.
 */
public class VolleyHelper {
    private JSONObject postObj = new JSONObject();
    private String uri = "";
    private String resObj = "";
    private final String TAG = "";

    public VolleyHelper(String methodUri){
        uri = methodUri;
    }
    public VolleyHelper(JSONObject objData, String methodUri){
        postObj = objData;
        uri = methodUri;
    }

    public String getMethod(Context self){
        String url = "http://httpbin.org/html";

        // Request a string response
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Result handling
                        Log.i(TAG,response.substring(0,100));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                Log.e(TAG,"Something went wrong!");
                error.printStackTrace();

            }
        });

// Add the request to the queue
        Volley.newRequestQueue(self).add(stringRequest);
        return resObj;
    }
}
