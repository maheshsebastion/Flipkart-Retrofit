package com.example.admin.flipkart.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.admin.flipkart.activity.MainActivity;
import com.example.admin.flipkart.app.AppActivity;

import java.util.HashMap;

/**
 * Created by Admin on 07-09-2017.
 */

public class SessionManager extends AppActivity{
    // Shared Preferences
    SharedPreferences sharedpreferences;

    //Context
    Context context;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    //Constructor for SessionManager
    public SessionManager(Context context) {

        this.context = context;
        sharedpreferences = context.getSharedPreferences(PREF_NAME,context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

    }

    //Create login session
    public void createLoginSession(String name, String email){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, name);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        // commit changes
        editor.commit();
    }

    //Get Stored Session
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, sharedpreferences.getString(KEY_NAME, null));

        // user email id
        user.put(KEY_EMAIL, sharedpreferences.getString(KEY_EMAIL, null));

        // return user
        return user;
    }

    //Clear Session Details
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(context, MainActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        context.startActivity(i);
        finish();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            context.startActivity(i);
            finish();

        }

    }

    // Get Login State
    public boolean isLoggedIn(){
        return sharedpreferences.getBoolean(IS_LOGIN, false);
    }
}
