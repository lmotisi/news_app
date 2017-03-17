package com.example.lmotisi.myapp;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class CategoriePreferenceFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }

}
