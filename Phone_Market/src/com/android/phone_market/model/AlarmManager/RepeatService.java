package com.android.phone_market.model.AlarmManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.phone_market.model.NetworkAccess;
import com.android.phone_market.model.DataBaseOperations.DataBaseWorker;
import com.android.phone_market.model.Threads.ServiceThread;

/**
 * @author Nickolai
 *
 */
public class RepeatService extends BroadcastReceiver {
	private static ExecutorService executor;
	final String LogTag = "Repeat Service: ";
	private NotificationManager nm;

	/**
	 * Empty constructor
	 */
	public RepeatService() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Log.w(LogTag, "Start On Reseive...");
		if (executor == null) {
			executor = Executors.newFixedThreadPool(1);
		}
		if (new NetworkAccess(context).CheckConnection()) {
			Log.w(LogTag, "Getting SQL request: Start Request");
			String SQL = new DataBaseWorker(context).GetSavedSQL();
			Log.w(LogTag, SQL);
			Log.w(LogTag, "End Request");
			if (SQL != null) {
				if (executor == null) {
					executor = Executors.newFixedThreadPool(1);
					executor.execute(new ServiceThread(context, SQL, nm));
					Log.d("SQL Request", SQL);
				} else {
					executor.execute(new ServiceThread(context, SQL, nm));
					Log.d("SQL Request", SQL);
				}
			} else {
				Toast.makeText(context, "Ошибка при чтении SQL запроса",
						Toast.LENGTH_LONG).show();
			}
		} else {
			Toast.makeText(context, "Автообновление: Нет доступа к интернету.",
					Toast.LENGTH_SHORT).show();
		}
	}
}
