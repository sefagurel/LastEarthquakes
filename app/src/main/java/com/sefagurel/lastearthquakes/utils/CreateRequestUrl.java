package com.sefagurel.lastearthquakes.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */
public class CreateRequestUrl {

	public static String URL_SEISMICPORTAL(int day) { // maksimum 10 gun geriye gidilebilir

		int dateRange = 1;

		if (day == 0) {
			dateRange = 1;
		}
		else if (day == 1) {
			dateRange = 7;
		}
		else if (day == 2) {
			dateRange = 10;
		}

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -dateRange);
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		String s = formatter.format(cal.getTime());
		return "http://www.seismicportal.eu/fdsnws/event/1/query?start=" + s + "&format=json";
	}

	public static String URL_KOERI(int day) {

		String str = "son24saat";

		if (day == 0) {
			str = "son24saat";
		}
		else if (day == 1) {
			str = "sonHafta";
		}
		else if (day == 2) {
			str = "sonay";
		}

		return "http://www.koeri.boun.edu.tr/sismo/zeqmap/xmlt/" + str + ".xml";
	}

	public static String URL_USGS(int day) {

		String str = "all_day";

		if (day == 0) {
			str = "all_day";
		}
		else if (day == 1) {
			str = "1.0_week";
		}
		else if (day == 2) {
			str = "2.5_month";
		}

		return "http://earthquake.usgs.gov/earthquakes/feed/v0.1/summary/" + str + ".geojson";
	}

}
