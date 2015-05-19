package com.sefagurel.lastearthquakes.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.splunk.mint.Mint;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */
public class Tools {

	public static boolean isOnline(Context act) {
		ConnectivityManager cm = (ConnectivityManager) act.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		return netInfo != null && netInfo.isConnectedOrConnecting();
	}

	public static void catchException(Exception ex) {
		ex.printStackTrace();
		Mint.logException(ex);
	}

	public static int		syncPeriod					= 1000 * 15;

	public static String	DATEFORMAT					= "yyyy-MM-dd HH:mm:ss";
	public static String	DATEFORMAT_KOERI			= "yyyy.MM.dd HH:mm:ss";
	public static String	DATEFORMAT_SEISMICPORTAL	= "yyyy-MM-dd'T'HH:mm:ss.S'Z'";
}
