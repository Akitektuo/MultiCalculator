package com.akitektuo.dividemynumber;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by AoD Akitektuo on 10-Jun-17 at 18:14.
 */

class Preference {

    static final String KEY_INITIALIZE = "initialize";
    static final String KEY_LAYOUT = "layout";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    Preference(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(KEY_INITIALIZE, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
    }

    private void savePreferences() {
        editor.commit();
    }

    void setPreference(String key, int layout) {
        editor.putInt(key, layout);
        savePreferences();
    }

    int getPreferenceInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }
}
