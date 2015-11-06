package com.digin.www.digin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "Digin_log";
    private static int progress = View.INVISIBLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.i(TAG,"Login activity created");

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
        progress = View.VISIBLE;
        if(!uname.equals("")){
            if(!pwd.equals("")){
                if(isEmailValid(uname)){
                    final ProgressBar loginProgress = (ProgressBar) findViewById(R.id.loginProgressBar);
                    loginProgress.setVisibility(View.VISIBLE);
                    String url = "http://"+getString(R.string.auth_uri)+"/Login/"+uname+"/"+pwd+"/"+getString(R.string.base_uri);

                    // Request a string response
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    // Result handling
                                    Log.i(TAG,response);
                                    loginProgress.setVisibility(View.INVISIBLE);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            // Error handling
                            Log.i(TAG,"Something went wrong!");
                            error.printStackTrace();

                        }
                    });

// Add the request to the queue
                    Volley.newRequestQueue(this).add(stringRequest);
                }else showToast(getString(R.string.errorInvalidUsername));
            }else showToast(getString(R.string.errorEmptyPassword));
        }else showToast(getString(R.string.errorEmptyUsername));
    }


}
