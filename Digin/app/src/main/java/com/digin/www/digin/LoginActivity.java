package com.digin.www.digin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "Digin_log";

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
        if(!uname.equals("")){
            if(!pwd.equals("")){
                if(isEmailValid(uname)){

                }else showToast(getString(R.string.errorInvalidUsername));
            }else showToast(getString(R.string.errorEmptyPassword));
        }else showToast(getString(R.string.errorEmptyUsername));
    }


}
