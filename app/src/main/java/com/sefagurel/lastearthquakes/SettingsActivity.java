package com.sefagurel.lastearthquakes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */
public class SettingsActivity extends PreferenceActivity implements OnPreferenceChangeListener, SharedPreferences.OnSharedPreferenceChangeListener {

	private String	Key_TimeInterval, Key_Source, Key_Magnitude, Key_MapType;
	private String	Key_UpdatePeriod, Key_Sorting, Key_Notifications, Key_Vibration, Key_Sound;

	private ListPreference	lpTimeInterval, lpSource, lpMagnitude, lpMapType, lpUpdatePeriod, lpSorting;
	private CheckBoxPreference	cbNotifications, cbVibration, cbSound;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.pref);

		Key_TimeInterval = getResources().getString(R.string.listPref_Key_TimeInterval);
		Key_Source = getResources().getString(R.string.listPref_Key_Source);
		Key_Magnitude = getResources().getString(R.string.listPref_Key_Magnitude);
		Key_MapType = getResources().getString(R.string.listPref_Key_MapType);
		Key_UpdatePeriod = getResources().getString(R.string.listPref_Key_UpdatePeriod);
		Key_Sorting = getResources().getString(R.string.listPref_Key_Sorting);
		Key_Notifications = getResources().getString(R.string.CheckBoxPref_Key_Notifications);
		Key_Vibration = getResources().getString(R.string.CheckBoxPref_Key_Vibration);
		Key_Sound = getResources().getString(R.string.CheckBoxPref_Key_Sound);

		lpTimeInterval = (ListPreference) findPreference(Key_TimeInterval);
		lpSource = (ListPreference) findPreference(Key_Source);
		lpMagnitude = (ListPreference) findPreference(Key_Magnitude);
		lpMapType = (ListPreference) findPreference(Key_MapType);
		lpUpdatePeriod = (ListPreference) findPreference(Key_UpdatePeriod);
		lpSorting = (ListPreference) findPreference(Key_Sorting);
		cbNotifications = (CheckBoxPreference) findPreference(Key_Notifications);
		cbVibration = (CheckBoxPreference) findPreference(Key_Vibration);
		cbSound = (CheckBoxPreference) findPreference(Key_Sound);

		lpTimeInterval.setOnPreferenceChangeListener(this);
		lpSource.setOnPreferenceChangeListener(this);
		lpMagnitude.setOnPreferenceChangeListener(this);
		lpMapType.setOnPreferenceChangeListener(this);
		lpUpdatePeriod.setOnPreferenceChangeListener(this);
		lpSorting.setOnPreferenceChangeListener(this);
		cbNotifications.setOnPreferenceChangeListener(this);
		cbVibration.setOnPreferenceChangeListener(this);
		cbSound.setOnPreferenceChangeListener(this);

		initSummary(getPreferenceScreen());

		if (cbNotifications.isChecked()) {
			cbVibration.setEnabled(true);
			cbSound.setEnabled(true);
		}
		else {
			cbVibration.setEnabled(false);
			cbSound.setEnabled(false);
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		updatePrefSummary(findPreference(key));
	}

	private void initSummary(Preference p) {
		if (p instanceof PreferenceGroup) {
			PreferenceGroup pGrp = (PreferenceGroup) p;
			for (int i = 0; i < pGrp.getPreferenceCount(); i++) {
				initSummary(pGrp.getPreference(i));
			}
		}
		else {
			updatePrefSummary(p);
		}
	}

	private void updatePrefSummary(Preference p) {
		if (p instanceof ListPreference) {
			ListPreference listPref = (ListPreference) p;
			p.setSummary(listPref.getEntry());
		}
		if (p instanceof EditTextPreference) {
			EditTextPreference editTextPref = (EditTextPreference) p;
			if (p.getTitle().toString().contains("password")) {
				p.setSummary("******");
			}
			else {
				p.setSummary(editTextPref.getText());
			}
		}
		if (p instanceof MultiSelectListPreference) {
			EditTextPreference editTextPref = (EditTextPreference) p;
			p.setSummary(editTextPref.getText());
		}
		if (p instanceof CheckBoxPreference) {
			CheckBoxPreference listPref = (CheckBoxPreference) p;

			p.setSummary(listPref.isChecked() ? getResources().getString(R.string.statu_on) : getResources().getString(R.string.statu_off));
		}
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {

		String key = preference.getKey();
		String value = newValue.toString();

		if (key.equalsIgnoreCase(Key_TimeInterval)) {
			lpTimeInterval.setSummary(lpTimeInterval.getEntries()[Integer.parseInt(value)]);
		}
		else if (key.equalsIgnoreCase(Key_Source)) {
			lpSource.setSummary(lpSource.getEntries()[Integer.parseInt(value)]);
		}
		else if (key.equalsIgnoreCase(Key_Magnitude)) {
			lpMagnitude.setSummary(lpMagnitude.getEntries()[Integer.parseInt(value)]);
		}
		else if (key.equalsIgnoreCase(Key_MapType)) {
			lpMapType.setSummary(lpMapType.getEntries()[Integer.parseInt(value)]);
		}
		else if (key.equalsIgnoreCase(Key_UpdatePeriod)) {
			lpUpdatePeriod.setSummary(lpUpdatePeriod.getEntries()[Integer.parseInt(value)]);
		}
		else if (key.equalsIgnoreCase(Key_Sorting)) {
			lpSorting.setSummary(lpSorting.getEntries()[Integer.parseInt(value)]);
		}
		else if (key.equalsIgnoreCase(Key_Notifications)) {

			if (value.equals("true")) {
				cbNotifications.setSummary(getResources().getString(R.string.statu_on));
				cbVibration.setEnabled(true);
				cbSound.setEnabled(true);
			}
			else {
				cbNotifications.setSummary(getResources().getString(R.string.statu_off));
				cbVibration.setEnabled(false);
				cbVibration.setChecked(false);
				cbVibration.setSummary(getResources().getString(R.string.statu_off));
				cbSound.setEnabled(false);
				cbSound.setChecked(false);
				cbSound.setSummary(getResources().getString(R.string.statu_off));
			}

		}
		else if (key.equalsIgnoreCase(Key_Vibration)) {

			if (value.equals("true")) {
				cbVibration.setSummary(getResources().getString(R.string.statu_on));
			}
			else {
				cbVibration.setSummary(getResources().getString(R.string.statu_off));
			}
		}
		else if (key.equalsIgnoreCase(Key_Sound)) {

			if (value.equals("true")) {
				cbSound.setSummary(getResources().getString(R.string.statu_on));
			}
			else {
				cbSound.setSummary(getResources().getString(R.string.statu_off));
			}

		}
		return true;
	}
}
