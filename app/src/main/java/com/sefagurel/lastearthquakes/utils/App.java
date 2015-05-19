package com.sefagurel.lastearthquakes.utils;

import android.app.Application;
import android.content.Context;

import com.sefagurel.lastearthquakes.R;
import com.splunk.mint.Mint;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */
public class App extends Application {

	public static Context		AppContext	= null;
	public static MainThreadBus	bus			= null;

	@Override
	public void onCreate() {
		super.onCreate();

		AppContext = getApplicationContext();

		Mint.initAndStartSession(App.this, getString(R.string.Mint_apiKey));

		bus = new MainThreadBus();

	}
}
