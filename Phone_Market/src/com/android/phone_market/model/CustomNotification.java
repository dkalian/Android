package com.android.phone_market.model;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.phone_market.controller.R;
import com.android.phone_market.controller.ResultsSearchActivity;
import com.android.phone_market.model.Threads.DataBaseOperation.ServiceWorkResultsReader;

/**
 * @author Nickolai
 *
 */
public class CustomNotification {

	String LogTag = "Custom Notification: ";
	
	/**
	 * @param context Context
	 * @param StatusBarText Text in status bar
	 * @param Title Notification title
	 * @param Message Notification message
	 * @param nm Notification Mannager
	 */
	@SuppressWarnings("deprecation")
	public void ShowNotification(Context context, String StatusBarText,
			String Title, String Message, NotificationManager nm) {
		Notification notification = new Notification(R.drawable.ic_launcher,
				StatusBarText, System.currentTimeMillis());
		Intent intent = new Intent(context, ResultsSearchActivity.class);
		Log.i(LogTag, "Getting Results Search...");
		Log.i(LogTag, "Success! Start results:");
		System.out.println(ServiceWorkResultsReader.Read(context,
				Constant.TABLE_NAME_SERVICE_SEARCH_RESULTS));
		Log.i(LogTag, "Success! End results");
		intent.putExtra("results", ServiceWorkResultsReader.Read(context,
				Constant.TABLE_NAME_SERVICE_SEARCH_RESULTS));
		PendingIntent pIntent = PendingIntent.getActivity(context,
				Constant.REQUEST_CODE_NOTIFOCATION, intent, 0);
		notification.setLatestEventInfo(context, Title, Message, pIntent);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		nm.notify(1, notification);
	}
}
