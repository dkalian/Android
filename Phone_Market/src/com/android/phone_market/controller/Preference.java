package com.android.phone_market.controller;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockPreferenceActivity;

/**
 * @author Николай
 * 
 */
public class Preference extends SherlockPreferenceActivity {
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preference);

	}
}
