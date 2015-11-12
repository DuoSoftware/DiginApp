package com.digin.www.digin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Senal on 11/11/2015.
 */
public class DuoStoreService {

    private  final String TAG = "Digin_log";
    private JSONObject resultObj = new JSONObject();

    public JSONObject getDashboards(){
//        String url = "http://"+getString(R.string.auth_uri)+"/Login/"+uname+"/"+pwd+"/"+getString(R.string.base_uri);
//        Log.i(TAG, url);
//        JsonObjectRequest jsonRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        // the response is already constructed as a JSONObject!
//                        try {
//                            SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.authObj), MODE_PRIVATE).edit();
//                            editor.putString("UserID", response.getString("UserID"));
//                            editor.putString("Username", response.getString("Username"));
//                            editor.putString("Name", response.getString("Name"));
//                            editor.putString("Email", response.getString("Email"));
//                            editor.putString("SecurityToken", response.getString("SecurityToken"));
//                            editor.commit();
//                            loginProgress.setVisibility(View.INVISIBLE);
//                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                            startActivity(intent);
//                            finish();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        error.printStackTrace();
//                    }
//                });
//
//        Volley.newRequestQueue(this).add(jsonRequest);
        return null;
    }
}
