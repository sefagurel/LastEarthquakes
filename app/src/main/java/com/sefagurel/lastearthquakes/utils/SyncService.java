package com.sefagurel.lastearthquakes.utils;

import java.util.List;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.sefagurel.lastearthquakes.MainActivity;
import com.sefagurel.lastearthquakes.R;
import com.sefagurel.lastearthquakes.database.EarthQuakes;
import com.sefagurel.lastearthquakes.database.LastEarthquakeDate;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */
public class SyncService extends Service {

	private int				mStartMode;
	private IBinder			mBinder;
	private boolean			mAllowRebind;
	private Handler			handler;

	public static boolean	isServiceRunning	= false;

	public static Context	AppContextService;

	@Override
	public void onCreate() {
		AppContextService = getApplicationContext();
		App.bus.register(this);
		handler = new Handler(Looper.getMainLooper());
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		isServiceRunning = true;

		new Thread(new Runnable() {
			@Override
			public void run() {

				while (isServiceRunning) {

					if (Tools.isOnline(AppContextService)) {

						Log.i("SyncService", "Service Running");

						updateSyncPeriod();

						int day = AppSettings.getInstance().getTimeInterval();
						int source = AppSettings.getInstance().getSource();

						SaveResponseToDB clientHelper = new SaveResponseToDB();

						if (source == 0) {
							clientHelper.saveDatabaseUsgs(CreateRequestUrl.URL_USGS(day));
							clientHelper.saveDatabaseSeismicPortal(CreateRequestUrl.URL_SEISMICPORTAL(day));
							clientHelper.saveDatabaseKoeri(CreateRequestUrl.URL_KOERI(day));
						}
						else if (source == 1) {
							clientHelper.saveDatabaseKoeri(CreateRequestUrl.URL_KOERI(day));
						}
						else if (source == 2) {
							clientHelper.saveDatabaseSeismicPortal(CreateRequestUrl.URL_SEISMICPORTAL(day));
						}
						else if (source == 3) {
							clientHelper.saveDatabaseUsgs(CreateRequestUrl.URL_USGS(day));
						}

						notificationHandler();

						App.bus.post(new EBus(123));

					}
					else {
						App.bus.post(new EBus(999));
					}

					try {
						Thread.sleep(1000 * 60 * Tools.syncPeriod);
					}
					catch (InterruptedException e) {
						Tools.catchException(e);
					}
				}

			}
		}).start();

		return Service.START_STICKY;
	}

	private void updateSyncPeriod() {

		int refreshtime = AppSettings.getInstance().getUpdatePeriod();

		if (refreshtime == 0) {
			Tools.syncPeriod = 2;
		}
		else if (refreshtime == 1) {
			Tools.syncPeriod = 15;
		}
		else if (refreshtime == 2) {
			Tools.syncPeriod = 30;
		}
		else if (refreshtime == 3) {
			Tools.syncPeriod = 60;
		}
		else {
			Tools.syncPeriod = 2;
		}
	}

	private void notificationHandler() {

		handler.post(new Runnable() {
			@Override
			public void run() {
				showNotification();
			}
		});
	}

	private void showNotification() {
		List<EarthQuakes> newEarthquakes = new EarthQuakes().newEarthquakes();

		if (newEarthquakes.size() > 0) {

			if (AppSettings.getInstance().isNotifications()) {
				createNotification(getString(R.string.EarthquakesDetect), //
						"" + newEarthquakes.get(0).getMagnitude() + "  |  " + newEarthquakes.get(0).getLocationName());
			}

			LastEarthquakeDate led = new LastEarthquakeDate();
			led.setDateMilis(new EarthQuakes().GetLastEarthQuakeDate());
			led.Insert();
		}
	}

	private void createNotification(String strContentTitle, String strContentText) {

		NotificationCompat.Builder builder = new NotificationCompat.Builder(SyncService.this) //
				.setSmallIcon(R.drawable.icon1) //
				.setContentTitle(strContentTitle) //
				.setContentText(strContentText);

		Intent resultIntent = new Intent(this, MainActivity.class);
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(MainActivity.class);
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

		builder.setContentIntent(resultPendingIntent);
		builder.setAutoCancel(true);
		builder.setLights(Color.BLUE, 500, 500);

		if (AppSettings.getInstance().isVibration()) {
			long[] pattern = { 500, 500 };
			builder.setVibrate(pattern);
		}
		if (AppSettings.getInstance().isSound()) {
			Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			builder.setSound(alarmSound);
		}

		NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		manager.notify(0, builder.build());
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return mAllowRebind;
	}

	@Override
	public void onRebind(Intent intent) {

	}

	@Override
	public void onDestroy() {
		isServiceRunning = false;
		App.bus.unregister(this);
	}

}
