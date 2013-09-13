package com.android.phone_market.model.AlarmManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * @author Nickolai
 *
 */
public class TimeManager {
	
	/**
	 * This method get Time Update
	 * @param context Context
	 * @return int Time Update
	 */
	public static int GetTimeUpdate(Context context) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		return Integer.parseInt(sp.getString("TimeUpdate", String.valueOf(3)))*60000;
	}
}
