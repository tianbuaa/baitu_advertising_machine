package com.skynet.adplayer;

import android.content.SharedPreferences;
import android.os.Build;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    getFragmentManager().beginTransaction()
            .replace(R.id.preference_block, new SettingsFragment())
            .commit();
}


    public static class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.addPreferencesFromResource(R.xml.preferences);

        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
            initSummaries();
        }

        private void initSummaries() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            updateStringSummary(prefs, "pref_key_model_name", Build.MANUFACTURER+" "+Build.MODEL);
            updateStringSummary(prefs, "pref_key_serial_number", Build.SERIAL);
            updateStringSummary(prefs, "pref_key_apk_version", BuildConfig.VERSION_NAME);
        }

        private void updateStringSummary(SharedPreferences prefs, String key, String value) {
            Preference pref = findPreference(key);
            pref.setSummary(value);
            //Log.i(TAG, "Prereference " + key + " value is " + prefs.getString(key, ""));
        }
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        }
    }
}
