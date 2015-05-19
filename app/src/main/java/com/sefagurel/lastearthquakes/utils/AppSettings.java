package com.sefagurel.lastearthquakes.utils;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.sefagurel.lastearthquakes.R;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */
public class AppSettings {

	public static AppSettings	pojoPref	= null;
	private static Context		ctx			= App.AppContext;

	private int					TimeInterval, Source, Magnitude, MapType, UpdatePeriod, Sorting;
	private boolean				Notifications, Vibration, Sound;

	private String				Key_TimeInterval, Key_Source, Key_Magnitude, Key_MapType;
	private String				Key_UpdatePeriod, Key_Sorting, Key_Notifications, Key_Vibration, Key_Sound;

	AppSettings() {

		Key_TimeInterval = ctx.getResources().getString(R.string.listPref_Key_TimeInterval);
		Key_Source = ctx.getResources().getString(R.string.listPref_Key_Source);
		Key_Magnitude = ctx.getResources().getString(R.string.listPref_Key_Magnitude);
		Key_MapType = ctx.getResources().getString(R.string.listPref_Key_MapType);
		Key_UpdatePeriod = ctx.getResources().getString(R.string.listPref_Key_UpdatePeriod);
		Key_Sorting = ctx.getResources().getString(R.string.listPref_Key_Sorting);
		Key_Notifications = ctx.getResources().getString(R.string.CheckBoxPref_Key_Notifications);
		Key_Vibration = ctx.getResources().getString(R.string.CheckBoxPref_Key_Vibration);
		Key_Sound = ctx.getResources().getString(R.string.CheckBoxPref_Key_Sound);

		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(ctx);
		Map<String, ?> allEntries = pref.getAll();

		String asd = (String) allEntries.get(Key_TimeInterval);

		TimeInterval = Integer.parseInt(asd);
		Source = Integer.parseInt((String) allEntries.get(Key_Source));
		Magnitude = Integer.parseInt((String) allEntries.get(Key_Magnitude));
		MapType = Integer.parseInt((String) allEntries.get(Key_MapType));
		UpdatePeriod = Integer.parseInt((String) allEntries.get(Key_UpdatePeriod));
		Sorting = Integer.parseInt((String) allEntries.get(Key_Sorting));

		Notifications = (Boolean) allEntries.get(Key_Notifications);
		Vibration = (Boolean) allEntries.get(Key_Vibration);
		Sound = (Boolean) allEntries.get(Key_Sound);

		// for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
		// Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
		// }

	}

	public static void setDefaultSettings() {
		PreferenceManager.setDefaultValues(ctx, R.xml.pref, false);
	}

	public static AppSettings getInstance() {
		return pojoPref == null ? new AppSettings() : pojoPref;
	}

	public int getTimeInterval() {
		return TimeInterval;
	}

	public void setTimeInterval(int timeInterval) {
		TimeInterval = timeInterval;
	}

	public int getSource() {
		return Source;
	}

	public void setSource(int source) {
		Source = source;
	}

	public int getMagnitude() {
		return Magnitude;
	}

	public void setMagnitude(int magnitude) {
		Magnitude = magnitude;
	}

	public int getMapType() {
		return MapType;
	}

	public void setMapType(int mapType) {
		MapType = mapType;
	}

	public int getUpdatePeriod() {
		return UpdatePeriod;
	}

	public void setUpdatePeriod(int updatePeriod) {
		UpdatePeriod = updatePeriod;
	}

	public int getSorting() {
		return Sorting;
	}

	public void setSorting(int sorting) {
		Sorting = sorting;
	}

	public boolean isSound() {
		return Sound;
	}

	public void setSound(boolean sound) {
		Sound = sound;
	}

	public boolean isVibration() {
		return Vibration;
	}

	public void setVibration(boolean vibration) {
		Vibration = vibration;
	}

	public boolean isNotifications() {
		return Notifications;
	}

	public void setNotifications(boolean notifications) {
		Notifications = notifications;
	}
}
