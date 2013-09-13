package com.android.phone_market.model.Service;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.android.phone_market.model.NetworkAccess;
import com.android.phone_market.model.AlarmManager.RepeatService;
import com.android.phone_market.model.AlarmManager.TimeManager;
import com.android.phone_market.model.DataBaseOperations.DataBaseWorker;
import com.android.phone_market.model.Threads.ServiceThread;

/**
 * @author Николай
 * 
 */
public class BackgroungService extends Service {

	final String LogTag = "BackgroundService: ";
	private static ExecutorService executor;
	NotificationManager nm;
	private AlarmManager alarmManager;

	/**
	 * Empty constructor
	 */
	public BackgroungService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(LogTag, "Service on create");
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

	}

	/**
	 * Start service
	 * 
	 * @throws SQLException
	 *             exception in SQL request
	 * @throws ClassNotFoundException
	 *             class not found
	 * @throws IllegalAccessException
	 *             illegal access exception
	 * @throws InstantiationException
	 *             instant exception
	 */

	public void onStart() {
		Log.d(LogTag, "Service on start");
		search();
	}

	/**
	 * Search on database
	 */
	void search() {
		if (new NetworkAccess(this).CheckConnection()) {
			Log.w(LogTag, "Getting SQL request: Start Request");
			String SQL = new DataBaseWorker(this).GetSavedSQL();
			Log.w(LogTag, SQL);
			Log.w(LogTag, "End Request");
			if (SQL != null) {
				Toast.makeText(
						this,
						"Автопоиск зарущен: время обновления - "
								+ TimeManager
										.GetTimeUpdate(BackgroungService.this)
								/ 60000 + " мин.", Toast.LENGTH_LONG).show();

				if (executor == null) {
					executor = Executors.newFixedThreadPool(1);
					executor.execute(new ServiceThread(this, SQL, nm));
					Log.d("SQL Request", SQL);
				} else {
					executor.execute(new ServiceThread(this, SQL, nm));
					Log.d("SQL Request", SQL);
				}
				setAlarmManager();
			} else {
				Toast.makeText(this, "Ошибка при чтении SQL запроса",
						Toast.LENGTH_LONG).show(); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			}
		} else {
			Toast.makeText(this, "Автообновление: Нет доступа к интернету.",
					Toast.LENGTH_LONG).show();
		}

	}

	private void setAlarmManager() {
		Intent intent = new Intent(this, RepeatService.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
				intent, 0);
		alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
				System.currentTimeMillis() + 10000,
				TimeManager.GetTimeUpdate(BackgroungService.this),
				pendingIntent);

	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "Автопоиск остановлен.", Toast.LENGTH_SHORT)
				.show();
		Log.d(LogTag, "Service on destroy");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		search();
		return super.onStartCommand(intent, flags, startId);
	}
}
