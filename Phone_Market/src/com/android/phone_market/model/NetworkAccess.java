package com.android.phone_market.model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Николай
 * 
 */
public class NetworkAccess {
	private Context context;

	/**
	 * @param context
	 *            передаваемый конкест
	 */
	public NetworkAccess(Context context) {
		this.context = context;
	}

	/**
	 * Проверяет соединение с интернетом
	 * 
	 * @return true, если есть соединение с интернетом
	 */
	public boolean CheckConnection() {
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		return false;
	}
}
