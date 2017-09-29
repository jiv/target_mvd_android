package com.jiv.target_mvd.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    public static final String SHARED_PREFERENCES_KEY = "com.jiv.target_mvd";
    public static final String USER_ID = "id";
    public static final String ACCESS_TOKEN = "access-token";
    public static final String CLIENT = "client";
    public static final String UID = "uid";

    private static SharedPreferencesManager instance;

    private SharedPreferences privateSharedPreferences;

    private SharedPreferencesManager(Context context) {

        this.privateSharedPreferences = context.getSharedPreferences(
                SHARED_PREFERENCES_KEY,
                Context.MODE_PRIVATE);
    }

    public static SharedPreferencesManager getInstance(Context context) {

        synchronized (SharedPreferencesManager.class) {
            if (instance == null) {
                instance = new SharedPreferencesManager(context);
            }
            return instance;
        }
    }

    public String getUserId() {

        return getStringFromSharedPreferences(USER_ID);
    }

    public void storeUserId(String id) {

        storeStringInSharedPreferences(USER_ID, id);
    }

    public String getAccessToken() {

        return getStringFromSharedPreferences(ACCESS_TOKEN);
    }

    public String getClient() {

        return getStringFromSharedPreferences(CLIENT);
    }

    public String getUid() {

        return getStringFromSharedPreferences(UID);
    }

    public void storeHeaders(String access_token, String client, String uid) {
        SharedPreferences.Editor editor = privateSharedPreferences.edit();
        editor.putString(ACCESS_TOKEN, access_token);
        editor.putString(CLIENT, client);
        editor.putString(UID, uid);
        editor.apply();
    }

    private String getStringFromSharedPreferences(String key) {

        return privateSharedPreferences.getString(key, "");
    }

    private void storeStringInSharedPreferences(String key, String content) {

        SharedPreferences.Editor editor = privateSharedPreferences.edit();
        editor.putString(key, content);
        editor.apply();
    }
}
