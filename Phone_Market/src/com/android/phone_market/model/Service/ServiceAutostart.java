package com.android.phone_market.model.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * @author Nickolai
 *
 */
public class ServiceAutostart extends BroadcastReceiver {

	private SharedPreferences sp;

	@Override
	public void onReceive(Context context, Intent intent) {
		sp = PreferenceManager.getDefaultSharedPreferences(context);
		boolean autostart = sp.getBoolean("autostart", true);
		if (("android.intent.action.BOOT_COMPLETED".equals(intent.getAction()) && (autostart == true))) {
			context.startService(new Intent(context, BackgroungService.class));
		}
	}
}
