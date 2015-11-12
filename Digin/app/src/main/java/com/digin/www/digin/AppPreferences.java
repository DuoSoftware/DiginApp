package com.digin.www.digin;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Senal on 11/12/2015.
 */
public class AppPreferences {
    public static final String KEY_PREFS_SOME_STRING = "some_string";
    private static final String APP_SHARED_PREFS = AppPreferences.class.getSimpleName(); //  Name of the file -.xml
    private SharedPreferences _sharedPrefs;
    private SharedPreferences.Editor _prefsEditor;

    public AppPreferences(Context context, String prefName) {
        this._sharedPrefs = context.getSharedPreferences(prefName, Activity.MODE_PRIVATE);
        this._prefsEditor = _sharedPrefs.edit();
    }

    public String getSomeString(String key) {
        return _sharedPrefs.getString(key, null); // Get our string from prefs or return an empty string
    }

    public void saveSomeString(String key,String text) {
        _prefsEditor.putString(key, text);
        _prefsEditor.commit();
    }
}
