package com.sefagurel.lastearthquakes.utils;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.json.XML;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sefagurel.lastearthquakes.database.EarthQuakes;
import com.sefagurel.lastearthquakes.sources.koeri.earhquake;
import com.sefagurel.lastearthquakes.sources.koeri.eqlist;
import com.sefagurel.lastearthquakes.sources.koeri.item;
import com.sefagurel.lastearthquakes.sources.seismicportal.features;
import com.sefagurel.lastearthquakes.sources.seismicportal.geometry;
import com.sefagurel.lastearthquakes.sources.seismicportal.object;
import com.sefagurel.lastearthquakes.sources.seismicportal.properties;
import com.sefagurel.lastearthquakes.sources.seismicportal.totalCount;
import com.sefagurel.lastearthquakes.sources.usgs.features1;
import com.sefagurel.lastearthquakes.sources.usgs.geometry1;
import com.sefagurel.lastearthquakes.sources.usgs.metadata1;
import com.sefagurel.lastearthquakes.sources.usgs.properties1;
import com.sefagurel.lastearthquakes.sources.usgs.usgs;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by Sefa Gurel on 15.5.2015. sefagurel89@gmail.com
 */
public class SaveResponseToDB {

	private int	decimalPlace	= 1;

	public SaveResponseToDB() {
		// DatabaseHelper.getDbHelper().clearDatabase();
	}

	public void saveDatabaseKoeri(String url) {

		try {

			Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat(Tools.DATEFORMAT_KOERI).create();

			Type listType = new TypeToken<eqlist<earhquake<List<item>>>>() {
			}.getType();

			String str1 = getJson(url);

			if (str1 == null || str1.length() < 10) {
				return;
			}

			String json = XML.toJSONObject(str1).toString();

			if (json == null || json.length() < 10) {
				return;
			}

			eqlist<earhquake<List<item>>> items = gson.fromJson(json, listType);

			if (items == null || items.getEqlist().getEarhquake() == null) {
				return;
			}

			List<item> list = items.getEqlist().getEarhquake();

			if (list.size() == 0) {
				return;
			}

			for (item item1 : list) {

				EarthQuakes eq = new EarthQuakes();
				eq.setDateMilis(item1.getName().getTime());
				eq.setDepth(round(item1.getDepth(), decimalPlace));
				eq.setLatitude(item1.getLat());
				eq.setLongitude(item1.getLng());
				eq.setLocationName(item1.getLokasyon().trim().toUpperCase());
				eq.setMagnitude(round(item1.getMag(), decimalPlace));
				eq.setSource(1);

				eq.Insert();

			}

		}
		catch (Exception e) {
			Tools.catchException(e);
		}

	}

	public void saveDatabaseSeismicPortal(String url) {

		try {

			Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat(Tools.DATEFORMAT_SEISMICPORTAL).create();

			Type listType = new TypeToken<object<totalCount, List<features<geometry, properties>>>>() {
			}.getType();

			String json = getJson(url);

			if (json == null || json.length() < 10) {
				return;
			}

			object<totalCount, List<features<geometry, properties>>> items = gson.fromJson(json, listType);

			if (items == null || items.getFeatures() == null || items.getFeatures().size() == 0) {
				return;
			}

			for (features<geometry, properties> item : items.getFeatures()) {

				Calendar cal = Calendar.getInstance();
				cal.setTime(item.getProperties().getTime());
				cal.add(Calendar.HOUR_OF_DAY, 2);

				EarthQuakes eq = new EarthQuakes();
				eq.setDateMilis(cal.getTime().getTime());
				eq.setDepth(round(item.getProperties().getDepth(), decimalPlace));
				eq.setLatitude(item.getProperties().getLat());
				eq.setLongitude(item.getProperties().getLon());
				eq.setLocationName(item.getProperties().getFlynn_region().trim().toUpperCase());
				eq.setMagnitude(round(item.getProperties().getMag(), decimalPlace));
				eq.setSource(2);
				eq.Insert();

			}

		}
		catch (Exception e) {
			Tools.catchException(e);
		}

	}

	public void saveDatabaseUsgs(String url) {

		try {

			Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat(Tools.DATEFORMAT).create();

			Type listType = new TypeToken<usgs<metadata1, features1<properties1, geometry1>>>() {
			}.getType();

			String json = getJson(url);

			if (json == null || json.length() < 10) {
				return;
			}

			usgs<metadata1, features1<properties1, geometry1>> items = gson.fromJson(json, listType);

			if (items == null || items.getFeatures() == null || items.getFeatures().size() == 0) {
				return;
			}

			for (features1<properties1, geometry1> item : items.getFeatures()) {

				String str1 = item.getProperties().getPlace().trim().toUpperCase();
				String str2 = str1.substring(str1.indexOf("OF") + 3);

				EarthQuakes eq = new EarthQuakes();
				eq.setDateMilis(Long.parseLong(item.getProperties().getTime()));
				eq.setDepth(round(item.getGeometry().getCoordinates().get(2), decimalPlace));
				eq.setLatitude(item.getGeometry().getCoordinates().get(1));
				eq.setLongitude(item.getGeometry().getCoordinates().get(0));
				eq.setLocationName(str2);
				eq.setMagnitude(round(item.getProperties().getMag(), decimalPlace));
				eq.setSource(3);
				eq.Insert();

			}

		}
		catch (Exception e) {
			Tools.catchException(e);
		}

	}

	private String getJson(String reqUrl) throws Exception {
		Request request = new Request.Builder().url(reqUrl).build();
		Response response = new OkHttpClient().newCall(request).execute();
		return response.isSuccessful() ? response.body().string() : "";
	}

	public float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}

}
