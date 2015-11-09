package com.digin.www.digin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "Digin_log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.i(TAG, "Login activity created");

        //login event listener
        Button loginBtn = (Button) findViewById(R.id.btnLogin);
        final EditText uName = (EditText) findViewById(R.id.txtUsername);
        final EditText uPwd = (EditText) findViewById(R.id.txtPassword);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "Login initiated " + uName.getText() + " " + uPwd.getText());
                attemptLogin(uName.getText().toString(), uPwd.getText().toString());
            }
        });


    }

    public void showToast(String msg){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.basic_toast,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(msg);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 20);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    private boolean isEmailValid(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return email.matches(EMAIL_PATTERN);
    }

    //TODO if there is a password validation
    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private void attemptLogin(String uname,String pwd) {
        if(!uname.equals("")){
            if(!pwd.equals("")){
                if(isEmailValid(uname)){
                    final ProgressBar loginProgress = (ProgressBar) findViewById(R.id.loginProgressBar);
                    loginProgress.setVisibility(View.VISIBLE);
                    String url = "http://"+getString(R.string.auth_uri)+"/Login/"+uname+"/"+pwd+"/"+getString(R.string.base_uri);
                    Log.i(TAG,url);
                    JsonObjectRequest jsonRequest = new JsonObjectRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // the response is already constructed as a JSONObject!
                                    try {
                                        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.authObj), MODE_PRIVATE).edit();
                                        editor.putString("UserID", response.getString("UserID"));
                                        editor.putString("Username", response.getString("Username"));
                                        editor.putString("Name", response.getString("Name"));
                                        editor.putString("Email", response.getString("Email"));
                                        editor.putString("SecurityToken", response.getString("SecurityToken"));
                                        editor.commit();
                                        loginProgress.setVisibility(View.INVISIBLE);
                                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                }
                            });

                    Volley.newRequestQueue(this).add(jsonRequest);


                }else showToast(getString(R.string.errorInvalidUsername));
            }else showToast(getString(R.string.errorEmptyPassword));
        }else showToast(getString(R.string.errorEmptyUsername));
    }


}
